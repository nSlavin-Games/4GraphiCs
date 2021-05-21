package com.fourgraphics.test;

import com.fourgraphics.Input;
import com.fourgraphics.SceneManager;
import com.fourgraphics.Vector2;

public class PlayerCombat extends Combat {

    //Melee Combat
    float meleeRecovery = 0.5f;
    float meleeTimer;

    //Ranged Combat
    boolean canUseRanged = true;
    float fireballRecovery = 1.5f; //tempo di recupero della fireball in secondi
    float fireballTimer;

    public void Start() {
        currentHealth = 3;
    }

    public void Update() {
        meleeTimer -= SceneManager.deltaTime();
        fireballTimer -= SceneManager.deltaTime();

        if(Input.getButtonDown("Melee") && meleeTimer <= 0)
        {
            System.out.println("melee attack");
            damage();
            meleeTimer = meleeRecovery;
        }

        if (Input.getButtonDown("Ranged") && canUseRanged && fireballTimer <= 0) {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 cloneDirection = new Vector2();
            cloneDirection.set(direction);
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(cloneDirection.multiply(transform.getScale().getX() / 2 + 15));
            Projectile.CreateProjectile(position, direction, gameObject.getName());
            fireballTimer = fireballRecovery;
        }

        if(currentHealth <= 0)
            die();
    }

    @Override
    public void die()
    {
        super.die();
        System.out.println("F");
        SceneManager.loadScene(0);
    }

    @Override
    public void damage()
    {
        SceneManager.destroy(SceneManager.getActiveScene().getObject("Health " + currentHealth));
        super.damage();
    }
}
