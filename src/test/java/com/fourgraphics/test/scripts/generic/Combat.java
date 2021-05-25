package com.fourgraphics.test.scripts.generic;

import com.fourgraphics.Script;

import static com.fourgraphics.SceneManager.destroy;

public class Combat extends Script {
    public int currentHealth;

    public void damage() {
        currentHealth--;

        if (currentHealth == 0)
            die();
    }

    public void die() {
        destroy(gameObject);
    }
}
