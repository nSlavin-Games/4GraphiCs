package com.fourgraphics.ui;

import processing.core.PImage;

public class Button extends UIElement{

	public Button(String text, int color, PImage texture) {
		super(text, color, texture);	
	}

	public Button (String text,int color,String fileName) {
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

	public boolean mouseOver() {


		if(sketch.mouseX>transform.getPosition().getX()-transform.getScale().getX()/2 &&sketch.mouseX<transform.getPosition().getX()+transform.getScale().getX()/2 && sketch.mouseY>transform.getPosition().getY()-transform.getScale().getY()/2&&sketch.mouseY<transform.getPosition().getY()+transform.getScale().getY()/2)
			return true;

		return false;
	}
}
