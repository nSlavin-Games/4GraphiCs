package com.fourgraphics.test.scripts.generic;

import com.fourgraphics.Script;
import com.fourgraphics.Vector2;

public abstract class Attack extends Script
{
    String parentName; //parent info

    public void SetParent(String name)
    {
        parentName = name;
    }

    public static void CreateAttack(Vector2 spawnPosition, Vector2 direction, String parent)
    {

    }
}
