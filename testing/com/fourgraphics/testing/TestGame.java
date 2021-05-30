package com.fourgraphics.testing;

import com.fourgraphics.objects.Vector2;
import com.fourgraphics.scenes.FGCApp;
import com.fourgraphics.scenes.SceneManager;

public class TestGame extends FGCApp
{
    public static void main(String[] args)
    {
        TestGame self = new TestGame();
        self.debugMode = true;
        SceneManager.Run(self);
    }

    protected void OnGameStart()
    {
        Vector2 vec = new Vector2(100,20);
        System.out.println(vec.Magnitude());
        System.out.println(vec.Normalized().Magnitude() + " | " + vec.Magnitude());
        System.out.println(vec.Normalize().Magnitude() + " | " + vec.Magnitude());
    }
}
