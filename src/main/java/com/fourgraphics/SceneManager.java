package com.fourgraphics;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Il gestore di tutte le scene del gioco, esegue la scena attiva e gestisce la
 * creazione e distruzione dinamica di oggetti della scena
 */
public class SceneManager
{
    /**
     * La lista delle scene esistenti
     */
    private static ArrayList<SceneBlueprint> sceneList;

    /**
     * AGGIUNTA - FURFARO
     * Riferimento all'input manager
     */
    private static InputManager inputManager;

    /**
     * L’index che indica quale elemento della lista delle scene deve essere la
     * scena attiva
     */
    private static int activeSceneIndex;

    /**
     * Il riferimento all'app principale del gioco
     */
    private static PApplet mainApp;

    /**
     * La durata dell'ultimo frame in secondi, utilizzato per fare gli adattamenti
     * al framerate
     */
    private static float deltaTime;
    /**
     * Il valore del tempo di esecuzione dell'app che viene utilizzato per calcolare
     * il deltaTime
     */
    private static float time;

    private static float skipInitialFrames;

    /**
     * Il valore del fixed timestep, ovvero ogni quanto vengono eseguiti i metodi dato un tempo predefinito
     */
    private static float fixedTimestep = 0.02f;
    /**
     * L'accumulator per capire se devo eseguire i metodi fixed
     */
    private static float accumulator;

    /**
     * Inizializza la lista delle scene e l’index della scena attiva
     */
    public static void initialize(PApplet app)
    {
        mainApp = app;
        sceneList = new ArrayList<>();
        inputManager = new InputManager();
        Input.setup(inputManager);
    }

    /**
     * Esegue la scena attualmente selezionata
     */
    public static void executeScene()
    {

        // Eseguo come prima cosa il calcolo del deltaTime, ovvero quanto è durato
        // l'ultimo frame
        float lastTime = time; // salvo quand'è cominciata l'ultima escuzione
        time = mainApp.millis(); // segno quand'è cominciata questa esecuzione
        // calcolo il deltaTime facendo la differenza fra i due tempi così so quanto
        // tempo è durato il frame in millisecondi
        // poi lo divido per 1000 per averlo in secondi
        deltaTime = (time - lastTime) / 1000f;

        accumulator += deltaTime(); //utilizzato per eseguire un metodo in un fixed timestep

        if (skipInitialFrames < 5) {
            skipInitialFrames++;
            accumulator = 0;
        }

        sceneList.get(activeSceneIndex).update();
        sceneList.get(activeSceneIndex).getCamera().calculateCamera(); //aggiorno la telecamera

        //il valore si può accumulare fino a diventare maggiore del fixed timestep e viene eseguito finché non torna minore
        while (accumulator >= fixedTimestep) {
            sceneList.get(activeSceneIndex).fixedUpdate(); //eseguo il fixed update nella scena attuale
            accumulator -= fixedTimestep; //all'accumulator tolgo il valore del fixed timestep, se rimane sopra rieseguo il fixed update
            sceneList.get(activeSceneIndex).getCamera().calculateCamera(); //aggiorno la telecamera
        }
        sceneList.get(activeSceneIndex).calculateCollisions(); //calcolo le collisioni

        sceneList.get(activeSceneIndex).renderObjects(); //renderizzo gli oggetti
        sceneList.get(activeSceneIndex).renderUI(); //renderizzo l'UI

        Input.updateKeyStatus(); //aggiorno lo stato degli Input
    }

    /**
     * Carica la scena con l’index specificato
     *
     * @param index Index della scena da caricare
     */
    public static void loadScene(int index)
    {
        sceneList.get(index).initialize(); //inizializzazione della scena in posizione passata come parametro
        activeSceneIndex = index; //aggiornamento dell'indice della scena attiva
    }

    /**
     * Aggiunge la scena specificata alla lista delle scene esistenti
     *
     * @param blueprint Scena da aggiungere
     */
    public static void addScene(SceneBlueprint blueprint)
    {
        sceneList.add(blueprint); //inserimento della scena di tipo SceneBlueprint
    }

    /**
     * Getter per activeScene
     *
     * @return Restituisce la scena attiva
     */
    public static SceneBlueprint getActiveScene()
    {
        return sceneList.get(activeSceneIndex); //restituzione della scena attiva
    }

    /**
     * Getter per activeSceneIndex
     *
     * @return Restituisce l’index della scena attiva
     */
    public static int getActiveSceneIndex()
    {
        return activeSceneIndex;
    }

    /**
     * Aggiunge un oggetto alla scena attualmente attiva
     *
     * @param object Oggetto da aggiungere
     */
    public static void instantiate(GameObject object)
    {
        sceneList.get(activeSceneIndex).addObject(object); //inserimento dell'oggetto, passato come parametro, alla scena attualmente attiva, indicata da activeSceneIndex
    }

    /**
     * Rimuove un oggetto dalla scena attualmente attiva
     *
     * @param object Oggetto da rimuovere
     */
    public static void destroy(GameObject object)
    {
        sceneList.get(activeSceneIndex).removeObject(object); //rimozione dell'oggetto, passato come parametro, dalla scena attualmente attiva, indicata da activeSceneIndex
    }

    /**
     * Getter per l'app principale
     *
     * @return l'app principale
     */
    public static PApplet getApp()
    {
        return mainApp;
    }

    /**
     * Getter per il deltaTime
     *
     * @return il valore del deltaTime
     */
    public static float deltaTime()
    {
        return deltaTime;
    }

    public static float fixedDeltaTime()
    {
        return fixedTimestep;
    }
}
