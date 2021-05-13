package com.fourgraphics.colliders;

import com.fourgraphics.gameobjects.GameObject;
import com.fourgraphics.gameobjects.Vector2;
import com.fourgraphics.scenemanagement.SceneManager;

import processing.core.PApplet;

import com.fourgraphics.gameobjects.Transform;

abstract public class Collider {
	
	public GameObject gameObject;
	Vector2 previousPosition;
	public Transform  transform;
	private boolean dynamicObject;
	private boolean debug;
	protected PApplet sketch;
	
	//SceneManager.getApp()
	
	public Collider(boolean dynamicObject) {
		
		setDynamic(dynamicObject);
		sketch=SceneManager.getApp();
	}
	
	public Collider(boolean dynamicObject, PApplet sketch) {
		
		setDynamic(dynamicObject);
		this.sketch=sketch;
	}
	
	
	abstract CollisionDirection CheckCollision(Collider other);
	
	abstract CollisionDirection CheckCollisionSnap(Collider other);


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