package com.fourgraphics.test;

import com.fourgraphics.*;

public class Enemy extends Combat {

    public enum EnemyType {
        Slime,
        Mage
    }

    public EnemyType type;

    //gestione gravità
    float yVelocity;
    float yForce;
    float jumpForce = 400f; //potenza del salto
    float gravityScale = 9.81f; //valore della gravità
    boolean isGrounded = false;

    //Sistema di combattimento
    GameObject player;
    float attackRange;
    float attackRecovery;
    float attackTimer;

    public void Start() {
        currentHealth = 1;
        switch (type)
        {
            case Mage:
                attackRecovery = 0.75f;
                attackRange = 300f;
            case Slime:
                attackRecovery = 1f;
        }
        player = SceneManager.getActiveScene().getObject("player");
    }

    public void Update() {
        attackTimer -= SceneManager.deltaTime();

        Vector2 distVector = new Vector2();
        distVector.set(transform.getPosition());
        distVector.sum(-player.transform.getPosition().getX(),-player.transform.getPosition().getY());
        float distance = (float)Math.sqrt(distVector.getX()*distVector.getX()+distVector.getY()*distVector.getY());

        sketch.fill(0,255,0,180);
        sketch.circle(transform.getPosition().getX(),transform.getPosition().getY(),attackRange*2);

        if(distance <= attackRange)
        switch (type)
        {
            case Mage:
                if(attackTimer <= 0)
                {
                    Vector2 direction = distVector.getX() > 0 ? Vector2.LEFT() : Vector2.RIGHT();
                    Vector2 cloneDirection = new Vector2();
                    cloneDirection.set(direction);
                    Vector2 position = new Vector2().sum(transform.getPosition()).sum(cloneDirection.multiply(transform.getScale().getX() / 2 + 15));
                    Projectile.CreateProjectile(position, direction, gameObject.getName());
                    attackTimer = attackRecovery;
                }
                break;
            case Slime:
                break;
        }

        if (currentHealth == 0)
            die();

        isGrounded = false;
    }

    public void FixedUpdate() {
        yForce += gravityScale; //alla forza applicata aggiungo la gravità, ogni calcolo è effettuato senza tenere in conto la massa dell'oggetto

        /*A questo punto muovo il personaggio applicandogli le varie forze

        Gli applico la velociti adattata al Fixed Time Step

        Dimezzo la forza da applicare e la moltiplico per il timestep al quadrato

        Sommo il tutto
         */
        transform.getPosition().sum(Vector2.DOWN().multiply(yVelocity * SceneManager.fixedDeltaTime() + ((yForce / 2) * (float) Math.pow(SceneManager.fixedDeltaTime(), 2))));

        yVelocity += yForce * SceneManager.fixedDeltaTime(); //A questo punto alla velocity aggiungo la forza moltiplicata per il timestep
    }

    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction) {
        if (direction == CollisionDirection.DOWN && other.gameObject.getName().equalsIgnoreCase("terrain")) {
            isGrounded = true;
            yVelocity = 0;
            yForce = 0;
        }
    }
}
