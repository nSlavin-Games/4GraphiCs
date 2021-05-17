package com.fourgraphics;

import processing.core.PApplet;

/**
 * Gestione della telecamera di gioco e impostazione di un sistema per seguire
 * un target(per esempio un giocatore)
 */
/**
 * @author ChristianElizabeth
 *
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

	/**
	 * Costruttore
	 */
	Camera() {
		sketch = SceneManager.getApp();
		position = new Vector2();
		offset = new Vector2();
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

	/**
	 * Il metodo adatta la posizione con l'offset del nuovo vettore
	 * 
	 * @return newVector
	 */
	public Vector2 getOffsetPosition() {
		Vector2 newVector = new Vector2();
		newVector.sum(position); // somma la posizione al corrispettivo vettore
		newVector.sum(offset); // somma l'offset al corrispettivo vettore
		return newVector; // restituzione del vector aggiornato
	}

	/**
	 * Il metodo modifica la posizione della telecamera:
	 * se la telecamera contiene un target, la posizione corrisponde a quella del target,
	 * altrimenti rimane ferma
	 */
	protected void calculateCamera()
	{
		if (target != null)
		{ //se si ha un target
			setPosition(target.transform.getPosition()); //impostazione della posizione della telecamera, prendendo quella del target
		}
		sketch.camera(getOffsetPosition().getX(), getOffsetPosition().getY(),
				(sketch.height / 2) / sketch.tan(sketch.PI * 30 / 180), getOffsetPosition().getX(),
				getOffsetPosition().getY(), 0, 0, 1, 0); //creazione telecamera con i metodi di processing
	}
}
