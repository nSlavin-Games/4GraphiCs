package com.fourgraphics.test.scripts.Player;

import com.fourgraphics.Input;
import com.fourgraphics.SceneManager;
import com.fourgraphics.Vector2;
import com.fourgraphics.test.scripts.Generic.Combat;
import com.fourgraphics.test.scripts.Generic.Melee;
import com.fourgraphics.test.scripts.Generic.Projectile;
import com.fourgraphics.test.scripts.Generic.Ultimate;

public class PlayerCombat extends Combat
{

    //Melee Combat
    float meleeRecovery = 0.5f;
    float meleeTimer;

    //Ranged Combat
    boolean canUseRanged = true;
    float fireballRecovery = 1.5f; //tempo di recupero della fireball in secondi
    float fireballTimer;

    //Ultimate
    public int ultCharge;

    public void Start() {
        currentHealth = 3;
        ultCharge = 0;
        fireballTimer = 0;
        meleeTimer = 0;
    }

    public void Update() {
        meleeTimer -= SceneManager.deltaTime();
        fireballTimer -= SceneManager.deltaTime();

        if(Input.getButtonDown("Melee") && meleeTimer <= 0)
        {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + 30));
            Melee.CreateAttack(position,direction,gameObject.getName());
            if(ultCharge < 7)
                ultCharge++;
            meleeTimer = meleeRecovery;
        }

        if (Input.getButtonDown("Ranged") && canUseRanged && fireballTimer <= 0) {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + 15));
            Projectile.CreateAttack(position, direction, gameObject.getName());
            fireballTimer = fireballRecovery;
        }

        if(Input.getButtonDown("Ultimate") && ultCharge == 7)
        {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + 15));
            Ultimate.CreateAttack(position, direction, gameObject.getName());
            ultCharge = 0;
        }
    }

    @Override
    public void die()
    {
        super.die();
        SceneManager.loadScene(1);
    }

    @Override
    public void damage()
    {
        SceneManager.destroy(SceneManager.getActiveScene().getObject("Health " + currentHealth));
        super.damage();
    }
}
