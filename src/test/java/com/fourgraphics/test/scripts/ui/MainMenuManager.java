package com.fourgraphics.test.scripts.ui;

import com.fourgraphics.*;
import javafx.scene.Scene;
import processing.core.PImage;

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

        play.setTexture(playNormal);
        exit.setTexture(exitNormal);
    }

    public void Update()
    {
        if(play.mouseOver())
        {
            play.setTexture(playHover);
            if(Input.getMouseButtonDown(0))
                Play();
        } else
            play.setTexture(playNormal);

        if(exit.mouseOver())
        {
            exit.setTexture(exitHover);
            if(Input.getMouseButtonDown(0))
                Exit();
        } else
            exit.setTexture(exitNormal);
    }

    private void Play()
    {
        SceneManager.loadScene(2);
    }

    private void Exit()
    {
        sketch.exit();
    }
}
