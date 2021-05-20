package com.fourgraphics.test;

import com.fourgraphics.Script;

import static com.fourgraphics.SceneManager.destroy;

public class Combat extends Script {
    public int currentHealth;

    public void damage() {
        currentHealth--;
    }

    public void die() {
        destroy(gameObject);
    }
}
