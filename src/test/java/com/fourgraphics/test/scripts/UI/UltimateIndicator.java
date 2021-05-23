package com.fourgraphics.test.scripts.UI;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.Player.PlayerCombat;
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
        gameObject.getComponent(Panel.class).setTexture(textureList.get(SceneManager.getActiveScene().getObject("player").getComponent(PlayerCombat.class).ultCharge));
    }
}
