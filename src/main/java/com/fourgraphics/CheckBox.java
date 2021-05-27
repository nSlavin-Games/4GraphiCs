package com.fourgraphics;

import processing.core.PImage;

public class CheckBox extends UIElement
{
    static PImage defaultCheck;
    private PImage fillTexture;
    private PImage checkIcon;
    private int fillColor;
    private boolean checked;

    public CheckBox(String text, int color, int fillColor, PImage checkIcon, boolean isWorldSpace)
    {
        super(text, color, isWorldSpace);
        this.fillColor = fillColor;
        this.checkIcon = checkIcon;
        defaultCheck = sketch.loadImage("../resources/defaultCheck.png");
    }

    public CheckBox(String text, int color, PImage texture, PImage fillTexture, PImage checkIcon, boolean isWorldSpace)
    {
        super(text, texture, isWorldSpace);
        this.fillTexture = fillTexture;
        this.checkIcon = checkIcon;
    }

    @Override
    protected void display()
    {
        if (!worldSpace)
        {
            Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
            sketch.noStroke();

            sketch.pushMatrix();
            sketch.translate(Rescaler.resizeW(cameraPosition.getX()), Rescaler.resizeH(cameraPosition.getY()));

        }

        float rx = Rescaler.resizeW(transform.getPosition().getX());
        float ry = Rescaler.resizeH(transform.getPosition().getY());
        float rw = Rescaler.resizeH(transform.getScale().getX());
        float rh = Rescaler.resizeH(transform.getScale().getY());

        if (texture == null || fillTexture == null)
        {
            if (!isChecked())
                sketch.fill(color);
            else
                sketch.fill(fillColor);
            sketch.noStroke();
            sketch.rectMode(sketch.CORNER);
            sketch.rect(transform.getPosition().getX() - transform.getScale().getX() / 2, transform.getPosition().getY() - transform.getScale().getY() / 2, transform.getScale().getY(), transform.getScale().getY());
        }
        if (!isChecked())
        {
            sketch.imageMode(sketch.CENTER);
            sketch.image(texture, rx, ry, rw, rh);
        } else
        {
            sketch.imageMode(sketch.CENTER);
            if (checkIcon != null)
                sketch.image(checkIcon, rx, ry, rw, rh);
            else
                sketch.image(defaultCheck, rx, ry, rw, rh);
        }
        sketch.textMode(sketch.CORNER);
        sketch.text(text, rx + rh, ry, rw, rh);
        if (!worldSpace)
            sketch.popMatrix();
    }

    public boolean mouseOver()
    {
        float rx = Rescaler.resizeW(transform.getPosition().getX());
        float ry = Rescaler.resizeH(transform.getPosition().getY());
        float rw = Rescaler.resizeH(transform.getScale().getX());
        float rh = Rescaler.resizeH(transform.getScale().getY());

        return sketch.mouseX > rx - rw / 2 && sketch.mouseX < rx + rw / 2 && sketch.mouseY > ry - rh / 2 && sketch.mouseY < ry + rh / 2;
    }

    public void toggleChecked()
    {
        checked = !checked;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    @Override
    public CheckBox clone()
    {
        try
        {
            return new CheckBox(this.text, this.color, (PImage) this.texture.clone(), (PImage) this.fillTexture.clone(), (PImage) this.checkIcon.clone(), this.worldSpace);
        } catch (Exception ignored)
        {    //NOTE(sv-molinari): kinda cursed ngl
            try
            {
                return new CheckBox(this.text, this.color, this.fillColor, (PImage) this.checkIcon.clone(), this.worldSpace);
            } catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
                DebugConsole.ErrorInternal("Unable to clone CheckBox", e.getStackTrace(), Thread.currentThread().getStackTrace());
                return null;
            }
        }
    }
}
