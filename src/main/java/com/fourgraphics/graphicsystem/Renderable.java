
package com.fourgraphics.graphicsystem;
import com.fourgraphics.gameobjects.*;
import processing.core.PApplet;

/**
 * 
 */

/**
 * @author Dareisa, Furriolo, Iurcea
 *
 */
public abstract class Renderable {
	public GameObject gameObject;      
	public Transform transform; 
	protected PApplet sketch;
	
	abstract void render();



}


