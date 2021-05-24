package com.fourgraphics.test.scripts.Player;

import com.fourgraphics.*;

import static com.fourgraphics.SceneManager.fixedDeltaTime;

public class PlayerMovement extends Script {

    Animator anim;

    boolean isGrounded;
    float yVelocity;
    float yForce;
    float jumpForce = 850f; //potenza del salto
    float gravityScale = 25f; //valore della gravità

    public int lastDirection = 1; //l'ultima direzione verso cui si è girato il giocatore
    float speed = 350f; //warawrrsr

    public void Start() {
        SceneManager.getActiveScene().getCamera().setTarget(gameObject);
        SceneManager.getActiveScene().getCamera().setOffset(new Vector2(0,-sketch.height/2+300));
        anim = gameObject.getComponent(Animator.class);
        anim.playAnimation("playerIdleRight");
    }

    public void Update() {
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
        transform.getPosition().sum(Vector2.RIGHT().multiply(speed * SceneManager.deltaTime() * Input.getAxis("Horizontal")));  //yes

        int temp = lastDirection;
        lastDirection = Input.getAxis("Horizontal") != 0 ? (int) Input.getAxis("Horizontal") : lastDirection;
        boolean dirChanged = temp != lastDirection;
        //Controllo se sono a terra
        if (isGrounded) {
            if((!anim.getCurrentAnimation().getName().contains("Slash") && !anim.getCurrentAnimation().getName().contains("Throw") && !anim.getCurrentAnimation().getName().contains("Damage")) ||
              ((anim.getCurrentAnimation().getName().contains("Slash") || anim.getCurrentAnimation().getName().contains("Throw") || anim.getCurrentAnimation().getName().contains("Damage")) && anim.getCurrentAnimation().isEnded()))
            {
                if (Input.getAxis("Horizontal") == 0 && (!anim.getCurrentAnimation().getName().contains("Idle") || dirChanged))
                    PlayIdle();
                else if (Input.getAxis("Horizontal") != 0 && (!anim.getCurrentAnimation().getName().contains("Run") || dirChanged))
                    PlayRun();
            }
            //Se premo i possibili input per il salto
            if (Input.getButtonDown("Jump")) {
                yForce = Vector2.UP().multiply(jumpForce).getY(); //imposto la forza da applicare all'asse Y indicandogli una direzione verso l'alto
                yVelocity += yForce * fixedDeltaTime();
                //Vector2.UP() Restituisce X = 0 e Y = -1
            }
        } else
        {
            if((!anim.getCurrentAnimation().getName().contains("Slash") && !anim.getCurrentAnimation().getName().contains("Throw") && !anim.getCurrentAnimation().getName().contains("Damage")) ||
                    ((anim.getCurrentAnimation().getName().contains("Slash") || anim.getCurrentAnimation().getName().contains("Throw") || anim.getCurrentAnimation().getName().contains("Damage")) && anim.getCurrentAnimation().isEnded()))
            {
                if (yVelocity < 0 && (!anim.getCurrentAnimation().getName().contains("Jump") || dirChanged))
                    PlayJump();
                else if (yVelocity > 0 && (!anim.getCurrentAnimation().getName().contains("Fall") || dirChanged))
                    PlayFall();
            }
        }

        isGrounded = false; //resetto il grounded
    }

    public void FixedUpdate()
    {
        yForce += gravityScale; //alla forza applicata aggiungo la gravità, ogni calcolo è effettuato senza tenere in conto la massa dell'oggetto
        if(isGrounded)
        {
            yForce = 0;
            yVelocity = 0;
        }

        /*A questo punto muovo il personaggio applicandogli le varie forze

        Gli applico la velociti adattata al Fixed Time Step

        Dimezzo la forza da applicare e la moltiplico per il timestep al quadrato

        Sommo il tutto
         */
        transform.getPosition().sum(Vector2.DOWN().multiply(yVelocity * fixedDeltaTime() + ((yForce / 2) * (float) Math.pow(fixedDeltaTime(), 2))));

        yVelocity += yForce * fixedDeltaTime(); //A questo punto alla velocity aggiungo la forza moltiplicata per il timestep
    }

    private void PlayRun()
    {
        if(lastDirection == 1)
            anim.playAnimation("playerRunRight");
        else
            anim.playAnimation("playerRunLeft");
    }

    private void PlayIdle()
    {
        if(lastDirection == 1)
            anim.playAnimation("playerIdleRight");
        else
            anim.playAnimation("playerIdleLeft");
    }

    private void PlayJump()
    {
        if(lastDirection == 1)
            anim.playAnimation("playerJumpRight");
        else
            anim.playAnimation("playerJumpLeft");
    }

    private void PlayFall()
    {
        if(lastDirection == 1)
            anim.playAnimation("playerFallRight");
        else
            anim.playAnimation("playerFallLeft");
    }

    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction)
    {
        if (direction == CollisionDirection.DOWN && other.gameObject.getName().equalsIgnoreCase("terrain")) {
            isGrounded = true;
            yVelocity = 0;
            yForce = 0;
        }
    }
}