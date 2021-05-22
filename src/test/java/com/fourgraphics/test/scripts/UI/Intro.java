package com.fourgraphics.test.scripts.UI;

import com.fourgraphics.SceneManager;
import com.fourgraphics.Script;

public class Intro extends Script
{
    float introDuration = 5;
    float introTimer;

    public void Update()
    {
        introTimer += SceneManager.deltaTime();
        if(introTimer >= introDuration)
            SceneManager.loadScene(1);
    }
}
