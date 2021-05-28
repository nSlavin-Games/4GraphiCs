package com.fourgraphics.test.scripts.generic;

import com.fourgraphics.Collider;
import com.fourgraphics.CollisionDirection;
import com.fourgraphics.SceneManager;
import com.fourgraphics.Script;
import com.fourgraphics.test.scripts.enemies.Enemy;
import com.fourgraphics.test.scripts.player.PlayerMovement;

import static com.fourgraphics.SceneManager.destroy;

public class FellOff extends Script
{
    @Override
    public void OnCollisionStay(Collider self, Collider other, CollisionDirection direction)
    {
        if(other.gameObject.hasComponent(Enemy.class))
            destroy(other.gameObject);
        if(other.gameObject.hasComponent(PlayerMovement.class))
            SceneManager.loadScene(SceneManager.getActiveSceneIndex());
    }
}
