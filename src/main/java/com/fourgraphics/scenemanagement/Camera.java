package com.fourgraphics.scenemanagement;

import com.fourgraphics.gameobjects.GameObject;
import com.fourgraphics.gameobjects.Vector2;

import processing.core.PApplet;

/**
 * Gestione della telecamera di gioco e impostazione di un sistema per seguire un target(per esempio un giocatore)
 */
public class Camera {
    /**
     * Il target da seguire, se non c’è un target allora rimane ferma
     */
    private GameObject target;
    /**
     * La posizione della telecamera
     */
    private Vector2 position;
    /**
     * L’offset della telecamera dalla sua posizione
     */
    private Vector2 offset;
    
    private PApplet sketch;
    
    Camera() {
    	sketch = SceneManager.getApp();
    }

    /**
     * Getter per target
     *
     * @return Restituisce l'oggetto che la camera deve seguire/alla quale è legata
     */
    public GameObject getTarget() {
        return target;
    }

    /**
     * @return Restituisce la posizione della camera
     */
    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public void setOffset(Vector2 offset) {
        this.offset.set(offset);
    }
    
    public Vector2 getOffsetPosition() {
    	Vector2 newVector = new Vector2();
    	newVector.sum(position);
    	newVector.sum(offset);
    	return newVector;
    }

    public void calculateCamera() {
        if (target != null) {
        	setPosition(target.transform.getPosition());
        }
        sketch.camera(getOffsetPosition().getX(), getOffsetPosition().getY(), (sketch.height/2) / sketch.tan(sketch.PI*30 / 180), getOffsetPosition().getX(), getOffsetPosition().getY(), 0, 0, 1, 0);
    }
}
