package com.fourgraphics.test.scripts.Enemies;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.Generic.Combat;

public class Enemy extends Combat
{
    //gestione gravità
    float yVelocity;
    float yForce;
    float gravityScale = 9.81f; //valore della gravità
    boolean isGrounded = false;

    //Sistema di combattimento
    GameObject player;
    float attackRange;
    float attackRecovery;
    public float attackTimer;

    public void Start() {
        currentHealth = 1;
        player = SceneManager.getActiveScene().getObject("player");
    }

    public void Update() {
        attackTimer -= SceneManager.deltaTime();

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
