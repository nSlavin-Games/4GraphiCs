package com.fourgraphics.scenemanagement;

import com.fourgraphics.colliders.*;
import com.fourgraphics.gameobjects.*;
import com.fourgraphics.graphicsystem.*;
import com.fourgraphics.ui.*;

import java.util.ArrayList;

/**
 * Il modello di una scena che gestisce l’esecuzione di tutti gli oggetti, renderizzando qualsiasi renderer attaccato, calcolando le collisioni, gestendo l’UI e la sua telecamera.
 */
public class SceneBlueprint {
    /**
     * La lista degli oggetti presenti nella scena
     */
    private ArrayList<GameObject> objectList = new ArrayList<>();
    /**
     * La lista di tuti gli script
     */
    private ArrayList<Script> scriptList = new ArrayList<>();
    /**
     * Tutti gli oggetti renderizzabili
     */
    private ArrayList<Renderable> renderableElements = new ArrayList<>();
    /**
     * Gli elementi dell’UI con cui si può interagire
     */
    private ArrayList<UIElement> uiElements = new ArrayList<>();
    /**
     * La lista dei collider dinamici
     */
    private ArrayList<Collider> dynamicColliders = new ArrayList<>();
    /**
     * La lista dei collider esistenti
     */
    private ArrayList<Collider> collidersList = new ArrayList<>();
    /**
     * La telecamera della scena
     */
    private Camera sceneCamera;
    /**
     * Indica se la scena è già stata inizializzata
     */
    private boolean isInitialized;

    /**
     * Se è il primo caricamento inizializza la scena con i vari GameObject, crea la telecamera, inizializza la lista degli script, oggetti renderizzabili, dell’ui e dei collider attaccati ai GameObject, specifica in una lista separata i collider dinamici e poi indica che è stato inizializzato, in qualsiasi caso dopo tutto quanto chiama il metodo Start
     *
     * @param objectList Lista degli oggetti da inizializzare
     */
    public void initialize(ArrayList<GameObject> objectList) {
        //TODO(samu): not implemented
    }

    /**
     * Eseguito al caricamento di una scena, deve eseguire lo start di tutti gli script attaccati
     */
    public void start() {
        //TODO(samu): not implemented
    }

    /**
     * Tutti i metodi ed elementi da eseguire ogni frame, deve eseguire l’update di tutti gli script attaccati
     */
    public void update() {
        //TODO(samu): not implemented
    }

    /**
     * Getter che ottiene un oggetto singolo dalla objectList tramite il suo ID
     *
     * @param objectID ID dell'oggetto
     * @return Restituisce l’oggetto equivalente all’id selezionato
     */
    GameObject getObject(int objectID) {
        //TODO(samu): not implemented
        return new GameObject();
    }

    /**
     * Getter che cerca nella lista e restituisce l’oggetto che corrisponde al nome specificato, se ci sono più oggetti con lo stesso nome restituisce il primo
     *
     * @param objectName Nome dell'oggetto da ottenere
     * @return Restituisce l’oggetto che corrisponde al nome specificato, se ci sono più oggetti con lo stesso nome restituisce il primo
     */
    GameObject getObject(String objectName) {
        //TODO(samu): not implemented
        return new GameObject();
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
        //TODO(samu): not implemented
    }

    /**
     * Disegna tutti gli elementi dell’UI
     */
    void renderUI() {
        //TODO(samu): not implemented
    }

    /**
     * Controlla per ogni oggetto dinamico le collisioni con gli altri collider, se c’è collisione chiama OnCollisionEnter per ogni script attaccato all’oggetto dinamico controllato, se il collider confrontato con l’oggetto dinamico è un collider statico allora va eseguito il calcolo della collisione con SNAP, altrimenti va eseguito il calcolo della collisione e basta
     */
    void calculateCollisions() {
        //TODO(samu): not implemented
    }
}
