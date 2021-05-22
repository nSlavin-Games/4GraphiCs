package com.fourgraphics;

import processing.core.PImage;

public class CheckBox extends UIElement {
	static PImage defaultCheck;
	private PImage fillTexture;
	private PImage checkIcon;
	private int fillColor;
	private boolean checked;

	public CheckBox(String text, int color, int fillColor, PImage checkIcon, boolean isWorldSpace) {
		super(text, color, isWorldSpace);
		this.fillColor = fillColor;
		this.checkIcon = checkIcon;
		defaultCheck = sketch.loadImage("../resources/defaultCheck.png");
	}

	public CheckBox(String text, int color, PImage texture, PImage fillTexture, PImage checkIcon, boolean isWorldSpace) {
		super(text, texture, isWorldSpace);
		this.fillTexture = fillTexture;
		this.checkIcon = checkIcon;
	}

	@Override
	protected void display() {
		if (!worldSpace) {
			Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
			sketch.noStroke();

			sketch.pushMatrix();	
			sketch.translate(cameraPosition.getX(), cameraPosition.getY());
			
		}
			if(texture==null || fillTexture==null) {
				if(!isChecked())
					sketch.fill(color);
				else
					sketch.fill(fillColor);
				sketch.noStroke();
				sketch.rectMode(sketch.CORNER);
				sketch.rect(transform.getPosition().getX()-transform.getScale().getX()/2,transform.getPosition().getY()-transform.getScale().getY()/2,transform.getScale().getY(), transform.getScale().getY());
			}
			if(!isChecked()) {
				sketch.imageMode(sketch.CENTER);
				sketch.image(texture, transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
			}else {
				sketch.imageMode(sketch.CENTER);
				if(checkIcon!=null)
					sketch.image(checkIcon, transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
				else
					sketch.image(defaultCheck, transform.getPosition().getX(),  transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
			}
			sketch.textMode(sketch.CORNER);
			sketch.text(text, transform.getPosition().getX()+transform.getScale().getY(),transform.getPosition().getY(),transform.getScale().getX(),transform.getScale().getY());
			if(!worldSpace)
				sketch.popMatrix();
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

	@Override
	public CheckBox clone() {
		try {
			return new CheckBox(this.text, this.color, (PImage) this.texture.clone(), (PImage) this.fillTexture.clone(), (PImage) this.checkIcon.clone(), this.worldSpace);
		} catch (Exception ignored) {    //NOTE(sv-molinari): kinda cursed ngl
			try {
				return new CheckBox(this.text, this.color, this.fillColor, (PImage) this.checkIcon.clone(), this.worldSpace);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
