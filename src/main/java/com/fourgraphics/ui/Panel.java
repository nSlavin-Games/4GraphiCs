package com.fourgraphics.ui;

import com.fourgraphics.gameobjects.Transform;

import processing.core.PImage;

/**
 * Elemento UI semplice che sovrascrive il metodo display per mostrare un pannello (con o senza testo), è l'equivalente di una TextBox
 */
public class Panel extends UIElement {

    /**
     * Costruttore per la classe Panel che accetta una PImage come texutre
     *
     * @param text    Testo del pannello di testo
     * @param color   Colore default del pannello di testo
     * @param texture Texture del pannello di testo (PImage)
     */
    public Panel(String text, int color, PImage texture) {
        super(text, color, texture);
    }

    /**
     * Costruttore per la classe Panel che accetta il percorso del file come texutre
     *
     * @param text     Testo del pannello di testo
     * @param color    Colore default del pannello di testo
     * @param fileName Percorso del file della texture
     */
    public Panel(String text, int color, String fileName) {
        super(text, color, fileName);
    }


    /**
     * Mostra il pannello di testo
     */
    public void display() {
        sketch.noStroke();
        sketch.rectMode(sketch.CENTER);
        if (texture == null) {
            sketch.fill(color);
            sketch.rect(transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
        } else {
            sketch.imageMode(sketch.CENTER);
            sketch.image(texture, transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
        }
        sketch.fill(0);
        sketch.textAlign(sketch.CENTER, sketch.CENTER);
        sketch.textSize(20);
        sketch.text(text, transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
    }
}
