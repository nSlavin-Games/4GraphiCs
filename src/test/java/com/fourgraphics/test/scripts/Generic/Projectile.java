package com.fourgraphics.test.scripts.Generic;

import com.fourgraphics.*;
import com.fourgraphics.test.ShowcaseGame;
import com.fourgraphics.test.scripts.Enemies.Enemy;
import com.fourgraphics.test.scripts.Player.PlayerCombat;

import static com.fourgraphics.SceneManager.destroy;

public class Projectile extends Attack
{
    float projectileSpeed = 70f;
    Vector2 projectileDirection = new Vector2();
    float lifeTime = 2f; //tempo di vita in secondi
    float timeLived; //da quanto è nato

    public void Update()
    {
        //proiettile ad accelerazione
        transform.getPosition().sum(projectileDirection.multiply(projectileSpeed * SceneManager.deltaTime()));
        //proiettile a velocità costante
        /*Vector2 clone = new Vector2();
        clone.set(projectileDirection);
        transform.getPosition().sum(clone.multiply(projectileSpeed * SceneManager.deltaTime()));*/
        timeLived += SceneManager.deltaTime();
        if (timeLived >= lifeTime)
            destroy(gameObject);
    }

    @Override
    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction)
    {
        if (other.gameObject.getName().equalsIgnoreCase("player") && !parentName.equalsIgnoreCase("player"))
        {
            other.gameObject.getComponent(PlayerCombat.class).damage();
            destroy(gameObject);
        } else if (parentName.equalsIgnoreCase("player") && other.gameObject.hasComponent(Enemy.class))
        {
            other.gameObject.getComponent(Enemy.class).damage();
            destroy(gameObject);
        }
    }

    public static void CreateAttack(Vector2 spawnPosition, Vector2 direction, String parent, int size)
    {
        Animator animator = new Animator();
        if (direction.getX() == -1)
        {
            animator.addAnimation(ShowcaseGame.fireballAnimationLeft.clone());
            animator.playAnimation("fireballLeft");
        } else
        {
            animator.addAnimation(ShowcaseGame.fireballAnimationRight.clone());
            animator.playAnimation("fireballRight");
        }

        Projectile proj = new Projectile();
        proj.projectileDirection.set(direction);
        proj.SetParent(parent);

        SceneManager.instantiate(GameObject.Compose(
                "projectile",
                spawnPosition,
                new Vector2(size * 1.66f, size),
                new CircleCollider(true),
                animator,
                proj
        ));
    }
}
