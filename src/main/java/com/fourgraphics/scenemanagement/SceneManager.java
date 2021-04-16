package com.fourgraphics.scenemanagement;

import com.fourgraphics.gameobjects.GameObject;

import java.util.ArrayList;

/**
 * Il gestore di tutte le scene del gioco, esegue la scena attiva e gestisce la creazione e distruzione dinamica di oggetti  della scena
 */
public class SceneManager {
    /**
     * La lista delle scene esistenti
     */
    static ArrayList<SceneBlueprint> sceneList = new ArrayList<>();
    /**
     *  L’index che indica quale elemento della lista delle scene deve essere la lista attiva
     */
    static int activeSceneIndex;


    /**
     * Inizializza la lista delle scene e l’index della scena attiva
     */
    static void initialize() {
        //TODO(samu): not implemented
    }

    /**
     * Esegue la scena attualmente selezionata
     */
    static void executeScene() {
        //TODO(samu): not implemented
    }

    /**
     * Carica la scena con l’index specificato
     * @param index Index della scena da caricare
     */
    static void loadScene(int index) {
        //TODO(samu): not implemented
    }

    /**
     * Aggiunge la scena specificata alla lista delle scene esistenti
     * @param blueprint Scena da aggiungere
     */
    static void addScene(SceneBlueprint blueprint) {
        //TODO(samu): not implemented
    }

    /**
     * Getter per activeScene
     * @return Restituisce la scena attiva
     */
    static SceneBlueprint getActiveScene() {
        return sceneList.get(activeSceneIndex);
    }

    /**
     * Getter per activeSceneIndex
     * @return Restituisce l’index della scena attiva
     */
    static int getActiveSceneIndex() {
        return activeSceneIndex;
    }

    /**
     * Aggiunge un oggetto alla scena attualmente attiva
     * @param object Oggetto da aggiungere
     */
    static void instantiate(GameObject object) {
        //TODO(samu): not implemented
    }

    /**
     * Rimuove un oggetto dalla scena attualmente attiva
     * @param object Oggetto da rimuovere
     */
    static void destroy(GameObject object) {
        //TODO(samu): not implemented
    }
}
