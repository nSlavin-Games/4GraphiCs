package com.fourgraphics.test;

import com.fourgraphics.*;

public class PlayerMovement extends Script
{
    boolean isGrounded;
    float yVelocity;
    float yForce;
    float jumpForce = 400f; //potenza del salto
    float gravityScale = 9.81f; //valore della gravità

    float speed = 150f;

    public void Start()
    {
        SceneManager.getActiveScene().getCamera().setTarget(gameObject);
    }

    public void Update()
    {
        /*Muovo il personaggio sull'asse orizzontale

        Spiegazione Calcolo:
        Vector2.RIGHT() Restituisce un vettore che ha X = 1 e Y = 0
        Se lo moltiplico per 1 valore singolo sarà X * Val e Y * val, avendo Y = 0 Y non cambierà
        Il valore per cui lo moltiplicherò sarà la velocità * il deltaTime(per adattarlo al framerate) * il valore del'axis "Horizontal" definito
        Un axis restituisce -1/0/1 a seconda dei tasti premuti

        Supponiamo che Speed sia 150(unità/s), che il nostro framerate sia di 60FPS e quindi il deltaTime sia 0,016 circa
        Ogni frame mi potrò muovere di 150 * 0,016 * axis
        Detto questo se non premo niente non mi muoverò di nulla, se premo il tasto positivo mi potrò muovere verso destra perché sto moltiplicando per 1
        altrimenti mi muovo verso sinistra perché moltiplico per -1
        */
        transform.getPosition().sum(Vector2.RIGHT().multiply(speed * SceneManager.deltaTime() * Input.getAxis("Horizontal")));

        //Controllo se sono a terra
        if(isGrounded)
        {
            //Se premo i possibili input per il salto
            if(Input.getButtonDown("Jump"))
            {
                yForce = Vector2.UP().multiply(jumpForce).getY(); //imposto la forza da applicare all'asse Y indicandogli una direzione verso l'alto
                //Vector2.UP() Restituisce X = 0 e Y = -1
            }
            else //Se non provo a saltare allora imposto la velocity a 0 in modo da non continuare a spingere il personaggio verso il basso
                yVelocity = 0;
        }

        isGrounded = false; //resetto il grounded
    }

    public void FixedUpdate()
    {
        yForce += gravityScale; //alla forza applicata aggiungo la gravità, ogni calcolo è effettuato senza tenere in conto la massa dell'oggetto

        /*A questo punto muovo il personaggio applicandogli le varie forze

        Gli applico la velociti adattata al Fixed Time Step

        Dimezzo la forza da applicare e la moltiplico per il timestep al quadrato

        Sommo il tutto
         */
        transform.getPosition().sum(Vector2.DOWN().multiply(yVelocity * SceneManager.fixedDeltaTime() + (yForce/2 * (float)Math.pow(SceneManager.fixedDeltaTime(),2))));

        yVelocity += yForce * SceneManager.fixedDeltaTime(); //A questo punto alla velocity aggiungo la forza moltiplicata per il timestep
        /*if(yVelocity > gravity)
            yVelocity = gravity;*/
    }

    public void OnCollisionEnter(Collider self, Collider other, CollisionDirection direction)
    {
        if(direction == CollisionDirection.DOWN && other.gameObject.getName().equalsIgnoreCase("terrain"))
        {
            isGrounded = true;
        }
    }
}
