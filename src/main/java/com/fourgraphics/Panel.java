package com.fourgraphics;

import processing.core.PImage;

/**
 * Elemento UI semplice che sovrascrive il metodo display per mostrare un pannello (con o senza testo), è l'equivalente di una TextBox
 */
public class Panel extends UIElement
{

    /**
     * Costruttore per la classe Panel che accetta una PImage come texutre
     *
     * @param text  Testo del pannello di testo
     * @param color Colore default del pannello di testo
     */
    public Panel(String text, int color, boolean space)
    {
        super(text, color, space);
    }

    /**
     * Costruttore per la classe Panel che accetta il percorso del file come texutre
     *
     * @param text    Testo del pannello di testo
     * @param texture Percorso del file della texture
     */
    public Panel(String text, PImage texture, boolean space)
    {
        super(text, texture, space);
    }


    /**
     * Mostra il pannello di testo
     */
    protected void display()
    {
        if (!worldSpace)
        {
            Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
            sketch.pushMatrix();
            sketch.translate(Rescaler.resizeW(cameraPosition.getX()), Rescaler.resizeH(cameraPosition.getY()));
        }

        float rx = Rescaler.resizeW(transform.getPosition().getX());
        float ry = Rescaler.resizeH(transform.getPosition().getY());
        float rw = Rescaler.resizeH(transform.getScale().getX());
        float rh = Rescaler.resizeH(transform.getScale().getY());

        sketch.noStroke();
        sketch.rectMode(sketch.CENTER);
        if (texture == null)
        {
            sketch.fill(color);
            sketch.rect(rx, ry, rw, rh);
        } else
        {
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

    @Override
    public Panel clone()
    {
        if (texture == null)
            return new Panel(text, color, worldSpace);
        else
            return new Panel(text, texture, worldSpace);
    }

    public void setTexture(PImage texture)
    {
        this.texture = texture;
    }
}
