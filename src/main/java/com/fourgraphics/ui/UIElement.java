package com.fourgraphics.ui;

import com.fourgraphics.gameobjects.*;
import com.fourgraphics.scenemanagement.*;

import processing.core.*;

/**
 * Un elemento generico dell’interfaccia grafica, ha il riferimento al suo oggetto e al suo transform(per comodità anche se si potrebbe ottenere dal gameObject), deve avere riferimenti a tutto ciò che lo definisce graficamente.
 */
public abstract class UIElement {

	/**
	 * L’oggetto a cui è attaccato l’elemento
	 */
	public GameObject gameObject;
	/**
	 * Posizione e dimensione dell’oggetto a cui è attaccato l’elemento
	 */
	public Transform transform;
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
	/**
	 * Riferimento all'applet di Processing
	 */
	protected PApplet sketch;
	protected boolean worldSpace;

	/**
	 * Costruttore generico per un elemento dell'interfaccia grafica con una texutre data tramite PImage
	 *
	 * @param text    Testo dell'elemento
	 * @param color   Colore default dell'elemento
	 * @param texture Texture dell'elemento (PImage)
	 */
	public UIElement(String text, int color, PImage texture,boolean space) {
		this.text = text;
		this.color = color;
		this.texture = texture;
		sketch = SceneManager.getApp();
		this.worldSpace=space;

	}

	/**
	 * Costruttore generico per un elemento dell'interfaccia grafica con una texutre data tramite il percorso del file
	 *
	 * @param text     Testo dell'elemento
	 * @param color    Colore default dell'elemento
	 * @param fileName Percorso del file della texture
	 */
	public UIElement(String text, int color, String fileName,boolean space) {
		this.text = text;
		this.color = color;
		sketch = SceneManager.getApp();
		texture = sketch.loadImage(fileName);
		this.space=space;
	}

	/**
	 * Mostra l'elemento dell'UI
	 */
	abstract public void display();


}

