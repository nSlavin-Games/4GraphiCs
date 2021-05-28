package com.fourgraphics.test.scripts.ui;

import com.fourgraphics.*;
import processing.core.PImage;

import java.lang.reflect.Method;
import java.util.Objects;

public class MainMenuManager extends Script
{
    private Button play;
    PImage playNormal;
    PImage playHover;

    private Button exit;
    PImage exitNormal;
    PImage exitHover;

    public void Start()
    {
        playNormal = sketch.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Images/UI/MainMenu/play.png")).getPath());
        playHover = sketch.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Images/UI/MainMenu/playHover.png")).getPath());
        exitNormal = sketch.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Images/UI/MainMenu/exit.png")).getPath());
        exitHover = sketch.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Images/UI/MainMenu/exitHover.png")).getPath());
        play = SceneManager.findObject("play button").getComponent(Button.class);
        exit = SceneManager.findObject("exit button").getComponent(Button.class);

        play.addOnClickMethods(Utils.getMethodInCurrentClass("Play"));
        exit.addOnClickMethods(Utils.getMethodInCurrentClass("Exit"));

        play.setTexture(playNormal);
        exit.setTexture(exitNormal);
        DebugConsole.Info("Loaded Menu");
    }

    public void Update()
    {
        if(Input.getKeyDown("F"))
            DebugConsole.Info("F");

        if(play.mouseOver())
            play.setTexture(playHover);
        else
            play.setTexture(playNormal);

        if(exit.mouseOver())
            exit.setTexture(exitHover);
        else
            exit.setTexture(exitNormal);
    }

    public void Play()
    {
        SceneManager.loadScene(2);
    }

    public void Exit()
    {
        SceneManager.quit();
    }
}
