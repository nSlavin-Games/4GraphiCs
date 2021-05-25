package com.fourgraphics;

import processing.core.PApplet;
import processing.core.PImage;
import processing.opengl.PJOGL;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Objects;

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

    private static ArrayList<PImage> introImages = new ArrayList<>();

    /**
     * Inizializza la lista delle scene e l’index della scena attiva
     */
    public static void initialize(PApplet app)
    {
        mainApp = app;
        app.fullScreen(app.P3D);
        sceneList = new ArrayList<>();
        inputManager = new InputManager();
        Input.setup(inputManager);
        setProjectIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("4GC_Logo.png")).getPath());
        Rescaler.setSize(app.displayWidth, app.displayHeight);
        introImages.add(app.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("4GC_Logo_Transparent.png")).getPath()));
        CreateIntro();
    }

    public static void initialize(PApplet app, int screen)
    {
        mainApp = app;
        app.fullScreen(app.P3D, screen);
        sceneList = new ArrayList<>();
        inputManager = new InputManager();
        Input.setup(inputManager);
        setProjectIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("4GC_Logo.png")).getPath());
        Rescaler.setSize(app.displayWidth, app.displayHeight);
        introImages.add(app.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("4GC_Logo_Transparent.png")).getPath()));
        CreateIntro();
    }

    public static void initialize(PApplet app, int width, int height)
    {
        mainApp = app;
        app.size(width,height,app.P3D);
        sceneList = new ArrayList<>();
        inputManager = new InputManager();
        Input.setup(inputManager);
        setProjectIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("4GC_Logo.png")).getPath());
        Rescaler.setSize(width, height);
        introImages.add(app.loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("4GC_Logo_Transparent.png")).getPath()));
        CreateIntro();
    }

    public static void startGame()
    {
        loadSceneInternal(0);
    }

    public static void addIntroLogo(PImage logo)
    {
        introImages.add(logo);
    }

    private static class Intro extends Script
    {
        int currentIndex = 0;
        float introDuration = 2;
        float introTimer;

        int alpha;
        boolean fadedIn = false;

        public void Update()
        {
            if(!fadedIn && alpha < 255)
            {
                alpha += 3;
                if(alpha >= 255)
                {
                    alpha = 255;
                    fadedIn = true;
                }
            }
            if(fadedIn)
            {
                introTimer += SceneManager.deltaTime();
                if (introTimer >= introDuration)
                {
                    if(alpha > 0)
                    {
                        alpha -= 3;
                        if(alpha <= 0)
                        {
                            alpha = 0;
                            currentIndex++;
                            if (currentIndex == introImages.size())
                            {
                                if (sceneList.size() == 1)
                                    sceneList.add(new SceneBlueprint());

                                sketch.delay(400);
                                SceneManager.loadScene(1);
                                alpha = 255;
                            } else
                            {
                                gameObject.getComponent(Renderer.class).setTexture(introImages.get(currentIndex));
                                fadedIn = false;
                                introTimer = 0;
                            }
                        }
                    }
                }
            }
            sketch.tint(255,alpha);
        }
    }

    private static void CreateIntro()
    {
        SceneManager.addScene(new SceneBlueprint()
                .setObjectList(
                        GameObject.Compose(
                                "4GC Logo",
                                0, 0,
                                introImages.get(0).width,introImages.get(0).height,
                                new Renderer(introImages.get(0)),
                                new Intro()
                        )
                ).setBackground(getApp().color(23,24,24)));
    }

    public static void setProjectIcon(String icon)
    {
        PJOGL.setIcon(icon);
    }

    public static void setProjectTitle(String title)
    {
        getApp().getSurface().setTitle(title);
    }

    public static void setCursorState(boolean state)
    {
        if(state)
            getApp().getSurface().showCursor();
        else
            getApp().getSurface().hideCursor();
    }

    public static void setCursorImage(PImage image)
    {
        //TODO: not yet implemented
        getApp().getSurface().setCursor(image,0,0);
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
            sceneList.get(activeSceneIndex).calculateCollisions(); //calcolo le collisioni
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
        if(index > 0)
        {
            activeSceneIndex = index; //aggiornamento dell'indice della scena attiva
            sceneList.get(index).initialize(); //inizializzazione della scena in posizione passata come parametro
        } else
            throw new InvalidParameterException("Cannot reload intro");
    }

    private static void loadSceneInternal(int index)
    {
        activeSceneIndex = index; //aggiornamento dell'indice della scena attiva
        sceneList.get(index).initialize(); //inizializzazione della scena in posizione passata come parametro
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

    public static GameObject findObject(String name)
    {
        return sceneList.get(activeSceneIndex).getObject(name);
    }

    public static GameObject findObject(int index)
    {
        return sceneList.get(activeSceneIndex).getObject(index);
    }

    /**
     * Getter per l'app principale
     *
     * @return l'app principale
     */
    protected static PApplet getApp()
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
