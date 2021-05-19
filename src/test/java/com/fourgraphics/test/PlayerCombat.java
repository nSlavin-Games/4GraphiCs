package com.fourgraphics.test;

import com.fourgraphics.Input;
import com.fourgraphics.Vector2;

public class PlayerCombat extends Combat {
    boolean canUseRanged = true;

    public void Start() {
        currentHealth = 3;  //sdfsdhshfdhs
    }

    public void Update() {
        if (Input.getButtonDown("Ranged") && canUseRanged) {
            Vector2 direction = gameObject.getComponent(PlayerMovement.class).lastDirection == 1 ? Vector2.RIGHT() : Vector2.LEFT();
            Vector2 cloneDirection = new Vector2();
            cloneDirection.set(direction);
            Vector2 position = new Vector2().sum(transform.getPosition()).sum(cloneDirection.multiply(transform.getScale().getX() / 2 + 15));
            Projectile.CreateProjectile(position, direction, gameObject.getName());
        }
    }
}
