package com.fourgraphics.test.scripts.player;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.enemies.Slime;
import com.fourgraphics.test.scripts.generic.*

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

    //I-Frames
    public static final float iFrames = 1.5f;
    private float currentIframes = 0;

    Animator anim;
    Vector2 damageDirection = new Vector2();

    public void Start()
    {
        anim = gameObject.getComponent(Animator.class);
        currentHealth = 3;
        ultCharge = 0;
        fireballTimer = 0;
        meleeTimer = 0;
        currentIframes = 0;
    }

    public void Update()
    {
        if (meleeTimer >= 0)
            meleeTimer -= SceneManager.deltaTime();
        if (fireballTimer >= 0)
            fireballTimer -= SceneManager.deltaTime();
        if (currentIframes >= 0)
            currentIframes -= SceneManager.deltaTime();

        if (Input.getButtonDown("Melee") && meleeTimer <= 0)
        {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + transform.getScale().getY()/2));
            PlayAttackAnim(direction);
            Melee.CreateAttack(position, direction, gameObject.getName(),(int)transform.getScale().getY());
            if (ultCharge < 7)
                ultCharge++;
            meleeTimer = meleeRecovery;
        }

        if (Input.getButtonDown("Ranged") && canUseRanged && fireballTimer <= 0)
        {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + 15));
            PlayThrowAnim(direction);
            Projectile.CreateAttack(position, direction, gameObject.getName(),35);
            fireballTimer = fireballRecovery;
        }

        if (Input.getButtonDown("Ultimate") && ultCharge == 7)
        {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + 15));
            PlayThrowAnim(direction);
            Ultimate.CreateAttack(position, direction, gameObject.getName(),65);
            ultCharge = 0;
        }
        if (currentIframes > 0)
        {
            gameObject.getComponent(Renderable.class).renderElement = !gameObject.getComponent(Renderable.class).renderElement;
        } else
        {
            gameObject.getComponent(Renderable.class).renderElement = true;
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
        if (currentIframes <= 0)
        {
            PlayDamageAnim();
            currentIframes = iFrames;
            SceneManager.destroy(SceneManager.getActiveScene().getObject("Health " + currentHealth));
            super.damage();
        }
    }

    @Override
    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction)
    {
        if (other.gameObject.hasComponent(Attack.class) || (other.gameObject.hasComponent(Slime.class) && other.gameObject.getComponent(Slime.class).attackTimer <= 0))
        {
            if (direction.equals(CollisionDirection.LEFT))
                damageDirection.set(Vector2.RIGHT());
            if (direction.equals(CollisionDirection.RIGHT))
                damageDirection.set(Vector2.LEFT());
        }
    }

    private void PlayDamageAnim()
    {
        if (damageDirection.getX() > 0)
            anim.playAnimation("playerDamageLeft");
        else
            anim.playAnimation("playerDamageRight");
    }

    private void PlayAttackAnim(Vector2 distVector)
    {
        if (distVector.getX() > 0)
            anim.playAnimation("playerSlashRight");
        else
            anim.playAnimation("playerSlashLeft");
    }

    private void PlayThrowAnim(Vector2 distVector)
    {
        if (distVector.getX() > 0)
            anim.playAnimation("playerThrowRight");
        else
            anim.playAnimation("playerThrowLeft");
    }
}
