package com.fourgraphics.ui;

import com.fourgraphics.gameobjects.GameObject;

import processing.core.PImage;

public class Panel extends UIElement{
	
	public Panel(String text, int color, PImage texture) {
		super(text, color, texture);	
	}

	public Panel(String text,int color,String fileName) {
		super(text,color,fileName);
	}


	public void display() {	
		sketch.noStroke();	
		sketch.rectMode(sketch.CENTER);	
		if(texture==null) {
			sketch.fill(color);
			sketch.rect(transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
		}else {
			sketch.imageMode(sketch.CENTER);
			sketch.image(texture, transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
		}
		sketch.fill(0);
		sketch.textAlign(sketch.CENTER,sketch.CENTER);
		sketch.textSize(20);
		sketch.text(text, transform.getPosition().getX(), transform.getPosition().getY(),transform.getScale().getX(), transform.getScale().getY());
	}
}
