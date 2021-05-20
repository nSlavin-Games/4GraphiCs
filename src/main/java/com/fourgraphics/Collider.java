package com.fourgraphics;

import processing.core.PApplet;

abstract public class Collider implements Cloneable {

	public GameObject gameObject;
	public Transform transform;
	protected PApplet sketch;
	Vector2 previousPosition; //WARNING: UNUSED
	private boolean dynamicObject;
	private boolean debug;

	//SceneManager.getApp()

	public Collider(boolean dynamicObject) {
		setDynamic(dynamicObject);
		sketch = SceneManager.getApp();
		previousPosition = new Vector2();
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

	/**
	 * @deprecated not finished
	 */
	@Deprecated
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}