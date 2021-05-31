package com.fourgraphics;

import javafx.scene.Scene;
import processing.core.PImage;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Elemento UI semplice che sovrascrive il display e indica un modo per capire se il mouse si trova sopra al bottone.
 */
public class Button extends UIElement {

    private ArrayList<Method> onClickMethods;

    /**
     * Costruttore per la classe Button che accetta una PImage come texutre
     *
     * @param text  Testo del bottone
     * @param color Colore default del bottone
     */
    public Button(String text, int color, boolean isWorldSpace, Method... methods) {
        super(text, color, isWorldSpace);
        onClickMethods = new ArrayList<>();
        onClickMethods.addAll(Arrays.asList(methods));
    }

    /**
     * Costruttore per la classe Button che accetta il percorso del file come texutre
     *
     * @param text    Testo del bottone
     * @param texture Percorso del file della texture
     */
    public Button(String text, PImage texture, boolean isWorldSpace, Method... methods) {
        super(text, texture, isWorldSpace);
        onClickMethods = new ArrayList<>();
        onClickMethods.addAll(Arrays.asList(methods));
    }


    /**
     * Mostra il bottone
     */
    protected void display() {
        //world space, i blocchi, man mano che ti sposti scompaiono
        if (!worldSpace) {
            Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
            sketch.pushMatrix();
            sketch.translate(Rescaler.resizeH(cameraPosition.getX()), Rescaler.resizeH(cameraPosition.getY()));
        }

        float rx = Rescaler.resizeW(transform.getPosition().getX());
        float ry = Rescaler.resizeH(transform.getPosition().getY());
        float rw = Rescaler.resizeH(transform.getScale().getX());
        float rh = Rescaler.resizeH(transform.getScale().getY());

        sketch.noStroke();
        sketch.rectMode(sketch.CENTER);
        if (texture == null) {
            sketch.fill(color);
            sketch.rect(rx, ry, rw, rh);
        } else {
            sketch.imageMode(sketch.CENTER);
            sketch.image(texture, rx, ry, rw, rh);
        }
        sketch.fill(0);
        sketch.textAlign(sketch.CENTER, sketch.CENTER);
        sketch.textSize(20);
        sketch.text(text, rx, ry, rw, rh);
        if (!worldSpace)
            sketch.popMatrix();
    }

    protected boolean onClick()
    {
        //DebugConsole.Info(gameObject.getName() + "|" + mouseOver() + "|"+Input.getMouseButtonDown(0));

        if(mouseOver() && Input.getMouseButtonUp(0))
        {
            try
            {
                for (int i = 0; i < onClickMethods.size(); i++)
                {
                    onClickMethods.get(i).invoke(SceneManager.findObjectWithType(onClickMethods.get(i).getDeclaringClass()).getComponent(onClickMethods.get(i).getDeclaringClass()));
                }
                return true;
            } catch(Exception e)
            {
                e.printStackTrace();
                DebugConsole.ErrorInternal("Button Click Error", "There has been an issue with the execution of an OnClick method, make sure it is public and it takes no parameters", e.getStackTrace(), e.getStackTrace());
            }
        }
        return false;
    }

    /**
     * Indica se il mouse è sopra il bottone
     *
     * @return Se il bottone è in evidenza (mouse sopra il bottone) restituisce true, altrimenti restituisce false
     */
    public boolean mouseOver() {
        Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
        float rx = Rescaler.resizeW(transform.getPosition().getX()) + Rescaler.resizeH(cameraPosition.getX()) + Rescaler.currentWidth/2;
        float ry = Rescaler.resizeH(transform.getPosition().getY()) + Rescaler.resizeH(cameraPosition.getY()) + Rescaler.currentHeight/2;
        float rw = Rescaler.resizeH(transform.getScale().getX());
        float rh = Rescaler.resizeH(transform.getScale().getY());

        return sketch.mouseX > rx - rw / 2 && sketch.mouseX < rx + rw / 2 && sketch.mouseY > ry - rh / 2 && sketch.mouseY < ry + rh / 2;
    }

    @Override
    public Button clone() {
        try {
            return new Button(this.text, (PImage) this.texture.clone(), this.worldSpace);
        } catch (Exception ignored) {
            return new Button(this.text, this.color, this.worldSpace);
        }
    }

    public void setTexture(PImage texture)
    {
        this.texture = texture;
    }

    public void addOnClickMethods(Method... methods)
    {
        onClickMethods.addAll(Arrays.asList(methods));
    }
}
