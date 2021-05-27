package com.fourgraphics;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PImage;

/**
 * Il modello di una scena che gestisce l’esecuzione di tutti gli oggetti,
 * renderizzando qualsiasi renderer attaccato, calcolando le collisioni,
 * gestendo l’UI e la sua telecamera.
 */
public class SceneBlueprint
{
    /**
     * La lista degli oggetti presenti nella scena
     */
    private ArrayList<GameObject> objectList = new ArrayList<>();
    /**
     * La lista degli oggetti presenti nella scena al suo caricamento
     */
    private ArrayList<GameObject> defaultObjectList = new ArrayList<>();
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
     * Immagine di background per la scena corrente
     */
    private PImage background;

    /**
     * Colore di background per la scena corrente
     */
    private int backgroundColor = SceneManager.getApp().color(45,73,118);

    /**
     * Se è il primo caricamento inizializza la scena con i vari GameObject, crea
     * la telecamera, inizializza la lista degli script, oggetti renderizzabili,
     * dell’ui e dei collider attaccati ai GameObject, specifica in una lista
     * separata i collider dinamici e poi indica che è stato inizializzato, in
     * qualsiasi caso dopo tutto quanto chiama il metodo Start
     */
    protected void initialize()
    {
        //assegnazione di tutti i GameObject
        objectList = new ArrayList<>();
        for (int i = 0; i < defaultObjectList.size(); i++)
            objectList.add(defaultObjectList.get(i).clone());

        sceneCamera = new Camera();
        scriptList = new ArrayList<>();
        renderableElements = new ArrayList<>();
        uiElements = new ArrayList<>();
        dynamicColliders = new ArrayList<>();
        collidersList = new ArrayList<>();
        for (GameObject o : objectList)
        { //per ogni oggetto di tipo GameObject nella lista di objectList
            initializeObject(o); //inizializzazione di ogni oggetto di tipo GameObject
        }
        start(); //caricamento della scena
    }

    /**
     * Eseguito al caricamento di una scena, deve eseguire lo start di tutti gli
     * script attaccati
     */
    protected void start()
    {
        if(collidersList.size() > 0)
        {
            for (Collider coll : dynamicColliders)
            {
                coll.previousPosition.set(coll.gameObject.transform.getPosition());
            }
        }

        try
        {
            if(scriptList.size() > 0)
            {
                //scena già inizializzata
                for (Script s : scriptList)
                { //per ogni script della lista scriptList
                    s.Start(); //inizializzazione di tutti gli script della scena
                }
            }
        } catch(Exception e)
        {
            DebugConsole.ErrorInternal("Script Initialization Error", e.getStackTrace(),Thread.currentThread().getStackTrace());
        }
    }

    /**
     * Tutti i metodi ed elementi da eseguire ogni frame, deve eseguire l’update
     * di tutti gli script attaccati
     */
    protected void update()
    {
        //NOTE(sv-molinari): Sfondo applicato all'interno dell'update di SceneBlueprint, prima era assente
        if (background == null)
        {
            SceneManager.getApp().background(backgroundColor);
        } else
        {
            SceneManager.getApp().imageMode(SceneManager.getApp().CENTER);
            SceneManager.getApp().pushMatrix();
            SceneManager.getApp().translate(Rescaler.resizeW(sceneCamera.getOffsetPosition().getX()), Rescaler.resizeH(sceneCamera.getOffsetPosition().getY()));
            SceneManager.getApp().image(background, 0, 0, SceneManager.getApp().width, SceneManager.getApp().height);
            SceneManager.getApp().popMatrix();
        }

        try
        {
            if(scriptList.size() > 0)
            {
                //NOTE(dfmolinari): ora esegue solo l'update degli script
                for (int i = scriptList.size() - 1; i >= 0; i--)
                {
                    scriptList.get(i).Update();
                }
            }
        } catch (Exception e)
        {
            DebugConsole.ErrorInternal("Script Update Error", e.getStackTrace(),Thread.currentThread().getStackTrace());
        }
    }

    /**
     * Esegue il fixed update di tutti gli script
     */
    protected void fixedUpdate()
    {
        if(scriptList.size() > 0)
        {
            for (int i = scriptList.size() - 1; i >= 0; i--)
            {
                scriptList.get(i).FixedUpdate();
            }
        }
    }

