package com.fourgraphics;

import processing.core.PApplet;

abstract public class Collider {
	
	public GameObject gameObject;
	Vector2 previousPosition; //WARNING: UNUSED
	public Transform  transform;
	private boolean dynamicObject;
	private boolean debug;
	protected PApplet sketch;
	
	//SceneManager.getApp()
	
	public Collider(boolean dynamicObject) {
		
		setDynamic(dynamicObject);
		sketch=SceneManager.getApp();
	}
	
	
	abstract protected CollisionDirection checkCollision(Collider other);
	
	abstract protected CollisionDirection checkCollisionSnap(Collider other);


	public boolean isDynamic() {
		return dynamicObject;
	}


	public void setDynamic(boolean dynamicObject) {
		this.dynamicObject = dynamicObject;
	}


	public boolean isDebug() {
		return debug;
	}


	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}