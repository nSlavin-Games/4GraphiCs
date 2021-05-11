package com.fourgraphics.scenemanagement;

import com.fourgraphics.colliders.*;
import com.fourgraphics.gameobjects.*;
import com.fourgraphics.graphicsystem.*;
import com.fourgraphics.ui.*;

import java.util.ArrayList;

/**
 * Il modello di una scena che gestisce lâ€™esecuzione di tutti gli oggetti,
 * renderizzando qualsiasi renderer attaccato, calcolando le collisioni,
 * gestendo lâ€™UI e la sua telecamera.
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
	 * Gli elementi dellâ€™UI con cui si puÃ² interagire
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
	 * Indica se la scena Ã¨ giÃ  stata inizializzata
	 */
	private boolean isInitialized;

	/**
	 * Se Ã¨ il primo caricamento inizializza la scena con i vari GameObject, crea
	 * la telecamera, inizializza la lista degli script, oggetti renderizzabili,
	 * dellâ€™ui e dei collider attaccati ai GameObject, specifica in una lista
	 * separata i collider dinamici e poi indica che Ã¨ stato inizializzato, in
	 * qualsiasi caso dopo tutto quanto chiama il metodo Start
	 *
	 * @param objectList Lista degli oggetti da inizializzare
	 */
	public void initialize() {
		if (isInitialized == false) { //se la scena non è ancora stata inizializzata
			//assegnazione di tutti i GameObject
			sceneCamera = new Camera();
			scriptList = new ArrayList<>();
			renderableElements = new ArrayList<>();
			uiElements = new ArrayList<>();
			dynamicColliders = new ArrayList<>();
			collidersList = new ArrayList<>();
			for (GameObject o : objectList) { //per ogni oggetto di tipo GameObject nella lista di objectList
				initializeObject(o); //inizializzazione di ogni oggetto di tipo GameObject
			}
		}
		isInitialized = true; //scena già inizializzata precedentemente
		start(); //caricamento della scena
	}

	/**
	 * Eseguito al caricamento di una scena, deve eseguire lo start di tutti gli
	 * script attaccati
	 */
	public void start() {
		if (isInitialized == true) { //scena già inizializzata
			for (Script s : scriptList) { //per ogni script della lista scriptList
				s.Start(); //inizializzazione di tutti gli script della scena
			}
		}
	}

	/**
	 * Tutti i metodi ed elementi da eseguire ogni frame, deve eseguire lâ€™update
	 * di tutti gli script attaccati
	 */
	public void update() {
		for (Script s : scriptList) { //per ogni script della lista scriptList
			s.Update(); //esecuzione dell'aggiornamento degli script
		}
		calculateCollisions(); //controllo le collisioni tra i collider
		renderObjects(); //esecuzione di tutti i render
		renderUI(); //disegna tutti gli elementi dell'UI
		sceneCamera.calculateCamera(); //impostazione visuale della telecamera
	}

	/**
	 * Getter che ottiene un oggetto singolo dalla objectList tramite il suo ID
	 *
	 * @param objectID ID dell'oggetto
	 * @return Restituisce lâ€™oggetto equivalente allâ€™id selezionato
	 */
	GameObject getObject(int objectID) {
		return objectList.get(objectID); //restituzione dell'oggetto contenuto in objectList con ID corrispondente al parametro
	}

	/**
	 * Getter che cerca nella lista e restituisce lâ€™oggetto che corrisponde al
	 * nome specificato, se ci sono piÃ¹ oggetti con lo stesso nome restituisce il
	 * primo
	 *
	 * @param objectName Nome dell'oggetto da ottenere
	 * @return Restituisce lâ€™oggetto che corrisponde al nome specificato, se ci
	 *         sono piÃ¹ oggetti con lo stesso nome restituisce il primo
	 */
	GameObject getObject(String objectName) {
		for (int i = 0; i < objectList.size(); i++) { //analisi di ogni oggetto nella lista di objectList
			if (objectList.get(i).getName().equals(objectName)) { //confronto del nome dell'oggetto corrente con quello dell'oggetto passato come parametro
				return objectList.get(i); //restituzione dell'oggetto nella posizione corrente (i)
			}
		}
		return null; //restituzione di default
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
	 * @return Resituisce la lista degli elementi dellâ€™UI
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
		for (Renderable r : renderableElements) { //per ogni oggetto di tipo Renderable della lista renderableElements
			r.render(); //esecuzione animazioni degli oggetti Renderable
		}
	}

	/**
	 * Disegna tutti gli elementi dellâ€™UI
	 */
	void renderUI() {
		for (UIElement uiE : uiElements) { //per ogni oggetto di tipo UIElement della lista uiElements
			uiE.display(); //rappresentazione grafica degli oggetti dell'UI
		}
	}

	/**
	 * Controlla per ogni oggetto dinamico le collisioni con gli altri collider, se
	 * câ€™Ã¨ collisione chiama OnCollisionEnter per ogni script attaccato
	 * allâ€™oggetto dinamico controllato, se il collider confrontato con
	 * lâ€™oggetto dinamico Ã¨ un collider statico allora va eseguito il calcolo
	 * della collisione con SNAP, altrimenti va eseguito il calcolo della collisione
	 * e basta
	 */
	void calculateCollisions() {
		for (Collider c : dynamicColliders) { //primo collider
			for (Collider c2 : collidersList) { //secondo collider (statico)
				if (c2.isDynamic()) { //se il secondo collider è dinamico
					if (c.checkCollision(c2) != CollisionDirection.NONE && !c.equals(c2)) { //se c'è collisione tra i due collider e questi sono diversi tra loro
						for (Object o : c.gameObject.getComponents()) { //per ogni oggetto di tipo Object della lista dei componenti del collider
							if (o instanceof Script) { //se l'oggetto è di tipo Script
								((Script) o).OnCollisionEnter(c, c2); //casting dell'oggetto da Object a Script e collisione tra i due collider
							}
						}
					}
				} else { //altrimenti se il collider è statico
					if (c.checkCollisionSnap(c2) != CollisionDirection.NONE) { //se c'è collisione tra i due collider, di cui uno statico
						for (Object o : c.gameObject.getComponents()) { //per ogni oggetto di tipo Object della lista dei componenti del collider
							if (o instanceof Script) { //se l'oggetto è di tipo Script
								((Script) o).OnCollisionEnter(c, c2); //casting dell'oggetto da Object a Script e collisione tra i due collider
							}
						}
					}
				}
			}
		}
	}

	void setObjectList(ArrayList<GameObject> objectList) {
		this.objectList = objectList;
	}

	void addObject(GameObject object) {
		objectList.add(object); //inserimento alla lista objectList dell'oggetto passato come parametro
		initializeObject(object); //inizializzazione oggetto
	}

	void removeObject(GameObject object) {
		objectList.remove(object); //rimozione dalla lista objectList dell'oggetto passato come parametro
		deinitializeObject(object); //deinizializzazione oggetto
	}

	void initializeObject(GameObject object) {
		ArrayList<Object> obj = object.getComponents(); //assegnazione dei componenti dell'oggetto passato come parametro all'arrayList di tipo Object (obj)
		for (Object o : obj) { //per ogni oggetto di tipo Object della lista obj
			if (o instanceof Script) { //se l'oggetto è uno script
				scriptList.add((Script) o); //casting da Object a Script e inserimento dell'oggetto alla lista per gli script
			} else if (o instanceof Renderable) { //se l'oggetto è un render
				renderableElements.add((Renderable) o); //casting da Object a Renderable e inserimento dell'oggetto alla lista per i render
			} else if (o instanceof UIElement) { //se l'oggetto è un elemento UI
				uiElements.add((UIElement) o); //casting da Object a UIElement e inserimento dell'oggetto alla lista per gli elementi dell'UI
			} else if (o instanceof Collider) { //se l'oggetto è un collider
				collidersList.add((Collider) o); //casting da Object a Collider e inserimento dell'oggetto alla lista per i collider
				if (((Collider) o).dynamicObject) { //casting in Collider e controllo se l'oggetto è in movimento (quindi è dinamico)
					dynamicColliders.add((Collider) o); //casting dell'oggetto in Collider e inserimento alla lista per i collider dinamici
				}
			}
		}
	}

	void deinitializeObject(GameObject object) {
		ArrayList<Object> obj = object.getComponents(); //assegnazione dei componenti dell'oggetto passato come parametro all'arrayList di tipo Object (obj)
		for (Object o : obj) { //per ogni oggetto di tipo Object della lista obj
			if (o instanceof Script) { //se l'oggetto è uno script
				scriptList.remove((Script) o); //casting da Object a Script e rimozione dell'oggetto alla lista per gli script
			} else if (o instanceof Renderable) { //se l'oggetto è un render
				renderableElements.remove((Renderable) o); //casting da Object a Renderable e rimozione dell'oggetto alla lista per i render
			} else if (o instanceof UIElement) { //se l'oggetto è un elemento UI
				uiElements.remove((UIElement) o); //casting da Object a UIElement e rimozione dell'oggetto alla lista per gli elementi dell'UI
			} else if (o instanceof Collider) { //se l'oggetto è un collider
				collidersList.remove((Collider) o); //casting da Object a Collider e rimozione dell'oggetto alla lista per i collider
				if (((Collider) o).dynamicObject) { //casting in Collider e controllo se l'oggetto è in movimento (quindi è dinamico)
					dynamicColliders.remove((Collider) o); //casting dell'oggetto in Collider e rimozione alla lista per i collider dinamici
				}
			}
		}
	}
}