    /**
     * Getter che ottiene un oggetto singolo dalla objectList tramite il suo ID
     *
     * @param objectID ID dell'oggetto
     * @return Restituisce l’oggetto equivalente all’id selezionato
     */
    protected GameObject getObject(int objectID)
    {
        if(objectID >= objectList.size() || objectID < 0)
        {
            DebugConsole.ErrorInternal("Object Retrieval Error", "ID Cannot be found, make sure it's greater than 0 and less than the amount of objects in the scene", Thread.currentThread().getStackTrace());
            return null;
        } else
            return objectList.get(objectID); //restituzione dell'oggetto contenuto in objectList con ID corrispondente al parametro
    }

    /**
     * Getter che cerca nella lista e restituisce l’oggetto che corrisponde al
     * nome specificato, se ci sono più oggetti con lo stesso nome restituisce il
     * primo
     *
     * @param objectName Nome dell'oggetto da ottenere
     * @return Restituisce l’oggetto che corrisponde al nome specificato, se ci
     * sono più oggetti con lo stesso nome restituisce il primo
     */
    protected GameObject getObject(String objectName)
    {
        for (int i = 0; i < objectList.size(); i++)
        { //analisi di ogni oggetto nella lista di objectList
            if (objectList.get(i).getName().equals(objectName))
            { //confronto del nome dell'oggetto corrente con quello dell'oggetto passato come parametro
                return objectList.get(i); //restituzione dell'oggetto nella posizione corrente (i)
            }
        }
        DebugConsole.ErrorInternal("Object Retrieval Error", "Specified object [" + objectName + "] cannot be found!", Thread.currentThread().getStackTrace());
        return null; //restituzione di default
    }

    /**
     * Getter per objectList
     *
     * @return Restituisce la lista degli oggetti
     */
    protected ArrayList<GameObject> getObjectList()
    {
        return objectList;
    }

    /**
     * Getter per UIElements
     *
     * @return Resituisce la lista degli elementi dell’UI
     */
    @Deprecated
    protected ArrayList<UIElement> getUiElements()
    {
        return uiElements;
    }

    /**
     * Getter per sceneCamera
     *
     * @return Restituisce la telecamera della scena
     */
    public Camera getCamera()
    {
        return sceneCamera;
    }

    /**
     * Esegue tutti i render necessari, fra renderer e animator
     */
    protected void renderObjects()
    {
        if(renderableElements.size() > 0)
        {
            for (int i = 0; i < renderableElements.size(); i++)
            { //per ogni oggetto di tipo Renderable della lista renderableElements
                renderableElements.get(i).render(); //esecuzione animazioni degli oggetti Renderable
            }
        }

        if(collidersList.size() > 0)
        {
            for(int i = 0; i < collidersList.size(); i++)
            {
                collidersList.get(i).debugDisplay();
            }
        }

    }

