package com.fourgraphics.test;

import com.fourgraphics.*;

public class PlayerMovement extends Script
{
    boolean isJumping;
    boolean isGrounded;
    float jumpForce = 350f; //potenza del salto
    float jumpTimer;
    float jumpDuration = .5f; //durata in secondi per il salto
    float gravityScale = 350f; //valore della gravità

    float speed = 150f;

    public void Start()
    {
        System.out.println("test");
    }
    public void Update()
    {
        if(isJumping)
        {
            transform.getPosition().sum(Vector2.UP().multiply(jumpForce*SceneManager.deltaTime()));
            jumpTimer -= SceneManager.deltaTime();
            if(jumpTimer <= 0)
            {
                isJumping = false;
            }
        } else
            transform.getPosition().sum(Vector2.DOWN().multiply(gravityScale*SceneManager.deltaTime()));

        if(sketch.keyPressed)
        {
            if(sketch.key == 'd')
            {
                transform.getPosition().sum(Vector2.RIGHT().multiply(speed*SceneManager.deltaTime()));
            } else if(sketch.key == 'a')
            {
                transform.getPosition().sum(Vector2.LEFT().multiply(speed*SceneManager.deltaTime()));
            } else if(sketch.key == 'd' && sketch.key == 'a')
            {
                transform.getPosition().sum(0);
            }
            if(isGrounded && sketch.key == ' ')
            {
                isJumping = true;
                jumpTimer = jumpDuration;
            }
        }
        isGrounded = false;
    }

    public void OnCollisionEnter(Collider self, Collider other, CollisionDirection direction)
    {
        if(direction == CollisionDirection.DOWN)
            isGrounded = true;
    }
}
