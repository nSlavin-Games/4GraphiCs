package com.fourgraphics;

import processing.core.PImage;

/**
 * Elemento UI semplice che sovrascrive il display e indica un modo per capire se il mouse si trova sopra al bottone.
 */
public class Button extends UIElement {

    /**
     * Costruttore per la classe Button che accetta una PImage come texutre
     *
     * @param text  Testo del bottone
     * @param color Colore default del bottone
     */
    public Button(String text, int color, boolean isWorldSpace) {
        super(text, color, isWorldSpace);
    }

    /**
     * Costruttore per la classe Button che accetta il percorso del file come texutre
     *
     * @param text    Testo del bottone
     * @param texture Percorso del file della texture
     */
    public Button(String text, PImage texture, boolean isWorldSpace) {
        super(text, texture, isWorldSpace);
    }


    /**
     * Mostra il bottone
     */
    protected void display() {
        //world space, i blocchi, man mano che ti sposti scompaiono
        if (!worldSpace) {
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

    /**
     * Indica se il mouse è sopra il bottone
     *
     * @return Se il bottone è in evidenza (mouse sopra il bottone) restituisce true, altrimenti restituisce false
     */
    public boolean mouseOver() {
        float rx = Rescaler.resizeW(transform.getPosition().getX()) + Rescaler.DEFAULT_WIDTH/2;
        float ry = Rescaler.resizeH(transform.getPosition().getY()) + Rescaler.DEFAULT_HEIGHT/2;
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
}
