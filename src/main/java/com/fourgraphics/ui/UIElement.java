package com.fourgraphics.ui;

import com.fourgraphics.gameobjects.*;
import com.fourgraphics.scenemanagement.*;

import processing.core.*;

public abstract class UIElement {
	
	protected GameObject gameObject;
	protected Transform transform; 
	protected String text;
	protected int color;
	protected PImage texture;
	protected PApplet sketch;
	
	
	public UIElement(String text,int color,PImage texture) {
		this.text=text;
		this.color=color;
		this.texture=texture;
		sketch=SceneManager.getApp();
		
	}
	
	public UIElement(String text,int color,String fileName) {
		this.text=text;
		this.color=color;
		sketch= SceneManager.getApp();
		texture= sketch.loadImage(fileName);	
	}
	
	abstract public void display();
	
	
}

