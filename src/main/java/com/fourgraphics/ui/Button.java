package com.fourgraphics.ui;

import com.fourgraphics.gameobjects.Vector2;
import com.fourgraphics.scenemanagement.SceneManager;

import processing.core.PImage;

/**
 * Elemento UI semplice che sovrascrive il display e indica un modo per capire se il mouse si trova sopra al bottone.
 */
public class Button extends UIElement {

	/**
	 * Costruttore per la classe Button che accetta una PImage come texutre
	 *
	 * @param text    Testo del bottone
	 * @param color   Colore default del bottone
	 * @param texture Texture del bottone (PImage)
	 */
	public Button(String text, int color, PImage texture,boolean space) {
		super(text, color, texture,space);
	}

	/**
	 * Costruttore per la classe Button che accetta il percorso del file come texutre
	 *
	 * @param text     Testo del bottone
	 * @param color    Colore default del bottone
	 * @param fileName Percorso del file della texture
	 */
	public Button(String text, int color, String fileName,boolean space) {
		super(text, color, fileName,space);
	}


	/**
	 * Mostra il bottone
	 */
	public void display() {
		//world space, i blocchi, man mano che ti sposti scompaiono
		if(!worldSpace) {
			Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
			sketch.pushMatrix();	
			sketch.translate(cameraPosition.getX(), cameraPosition.getY());
		}
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
			if(!worldSpace)
				sketch.popMatrix();
	}
	
	/**
	 * Indica se il mouse è sopra il bottone
	 *
	 * @return Se il bottone è in evidenza (mouse sopra il bottone) restituisce true, altrimenti restituisce false
	 */
	public boolean mouseOver() {


		if (sketch.mouseX > transform.getPosition().getX() - transform.getScale().getX() / 2 && sketch.mouseX < transform.getPosition().getX() + transform.getScale().getX() / 2 && sketch.mouseY > transform.getPosition().getY() - transform.getScale().getY() / 2 && sketch.mouseY < transform.getPosition().getY() + transform.getScale().getY() / 2)
			return true;

		return false;
	}
}
