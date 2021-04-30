package com.fourgraphics.scenemanagement;

import com.fourgraphics.colliders.*;
import com.fourgraphics.gameobjects.*;
import com.fourgraphics.graphicsystem.*;
import com.fourgraphics.ui.*;

import java.util.ArrayList;

/**
 * Il modello di una scena che gestisce l’esecuzione di tutti gli oggetti,
 * renderizzando qualsiasi renderer attaccato, calcolando le collisioni,
 * gestendo l’UI e la sua telecamera.
 */
public class SceneBlueprint {
	/**
	 * La lista degli oggetti presenti nella scena
	 */
	private ArrayList<GameObject> objectList = new ArrayList<>();
	/**
	 * La lista di tuti gli script
	 */
	private ArrayList<Script> scriptList;
	/**
	 * Tutti gli oggetti renderizzabili
	 */
	private ArrayList<Renderable> renderableElements;
	/**
	 * Gli elementi dell’UI con cui si può interagire
	 */
	private ArrayList<UIElement> uiElements;
	/**
	 * La lista dei collider dinamici
	 */
	private ArrayList<Collider> dynamicColliders;
	/**
	 * La lista dei collider esistenti
	 */
	private ArrayList<Collider> collidersList;
	/**
	 * La telecamera della scena
	 */
	private Camera sceneCamera;
	/**
	 * Indica se la scena è già stata inizializzata
	 */
	private boolean isInitialized;

	/**
	 * Se è il primo caricamento inizializza la scena con i vari GameObject, crea
	 * la telecamera, inizializza la lista degli script, oggetti renderizzabili,
	 * dell’ui e dei collider attaccati ai GameObject, specifica in una lista
	 * separata i collider dinamici e poi indica che è stato inizializzato, in
	 * qualsiasi caso dopo tutto quanto chiama il metodo Start
	 *
	 * @param objectList Lista degli oggetti da inizializzare
	 */
	public void initialize() {
		if (isInitialized == false) {
			sceneCamera = new Camera();
			scriptList = new ArrayList<>();
			renderableElements = new ArrayList<>();
			uiElements = new ArrayList<>();
			dynamicColliders = new ArrayList<>();
			collidersList = new ArrayList<>();
			for (GameObject o : objectList) {
				initializeObject(o);
			}
		}
		isInitialized = true;
		start();
	}

	/**
	 * Eseguito al caricamento di una scena, deve eseguire lo start di tutti gli
	 * script attaccati
	 */
	public void start() {
		if (isInitialized == true) {
			for (Script s : scriptList) {
				s.Start();
			}
		}
	}

	/**
	 * Tutti i metodi ed elementi da eseguire ogni frame, deve eseguire l’update
	 * di tutti gli script attaccati
	 */
	public void update() {
		for (Script s : scriptList) {
			s.Update();
		}
	}

	/**
	 * Getter che ottiene un oggetto singolo dalla objectList tramite il suo ID
	 *
	 * @param objectID ID dell'oggetto
	 * @return Restituisce l’oggetto equivalente all’id selezionato
	 */
	GameObject getObject(int objectID) {
		return objectList.get(objectID);
	}

	/**
	 * Getter che cerca nella lista e restituisce l’oggetto che corrisponde al
	 * nome specificato, se ci sono più oggetti con lo stesso nome restituisce il
	 * primo
	 *
	 * @param objectName Nome dell'oggetto da ottenere
	 * @return Restituisce l’oggetto che corrisponde al nome specificato, se ci
	 *         sono più oggetti con lo stesso nome restituisce il primo
	 */
	GameObject getObject(String objectName) {
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).getName().equals(objectName)) {
				return objectList.get(i);
			}
		}
		return null;
	}

	/**
	 * Getter per objectList
	 *
	 * @return Restituisce la lista degli oggetti
	 */
	ArrayList<GameObject> getObjectList() {
		return objectList;
	}

	/**
	 * Getter per UIElements
	 *
	 * @return Resituisce la lista degli elementi dell’UI
	 */
	ArrayList<UIElement> getUiElements() {
		return uiElements;
	}

	/**
	 * Getter per sceneCamera
	 *
	 * @return Restituisce la telecamera della scena
	 */
	Camera getCamera() {
		return sceneCamera;
	}

	/**
	 * Esegue tutti i render necessari, fra renderer e animator
	 */
	void renderObjects() {
		for (Renderable r : renderableElements) {
			r.render();
		}
	}

	/**
	 * Disegna tutti gli elementi dell’UI
	 */
	void renderUI() {
		for (UIElement uiE : uiElements) {
			uiE.display();
		}
	}

	/**
	 * Controlla per ogni oggetto dinamico le collisioni con gli altri collider, se
	 * c’è collisione chiama OnCollisionEnter per ogni script attaccato
	 * all’oggetto dinamico controllato, se il collider confrontato con
	 * l’oggetto dinamico è un collider statico allora va eseguito il calcolo
	 * della collisione con SNAP, altrimenti va eseguito il calcolo della collisione
	 * e basta
	 */
	void calculateCollisions() {
		for (Collider c : dynamicColliders) { // primo collider
			for (Collider c2 : dynamicColliders) { // secondo collider (dinamico)
				if (c.checkCollision(c2) != CollisionDirection.NONE && !c.equals(c2)) { // se collide con un collider
																						// dinamico diverso da se stesso
					c.gameObject.getComponent(Script.class).onCollisionEnter(c, c2); // esegue lo script alla collisione
				}
			}
			for (Collider c2 : collidersList) { // secondo collider (statico)
				if (c.checkCollisionSnap(c2) != CollisionDirection.NONE) { // se collide con un collider statico
					c.gameObject.getComponent(Script.class).onCollisionEnter(c, c2); // esegue lo script alla collisione
				}
			}
		}
	}

	void setObjectList(ArrayList<GameObject> objectList) {
		this.objectList = objectList;
	}

	void addObject(GameObject object) {
		objectList.add(object);
		initializeObject(object);
	}

	void removeObject(GameObject object) {
		objectList.remove(object);
		deinitializeObject(object);
	}

	void initializeObject(GameObject object) {
		ArrayList<Object> obj = object.getComponents();
		for (Object o : obj) {
			if (o instanceof Script) {
				scriptList.add((Script) o);
			} else if (o instanceof Renderable) {
				renderableElements.add((Renderable) o);
			} else if (o instanceof UIElement) {
				uiElements.add((UIElement) o);
			} else if (o instanceof Collider) {
				collidersList.add((Collider) o);
				if (((Collider) o).dynamicObject) {
					dynamicColliders.add((Collider) o);
				}
			}
		}
	}

	void deinitializeObject(GameObject object) {
		ArrayList<Object> obj = object.getComponents();
		for (Object o : obj) {
			if (o instanceof Script) {
				scriptList.remove((Script) o);
			} else if (o instanceof Renderable) {
				renderableElements.remove((Renderable) o);
			} else if (o instanceof UIElement) {
				uiElements.remove((UIElement) o);
			} else if (o instanceof Collider) {
				collidersList.remove((Collider) o);
				if (((Collider) o).dynamicObject) {
					dynamicColliders.remove((Collider) o);
				}
			}
		}
	}
}