    /**
     * Disegna tutti gli elementi dell’UI
     */
    protected void renderUI()
    {
        if(uiElements.size() > 0)
        {
            for (int i = 0; i < uiElements.size(); i++)
            {
                uiElements.get(i).display();
            }
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
    protected void calculateCollisions()
    {
        if(dynamicColliders.size() > 0)
        {
            for (int i = 0; i < dynamicColliders.size(); i++)
            {
                Collider c = dynamicColliders.get(i); //primo collider
                for (int j = 0; j < collidersList.size(); j++)
                {
                    Collider c2 = collidersList.get(j); //secondo collider (statico)
                    if (c2.isDynamic() || c.ignoreSnap)
                    { //se il secondo collider è dinamico
                        if (!c.equals(c2))
                        { //se c'è collisione tra i due collider e questi sono diversi tra loro
                            CollisionDirection direction = c.checkCollision(c2);
                            if (direction != CollisionDirection.NONE)
                            {
                                for (Object o : c.gameObject.getComponents())
                                { //per ogni oggetto di tipo Object della lista dei componenti del collider
                                    if (o instanceof Script)
                                    { //se l'oggetto è di tipo Script
                                        ((Script) o).OnCollisionStay(c, c2, direction); //casting dell'oggetto da Object a Script e collisione tra i due collider
                                    }
                                }
                            }
                        }
                    } else
                    { //altrimenti se il collider è statico
                        CollisionDirection direction = c.checkCollisionSnap(c2);
                        if (direction != CollisionDirection.NONE)
                        { //se c'è collisione tra i due collider, di cui uno statico
                            for (Object o : c.gameObject.getComponents())
                            { //per ogni oggetto di tipo Object della lista dei componenti del collider
                                if (o instanceof Script)
                                { //se l'oggetto è di tipo Script
                                    ((Script) o).OnCollisionStay(c, c2, direction); //casting dell'oggetto da Object a Script e collisione tra i due collider
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public SceneBlueprint setObjectList(ArrayList<GameObject> objectList) {
        this.defaultObjectList = objectList;
        return this;
    }

    public SceneBlueprint setObjectList(GameObject... objectList) {
        this.defaultObjectList = new ArrayList<>(Arrays.asList(objectList));
        return this;
    }

    protected void addObject(GameObject object)
    {
        objectList.add(object); //inserimento alla lista objectList dell'oggetto passato come parametro
        initializeObject(object); //inizializzazione oggetto
    }

    protected void removeObject(GameObject object)
    {
        objectList.remove(object); //rimozione dalla lista objectList dell'oggetto passato come parametro
        deinitializeObject(object); //deinizializzazione oggetto
    }

    private void initializeObject(GameObject object)
    {
        ArrayList<Component> obj = object.getComponents(); //assegnazione dei componenti dell'oggetto passato come parametro all'arrayList di tipo Object (obj)
        for (Component o : obj)
        { //per ogni oggetto di tipo Object della lista obj
            if (o instanceof Script)
            { //se l'oggetto è uno script
                if(o.getClass().isAnnotationPresent(RequireComponent.class))
                {
                    RequireComponent annotation = o.getClass().getAnnotation(RequireComponent.class);
                    for(int i = 0; i < annotation.requiredComponents().length; i++)
                    {
                        if(!o.gameObject.hasComponent(annotation.requiredComponents()[i]))
                        {
                            DebugConsole.Fatal("Missing Component Error",
                                    o.getClass().getName() + " requires " + annotation.requiredComponents()[i] + " as a component",
                                    Thread.currentThread().getStackTrace());
                        }
                    }
                }
                scriptList.add((Script) o); //casting da Object a Script e inserimento dell'oggetto alla lista per gli script
            } else if (o instanceof Renderable)
            { //se l'oggetto è un render
                renderableElements.add((Renderable) o); //casting da Object a Renderable e inserimento dell'oggetto alla lista per i render
            } else if (o instanceof UIElement)
            { //se l'oggetto è un elemento UI
                uiElements.add((UIElement) o); //casting da Object a UIElement e inserimento dell'oggetto alla lista per gli elementi dell'UI
            } else if (o instanceof Collider)
            { //se l'oggetto è un collider
                collidersList.add((Collider) o); //casting da Object a Collider e inserimento dell'oggetto alla lista per i collider
                if (((Collider) o).isDynamic())
                { //casting in Collider e controllo se l'oggetto è in movimento (quindi è dinamico)
                    dynamicColliders.add((Collider) o); //casting dell'oggetto in Collider e inserimento alla lista per i collider dinamici
                }
            }
        }
    }

    private void deinitializeObject(GameObject object)
    {
        ArrayList<Component> obj = object.getComponents(); //assegnazione dei componenti dell'oggetto passato come parametro all'arrayList di tipo Object (obj)
        for (Component o : obj)
        { //per ogni oggetto di tipo Object della lista obj
            if (o instanceof Script)
            { //se l'oggetto è uno script
                scriptList.remove((Script) o); //casting da Object a Script e rimozione dell'oggetto alla lista per gli script
            } else if (o instanceof Renderable)
            { //se l'oggetto è un render
                renderableElements.remove((Renderable) o); //casting da Object a Renderable e rimozione dell'oggetto alla lista per i render
            } else if (o instanceof UIElement)
            { //se l'oggetto è un elemento UI
                uiElements.remove((UIElement) o); //casting da Object a UIElement e rimozione dell'oggetto alla lista per gli elementi dell'UI
            } else if (o instanceof Collider)
            { //se l'oggetto è un collider
                collidersList.remove((Collider) o); //casting da Object a Collider e rimozione dell'oggetto alla lista per i collider
                if (((Collider) o).isDynamic())
                { //casting in Collider e controllo se l'oggetto è in movimento (quindi è dinamico)
                    dynamicColliders.remove((Collider) o); //casting dell'oggetto in Collider e rimozione alla lista per i collider dinamici
                }
            }
        }
    }

    /**
     * Imposta l'immagine di sfondo per il blueprint attuale.
     *
     * @param background Immagine di sfondo
     * @return La scena in utilizzo
     * @author sv-molinari
     */
    public SceneBlueprint setBackground(PImage background)
    {
        this.background = background;
        return this;
    }

    /**
     * Imposta il colore di sfondo per il blueprint attuale.
     *
     * @param backgroundColor Colore di sfondo
     * @return La scena in utilizzo
     * @author sv-molinari
     */
    public SceneBlueprint setBackground(int backgroundColor)
    {
        this.backgroundColor = backgroundColor;
        return this;
    }
}
