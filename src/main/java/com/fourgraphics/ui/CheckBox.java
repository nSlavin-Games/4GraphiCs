package com.fourgraphics.ui;

import processing.core.*;

public class CheckBox extends UIElement {
	static PImage defaultCheck;
	private PImage fillTexture;
	private PImage checkIcon;
	private int fillColor;
	private boolean checked;

	public CheckBox(String text, int color, PImage texture) {
		super(text, color, texture);	

	}

	public CheckBox(String text,int color,String fileName) {
		super(text,color,fileName);

	}
	@Override
	public void display() {
		if(texture==null || fillTexture==null) {
			if(!isChecked())
				sketch.fill(color);
			else
				//				sketch.fill(fillColor);
				sketch.noStroke();
			sketch.rectMode(sketch.CORNER);
			sketch.rect(transform.getPosition().getX()-transform.getScale().getX()/2,transform.getPosition().getY()-transform.getScale().getY()/2,transform.getScale().getY(), transform.getScale().getY());


		}
		if(!isChecked()) {
			sketch.imageMode(sketch.CENTER);
			sketch.image(texture, transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
		}else {
			sketch.imageMode(sketch.CENTER);
			sketch.image(fillTexture, transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
		}
		sketch.textMode(sketch.CORNER);
		sketch.text(text, transform.getPosition().getX()+transform.getScale().getY(),transform.getPosition().getY(),transform.getScale().getX(),transform.getScale().getY());

	}
	public boolean mouseOver() {
		if (sketch.mouseX > transform.getPosition().getX() - transform.getScale().getX() / 2 && sketch.mouseX < transform.getPosition().getX() + transform.getScale().getX() / 2 && sketch.mouseY > transform.getPosition().getY() - transform.getScale().getY() / 2 && sketch.mouseY < transform.getPosition().getY() + transform.getScale().getY() / 2)
			return true;

		return false;
	}
	public void toggleChecked() {
		if(checked) 
			checked=false;
		else
			checked=true;

	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
