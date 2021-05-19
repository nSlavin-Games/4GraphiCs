package com.fourgraphics.test;

import com.fourgraphics.Collider;
import com.fourgraphics.CollisionDirection;
import com.fourgraphics.SceneManager;
import com.fourgraphics.Vector2;

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

    public void Start() {
        currentHealth = 1;
    }

    public void Update() {
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

    public void OnCollisionEnter(Collider self, Collider other, CollisionDirection direction) {
        if (direction == CollisionDirection.DOWN && other.gameObject.getName().equalsIgnoreCase("terrain")) {
            isGrounded = true;
            yVelocity = 0;
            yForce = 0;
        }
    }
}
