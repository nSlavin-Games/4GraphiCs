package com.fourgraphics.test.scripts.enemies;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.player.PlayerCombat;

import java.util.Random;

public class Slime extends Enemy
{
    Animator anim;

    float speed = 250;
    float randomAttackDuration = 0;

    boolean canAttack;

    Vector2 lastDir = new Vector2();
    Vector2 randomDir = new Vector2();

    public Slime(boolean canAttack)
    {
        this.canAttack = canAttack;
    }

    @Override
    public void Start()
    {
        super.Start();
        attackRecovery = 1.5f;
        attackRange = 300f;
        randomAttackDuration = 0;
        anim = gameObject.getComponent(Animator.class);
        PlayIdleAnim();
    }

    @Override
    public void Update()
    {
        super.Update();


        Vector2 distVector = new Vector2();
        distVector.set(transform.getPosition());
        distVector.sum(-player.transform.getPosition().getX(), -player.transform.getPosition().getY());
        float distance = (float) Math.sqrt(distVector.getX() * distVector.getX() + distVector.getY() * distVector.getY());

        if ((anim.getCurrentAnimation().getName().contains("Chase") && distance > attackRange || attackTimer > 0) && randomAttackDuration <= 0)
            PlayIdleAnim();

        if (canAttack)
        {
            if (distance <= attackRange && attackTimer <= 0)
            {
                Vector2 direction = distVector.getX() > 0 ? Vector2.LEFT() : Vector2.RIGHT();
                if (!anim.getCurrentAnimation().getName().contains("Chase") || !lastDir.equals(direction))
                    PlayAttackAnim(direction);

                lastDir.set(direction);
                transform.getPosition().sum(direction.multiply(speed * SceneManager.deltaTime()));
            }
            if (new Random().nextInt(300) >= 299 && attackTimer <= 0)
            {
                if (!anim.getCurrentAnimation().getName().contains("Chase") || !lastDir.equals(randomDir))
                {
                    randomAttackDuration = 0.5f + new Random().nextFloat() * (2f - 0.5f);
                    randomDir = new Random().nextBoolean() ? Vector2.LEFT() : Vector2.RIGHT();
                    lastDir.set(randomDir);
                    PlayAttackAnim(randomDir);
                }
            }
            if (randomAttackDuration >= 0)
            {
                randomAttackDuration -= SceneManager.deltaTime();
                if (attackTimer <= 0)
                    transform.getPosition().sum(randomDir.multiplyN(speed * SceneManager.deltaTime()));
            }
        }
    }

    private void PlayIdleAnim()
    {
        anim.playAnimation("slimeIdle");
    }

    private void PlayAttackAnim(Vector2 distVector)
    {
        if (distVector.getX() > 0)
            anim.playAnimation("slimeChaseRight");
        else
            anim.playAnimation("slimeChaseLeft");
    }

    @Override
    public void FixedUpdate()
    {
        super.FixedUpdate();
    }

    @Override
    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction)
    {
        super.OnCollisionStay(self, other, direction);
        if(canAttack)
        {
            if (attackTimer <= 0 && other.gameObject.getName().equals("player"))
            {
                other.gameObject.getComponent(PlayerCombat.class).damage();
                attackTimer = attackRecovery;
            }
        }
    }
}
