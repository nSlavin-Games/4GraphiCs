package com.fourgraphics;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Un elemento generico dell’interfaccia grafica, ha il riferimento al suo oggetto e al suo transform(per comodità anche se si potrebbe ottenere dal gameObject), deve avere riferimenti a tutto ciò che lo definisce graficamente.
 */
public abstract class UIElement extends Component {
    /**
     * Il testo scritto
     */
    protected String text;
    /**
     * Il colore dell’elemento UI
     */
    protected int color;
    /**
     * La texture dell’elemento UI
     */
    protected PImage texture;

    protected boolean worldSpace;

    /**
     * Costruttore generico per un elemento dell'interfaccia grafica con una texutre data tramite PImage
     *
     * @param text  Testo dell'elemento
     * @param color Colore default dell'elemento
     */
    public UIElement(String text, int color, boolean isWorldSpace) {
        this.text = text;
        this.color = color;
        this.worldSpace = isWorldSpace;
    }

    /**
     * Costruttore generico per un elemento dell'interfaccia grafica con una texutre data tramite il percorso del file
     *
     * @param text    Testo dell'elemento
     * @param texture Percorso del file della texture
     */
    public UIElement(String text, PImage texture, boolean isWorldSpace) {
        this.text = text;
        sketch = SceneManager.getApp();
        this.texture = texture;
        this.worldSpace = isWorldSpace;
    }

    /**
     * Mostra l'elemento dell'UI
     */
    abstract protected void display();

    public abstract UIElement clone();
}

