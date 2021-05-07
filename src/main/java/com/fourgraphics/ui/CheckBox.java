package com.fourgraphics.ui;

import com.fourgraphics.gameobjects.Vector2;
import com.fourgraphics.scenemanagement.SceneManager;

import processing.core.*;

public class CheckBox extends UIElement {
	static PImage defaultCheck;
	private PImage fillTexture;
	private PImage checkIcon;
	private int fillColor;
	private boolean checked;

	public CheckBox(String text, int color, PImage texture,PImage defaultCheck,String fillTexture,String fillTextureIcon,boolean space) {
		super(text, color, texture,space);	
		this.defaultCheck=defaultCheck;
		this.fillTexture =sketch.loadImage(fillTexture);
		this.checkIcon= sketch.loadImage(fillTextureIcon);
	}

	public CheckBox(String text,int color,String fileName,PImage defaultCheck,PImage fillTexture,PImage checkIcon,boolean space) {
		super(text,color,fileName,space);
		this.defaultCheck=defaultCheck;
		this.fillTexture=fillTexture;
		this.checkIcon=checkIcon;
	}
	@Override
	public void display() {
		if(!worldSpace) {
			Vector2 cameraPosition = SceneManager.getActiveScene().getCamera().getOffsetPosition();
			sketch.noStroke();
			sketch.rectMode(sketch.CENTER);
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
