package com.fourgraphics.test.scripts.Enemies;

import com.fourgraphics.Animator;
import com.fourgraphics.Collider;
import com.fourgraphics.CollisionDirection;
import com.fourgraphics.Vector2;
import com.fourgraphics.test.scripts.Generic.Projectile;

public class Mage extends Enemy
{
    Animator anim;

    @Override
    public void Start()
    {
        super.Start();
        attackRecovery = 1.5f;
        attackRange = 300f;
        anim = gameObject.getComponent(Animator.class);
        PlayIdleAnim(Vector2.LEFT());
    }

    @Override
    public void Update()
    {
        super.Update();

        Vector2 distVector = new Vector2();
        distVector.set(transform.getPosition());
        distVector.sum(-player.transform.getPosition().getX(),-player.transform.getPosition().getY());
        float distance = (float)Math.sqrt(distVector.getX()*distVector.getX()+distVector.getY()*distVector.getY());

        if(anim.getCurrentAnimation().getName().contains("Attack") && anim.getCurrentAnimation().isEnded())
            PlayIdleAnim(distVector);

        if(distance <= attackRange && attackTimer <= 0)
        {
            Vector2 direction = distVector.getX() > 0 ? Vector2.LEFT() : Vector2.RIGHT();
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(direction.multiplyN(transform.getScale().getX() / 2 + 15));
            Projectile.CreateAttack(position, direction, gameObject.getName());
            PlayAttackAnim(distVector);
            attackTimer = attackRecovery;
        }
    }

    private void PlayIdleAnim(Vector2 distVector)
    {
        if(distVector.getX() > 0)
            anim.playAnimation("mageIdleLeft");
        else
            anim.playAnimation("mageIdleRight");
    }

    private void PlayAttackAnim(Vector2 distVector)
    {
        if(distVector.getX() > 0)
            anim.playAnimation("mageAttackLeft");
        else
            anim.playAnimation("mageAttackRight");
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
    }
}
