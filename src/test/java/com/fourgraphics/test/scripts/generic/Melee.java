package com.fourgraphics.test.scripts.generic;

import com.fourgraphics.*;
import com.fourgraphics.test.ShowcaseGame;
import com.fourgraphics.test.scripts.enemies.Enemy;
import com.fourgraphics.test.scripts.player.PlayerCombat;

import static com.fourgraphics.SceneManager.destroy;

public class Melee extends Attack
{
    @Override
    public void Update()
    {
        if(gameObject.getComponent(Animator.class).getCurrentAnimation().isEnded())
            destroy(gameObject);
    }

    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction)
    {
        Animator anim = gameObject.getComponent(Animator.class);
        if (other.gameObject.getName().equalsIgnoreCase("player") && !parentName.equalsIgnoreCase("player"))
        {
            other.gameObject.getComponent(PlayerCombat.class).damage();
        } else if (parentName.equalsIgnoreCase("player") && other.gameObject.hasComponent(Enemy.class))
        {
            other.gameObject.getComponent(Enemy.class).damage();
        }
    }

    public static void CreateAttack(Vector2 spawnPosition, Vector2 direction, String parent, int size)
    {
        Animator animator = new Animator();
        if (direction.getX() == -1)
        {
            animator.addAnimation(ShowcaseGame.meleeAnimationLeft.clone());
            animator.playAnimation("meleeLeft");
        } else
        {
            animator.addAnimation(ShowcaseGame.meleeAnimationRight.clone());
            animator.playAnimation("meleeRight");
        }

        Melee atk = new Melee();
        atk.SetParent(parent);

        SceneManager.instantiate(GameObject.Compose(
                "melee",
                spawnPosition,
                new Vector2(size, size),
                new RectCollider(true, true),
                animator,
                atk
        ));
    }
}
