package com.fourgraphics;

import java.util.ArrayList;

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
        for (GameObject o : objectList) { //per ogni oggetto di tipo GameObject nella lista di objectList
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
        for (Collider coll : dynamicColliders)
        {
            coll.previousPosition.set(coll.gameObject.transform.getPosition());
        }

        //scena già inizializzata
        for (Script s : scriptList)
        { //per ogni script della lista scriptList
            s.Start(); //inizializzazione di tutti gli script della scena
        }
    }

    /**
     * Tutti i metodi ed elementi da eseguire ogni frame, deve eseguire l’update
     * di tutti gli script attaccati
     */
    protected void update()
    {
        //MODIFICA - FURFARO ora esegue solo l'update degli script
        for (int i = scriptList.size() - 1; i >= 0; i--)
        {
            scriptList.get(i).Update();
        }
    }

    /**
     * Esegue il fixed update di tutti gli script
     */
    protected void fixedUpdate()
    {
        for (int i = scriptList.size() - 1; i >= 0; i--)
        {
            scriptList.get(i).FixedUpdate();
        }
    }

    /**
     * Getter che ottiene un oggetto singolo dalla objectList tramite il suo ID
     *
     * @param objectID ID dell'oggetto
     * @return Restituisce l’oggetto equivalente all’id selezionato
     */
    public GameObject getObject(int objectID)
    {
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
    public GameObject getObject(String objectName)
    {
        for (int i = 0; i < objectList.size(); i++)
        { //analisi di ogni oggetto nella lista di objectList
            if (objectList.get(i).getName().equals(objectName))
            { //confronto del nome dell'oggetto corrente con quello dell'oggetto passato come parametro
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
    public ArrayList<GameObject> getObjectList()
    {
        return objectList;
    }

    /**
     * Getter per UIElements
     *
     * @return Resituisce la lista degli elementi dell’UI
     */
    public ArrayList<UIElement> getUiElements()
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
        for (int i = renderableElements.size() - 1; i >= 0; i--)
        { //per ogni oggetto di tipo Renderable della lista renderableElements
            renderableElements.get(i).render(); //esecuzione animazioni degli oggetti Renderable
        }
    }

    /**
     * Disegna tutti gli elementi dell’UI
     */
    protected void renderUI()
    {
        for (int i = uiElements.size() - 1; i >= 0; i--)
        {
            uiElements.get(i).display();
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

        for (int i = dynamicColliders.size() - 1; i >= 0; i--)
        {
            Collider c = dynamicColliders.get(i); //primo collider
            for (int j = collidersList.size() - 1; j >= 0; j--)
            {
                Collider c2 = collidersList.get(j); //secondo collider (statico)
                if (c2.isDynamic())
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
                    if (direction != CollisionDirection.NONE) { //se c'è collisione tra i due collider, di cui uno statico
                        for (Object o : c.gameObject.getComponents()) { //per ogni oggetto di tipo Object della lista dei componenti del collider
                            if (o instanceof Script) { //se l'oggetto � di tipo Script
                                ((Script) o).OnCollisionStay(c, c2, direction); //casting dell'oggetto da Object a Script e collisione tra i due collider
                            }
                        }
                    }
                }
            }
        }
    }

    public void setObjectList(ArrayList<GameObject> objectList)
    {
        this.defaultObjectList = objectList;
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
        ArrayList<Object> obj = object.getComponents(); //assegnazione dei componenti dell'oggetto passato come parametro all'arrayList di tipo Object (obj)
        for (Object o : obj)
        { //per ogni oggetto di tipo Object della lista obj
            if (o instanceof Script) { //se l'oggetto è uno script
                scriptList.add((Script) o); //casting da Object a Script e inserimento dell'oggetto alla lista per gli script
            } else if (o instanceof Renderable) { //se l'oggetto è un render
                renderableElements.add((Renderable) o); //casting da Object a Renderable e inserimento dell'oggetto alla lista per i render
            } else if (o instanceof UIElement) { //se l'oggetto è un elemento UI
                uiElements.add((UIElement) o); //casting da Object a UIElement e inserimento dell'oggetto alla lista per gli elementi dell'UI
            } else if (o instanceof Collider) { //se l'oggetto è un collider
                collidersList.add((Collider) o); //casting da Object a Collider e inserimento dell'oggetto alla lista per i collider
                if (((Collider) o).isDynamic()) { //casting in Collider e controllo se l'oggetto è in movimento (quindi è dinamico)
                    dynamicColliders.add((Collider) o); //casting dell'oggetto in Collider e inserimento alla lista per i collider dinamici
                }
            }
        }
    }

    private void deinitializeObject(GameObject object)
    {
        ArrayList<Object> obj = object.getComponents(); //assegnazione dei componenti dell'oggetto passato come parametro all'arrayList di tipo Object (obj)
        for (Object o : obj)
        { //per ogni oggetto di tipo Object della lista obj
            if (o instanceof Script) { //se l'oggetto è uno script
                scriptList.remove((Script) o); //casting da Object a Script e rimozione dell'oggetto alla lista per gli script
            } else if (o instanceof Renderable) { //se l'oggetto è un render
                renderableElements.remove((Renderable) o); //casting da Object a Renderable e rimozione dell'oggetto alla lista per i render
            } else if (o instanceof UIElement) { //se l'oggetto è un elemento UI
                uiElements.remove((UIElement) o); //casting da Object a UIElement e rimozione dell'oggetto alla lista per gli elementi dell'UI
            } else if (o instanceof Collider) { //se l'oggetto è un collider
                collidersList.remove((Collider) o); //casting da Object a Collider e rimozione dell'oggetto alla lista per i collider
                if (((Collider) o).isDynamic()) { //casting in Collider e controllo se l'oggetto è in movimento (quindi è dinamico)
                    dynamicColliders.remove((Collider) o); //casting dell'oggetto in Collider e rimozione alla lista per i collider dinamici
                }
            }
        }
    }
}
