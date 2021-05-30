package com.fourgraphics.scenes;

import com.google.errorprone.annotations.ForOverride;

public class FGCApp
{
    public int width = 1280, height = 720;

    public boolean debugMode = false;

    public String gameTitle = "4GC Project";

    @ForOverride
    protected void OnGameStart()
    {
        System.out.println("DEFAULT: Nothing to do");
    }
}
