package com.fourgraphics.test.scripts.Generic;

import com.fourgraphics.*;
import com.fourgraphics.test.ShowcaseGame;
import com.fourgraphics.test.scripts.Enemies.Enemy;

import static com.fourgraphics.SceneManager.destroy;

public class Ultimate extends Attack
{
    float projectileSpeed = 70f;
    Vector2 projectileDirection = new Vector2();
    float lifeTime = 5f; //tempo di vita in secondi
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
        if (parentName.equalsIgnoreCase("player") && other.gameObject.hasComponent(Enemy.class))
        {
            other.gameObject.getComponent(Enemy.class).damage();
        }
    }

    public static void CreateAttack(Vector2 spawnPosition, Vector2 direction, String parent)
    {
        Animator animator = new Animator();
        if (direction.getX() == -1)
        {
            animator.addAnimation(ShowcaseGame.ultimateLeft.clone());
            animator.playAnimation("ultimateLeft");
        } else
        {
            animator.addAnimation(ShowcaseGame.ultimateRight.clone());
            animator.playAnimation("ultimateRight");
        }

        Ultimate ult = new Ultimate();
        ult.projectileDirection.set(direction);
        ult.SetParent(parent);
        int size = 65;

        SceneManager.instantiate(GameObject.Compose(
                "ult",
                spawnPosition,
                new Vector2(size * 1.66f, size),
                new CircleCollider(true),
                animator,
                ult
        ));
    }
}
