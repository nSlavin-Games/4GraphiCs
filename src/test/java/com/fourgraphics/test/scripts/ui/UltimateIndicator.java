package com.fourgraphics.test.scripts.ui;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.player.PlayerCombat;
import processing.core.PImage;

import java.util.ArrayList;

public class UltimateIndicator extends Script
{
    ArrayList<PImage> textureList;

    public void Start()
    {
        if(textureList == null)
        {
            textureList = new ArrayList<>();

            for(int i = 0; i < 8; i++)
                textureList.add(sketch.loadImage("../resources/Images/UI/Ultimate/UltiBar" + i + ".png"));
        }
    }

    public void Update()
    {
        gameObject.getComponent(Panel.class).setTexture(textureList.get(SceneManager.findObject("player").getComponent(PlayerCombat.class).ultCharge));
    }
}
