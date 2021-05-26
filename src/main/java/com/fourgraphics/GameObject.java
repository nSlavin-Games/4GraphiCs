package com.fourgraphics;
//librerie importate

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameObject {

    /**
     * La classe GameObject indica un oggetto qualsiasi della scena
     * componentList è un arraylist che contiene tutti i componenti attaccati all'oggetto
     * transform è un oggetto di tipo Transform ed inidica la posizione e la dimesione dell'oggetto
     * name è il nome dell'oggetto
     */
    private ArrayList<Component> componentList;
    public Transform transform;
    private String name;

    /**
     * Costruttore
     * @param componentList
     * @param name
     */
    public GameObject(ArrayList<Component> componentList, String name) {
        this.componentList = new ArrayList<>();
        this.transform = new Transform();
        this.name = name;

        for (Component component : componentList)
            addComponent(component);
    }

    /**
     * Costruttore
     *
     * @param componentList Lista dei componenti
     * @param name          Nome dell'oggetto
     * @param transform     Dimensione e posizione
     */
    public GameObject(ArrayList<Component> componentList, String name, Transform transform) {
        this.componentList = new ArrayList<>();
        this.transform = transform;
        this.name = name;

        for (Component component : componentList)
            addComponent(component);
    }

    /**
     * Costruttore
     *
     * @param name
     */
    public GameObject(String name) {
        this.componentList = new ArrayList<>();
        this.transform = new Transform();
        this.name = name;
    }

    protected GameObject(GameObject other) {
        transform = new Transform();
        transform.setPosition(other.transform.getPosition());
        transform.setScale(other.transform.getScale());
        componentList = new ArrayList<>();
        try {
            for (int i = 0; i < other.componentList.size(); i++) {
                addComponent(other.componentList.get(i).clone());
            }
        } catch (Exception e) {
            DebugConsole.ErrorInternal("Object Cloning Error", "Unable to clone an element in GameObject",Thread.currentThread().getStackTrace());
        }
        name = other.name;
    }

    /**
     * metodo getComponent restituisce un componente in base al tipo di classe inserita
     *
     * @param type tipo di classe
     * @param <T>
     * @return obj oggetto del tipo di classe inserito
     */
    public <T> T getComponent(Class<T> type) {
        //ciclo for each per ogni oggetto di tipo Object in componentList
        for (Component obj : componentList) {
            //se l'istanza dell'oggetto corrente è uguale a quella di type:
            if (type.isInstance(obj)) {
                //viene restituito l'ogetto corrente già castato
                return type.cast(obj);
            }
        }
        //se non viene trovato nessun componente dello stesso tipo di type viene mandato un errore
        DebugConsole.ErrorInternal("Missing Component Error", "Cannot find component of type [" + type.getName() + "] on this object", Thread.currentThread().getStackTrace());
        return null;
    }

    /**
     * metodo addComponent può aggiungere un component in componentList
     * @param component componente preso come parametro
     */
    public void addComponent(Component component) {
        //ciclo for each per ogni oggetto di tipo Object in componentList
        if(componentList != null)
        {
            for (Object obj : componentList) {
                //se c'è già un oggetto dello stesso tipo di component parte un'eccezione
                if(component.getClass().equals(obj.getClass())
                || (component instanceof Renderable && obj instanceof Renderable)
                || (component instanceof UIElement && obj instanceof UIElement)){
                    DebugConsole.ErrorInternal("Duplicate Component Error","A component of the specified type (" + component.getClass().getName() + ") already exists",Thread.currentThread().getStackTrace());
                    return;
                }
            }
        }

        //se component è un'istanza di di 'Script', 'Collider', 'Renderable' o 'UIElement' allora vengono inizializzate gli attributi transform e gameObject
        component.transform = transform;
        component.gameObject = this;
        component.sketch = SceneManager.getApp();
        //se non sono partite eccezioni viene aggiunto component alla lista
        componentList.add(component);
    }

    /**
     * metodo hasComponent restituisce vero se componentList ha un componente del tipo inserito
     * @param type tipo di classe
     * @return
     */
    public <T> boolean hasComponent(Class<T> type){

        //ciclo for each
        for (Component obj : componentList) {
            //se l'oggetto corrente è un istanza di type allora viene restituito vero
            if (type.isInstance(obj)) {
                return true;
            }
        }
        //altrimenti falso
        return false;
    }

    //metodo getter di componentList
    public ArrayList<Component> getComponents() {
        return componentList;
    }

    //metodo getter di name
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public GameObject clone() {
        return new GameObject(this);
    }

    //region ObjectComposer
    public static GameObject Compose(String name, float x, float y, float width, float height, Component... components) {
        return new GameObject(new ArrayList<>(Arrays.asList(components)), name, new Transform(x, y, width, height));
    }

    public static GameObject Compose(String name, Vector2 position, Vector2 size, Component... components) {
        return new GameObject(new ArrayList<>(Arrays.asList(components)), name, new Transform(position, size));
    }
    //endregion
}
