package com.fourgraphics.scenemanagement;

import com.fourgraphics.gameobjects.*;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Il gestore di tutte le scene del gioco, esegue la scena attiva e gestisce la creazione e distruzione dinamica di oggetti  della scena
 */
public class SceneManager {
    /**
     * La lista delle scene esistenti
     */
    private static ArrayList<SceneBlueprint> sceneList;
    /**
     *  L’index che indica quale elemento della lista delle scene deve essere la scena attiva
     */
    private static int activeSceneIndex;

    /**
     * Il riferimento all'app principale del gioco
     */
    private static PApplet mainApp;

    /**
     * La durata dell'ultimo frame in secondi, utilizzato per fare gli adattamenti al framerate
     */
    private static float deltaTime;
    /**
     * Il valore del tempo di esecuzione dell'app che viene utilizzato per calcolare il deltaTime
     */
    private static float time;

    /**
     * Inizializza la lista delle scene e l’index della scena attiva
     */
    public static void initialize(PApplet app) {
        mainApp = app;
        sceneList = new ArrayList<>();
    }

    /**
     * Esegue la scena attualmente selezionata
     */
    public static void executeScene() {
        //Eseguo come prima cosa il calcolo del deltaTime, ovvero quanto è durato l'ultimo frame
        float lastTime = time; //salvo quand'è cominciata l'ultima escuzione
        time = mainApp.millis(); //segno quand'è cominciata questa esecuzione
        //calcolo il deltaTime facendo la differenza fra i due tempi così so quanto tempo è durato il frame in millisecondi
        //poi lo divido per 1000 per averlo in secondi
        deltaTime = (time-lastTime)/1000f;
        loadScene(getActiveSceneIndex());
    }

    /**
     * Carica la scena con l’index specificato
     * @param index Index della scena da caricare
     */
    public static void loadScene(int index) {
        sceneList.get(index).initialize();
    }

    /**
     * Aggiunge la scena specificata alla lista delle scene esistenti
     * @param blueprint Scena da aggiungere
     */
    public static void addScene(SceneBlueprint blueprint) {
        sceneList.add(blueprint);
    }

    /**
     * Getter per activeScene
     * @return Restituisce la scena attiva
     */
    public static SceneBlueprint getActiveScene() {
        return sceneList.get(activeSceneIndex);
    }

    /**
     * Getter per activeSceneIndex
     * @return Restituisce l’index della scena attiva
     */
    public static int getActiveSceneIndex() {
        return activeSceneIndex;
    }

    /**
     * Aggiunge un oggetto alla scena attualmente attiva
     * @param object Oggetto da aggiungere
     */
    public static void instantiate(GameObject object) {
        sceneList.get(activeSceneIndex).addObject(object);
    }

    /**
     * Rimuove un oggetto dalla scena attualmente attiva
     * @param object Oggetto da rimuovere
     */
    public static void destroy(GameObject object) {
        sceneList.get(activeSceneIndex).removeObject(object);
    }

    /**
     * Getter per l'app principale
     * @return l'app principale
     */
    public static PApplet getApp() {
        return mainApp;
    }

    /**
     * Getter per il deltaTime
     * @return il valore del deltaTime
     */
    public static float deltaTime() {
        return deltaTime;
    }
}
