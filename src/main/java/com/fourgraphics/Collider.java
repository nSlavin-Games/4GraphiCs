package com.fourgraphics;

import processing.core.PApplet;

abstract public class Collider extends Component {

	Vector2 previousPosition; //WARNING: UNUSED
	private boolean dynamicObject;
	private boolean debug;
	protected boolean ignoreSnap;

	//SceneManager.getApp()

	public Collider(boolean dynamicObject, boolean ignoreSnap) {
		setDynamic(dynamicObject);
		this.ignoreSnap = ignoreSnap;
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

    public abstract Collider clone();

	protected abstract void debugDisplay();
}