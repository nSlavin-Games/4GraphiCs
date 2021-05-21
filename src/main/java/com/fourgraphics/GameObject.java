package com.fourgraphics;
//librerie importate

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameObject {

    /**
     * La classe GameObject indica un oggetto qualsiasi della scena
     * componentList è un arraylist che contiene tutti i componenti attaccati all'oggetto
     * transform è un oggetto di tipo Transform ed inidica la posizione e la dimesione dell'oggetto
     * name è il nome dell'oggetto
     */
    private ArrayList<Object> componentList;
    public Transform transform;
    private String name;

    /**
     * Costruttore
     * @param componentList
     * @param name
     */
    public GameObject(ArrayList<Object> componentList, String name) {
        this.componentList = new ArrayList<>();
        this.transform = new Transform();
        this.name = name;

        for (Object component : componentList)
            addComponent(component);
    }

    /**
     * Costruttore
     *
     * @param componentList Lista dei componenti
     * @param name          Nome dell'oggetto
     * @param transform     Dimensione e posizione
     */
    public GameObject(ArrayList<Object> componentList, String name, Transform transform) {
        this.componentList = new ArrayList<>();
        this.transform = transform;
        this.name = name;

        for (Object component : componentList)
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
                if (other.componentList.get(i) instanceof UIElement)
                    addComponent(((UIElement) other.componentList.get(i)).clone());

                if (other.componentList.get(i) instanceof Renderable)
                    addComponent(((Renderable) other.componentList.get(i)).clone());

                if (other.componentList.get(i) instanceof Collider)
                    addComponent(((Collider) other.componentList.get(i)).clone());

                if (other.componentList.get(i) instanceof Script)
                    addComponent(other.componentList.get(i));
            }
        } catch (Exception e) {
            Logger.getLogger("4GraphiCs | GameObject").log(Level.WARNING, "Unable to clone element in GameObject: ");
            e.printStackTrace();
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
        for (Object obj : componentList) {
            //se l'istanza dell'oggetto corrente è uguale a quella di type:
            if (type.isInstance(obj)) {
                //viene restituito l'ogetto corrente già castato
                return type.cast(obj);
            }
        }
        //se non viene trovato nessun componente dello stesso tipo di type viene mandato un errore
        throw new InvalidParameterException("There is no component of the specified type");
    }

    /**
     * metodo addComponent può aggiungere un component in componentList
     * @param component componente preso come parametro
     * @param <T>
     */
    public <T> void addComponent(T component) {
        //ciclo for each per ogni oggetto di tipo Object in componentList
        if(componentList != null)
        {
            for (Object obj : componentList) {
                //se c'è già un oggetto dello stesso tipo di component parte un'eccezione
                if(component.getClass().equals(obj.getClass())
                || (component instanceof Renderable && obj instanceof Renderable)
                || (component instanceof UIElement && obj instanceof UIElement)){
                    throw new InvalidParameterException("A component of the specified type (" + component.getClass().getName() + ") already exists");
                }
            }
        }

        //se component è un'istanza di di 'Script', 'Collider', 'Renderable' o 'UIElement' allora vengono inizializzate gli attributi transform e gameObject
        if(component instanceof Script){
            ((Script) component).transform = transform;
            ((Script) component).gameObject = this;
        } else if(component instanceof Collider){
            ((Collider) component).transform = transform;
            ((Collider) component).gameObject = this;
        } else if(component instanceof Renderable) {
            ((Renderable) component).transform = transform;
            ((Renderable) component).gameObject = this;
        } else if(component instanceof UIElement) {
            ((UIElement) component).transform = transform;
            ((UIElement) component).gameObject = this;
        }
        //se non sono partite eccezioni viene aggiunto component alla lista
        componentList.add(component);
    }

    /**
     * metodo hasComponent restituisce vero se componentList ha un componente del tipo inserito
     * @param type tipo di classe
     * @param <T>
     * @return
     */
    public <T> boolean hasComponent(Class<T> type){
        //ciclo for each
        for (Object obj : componentList) {
            //se l'oggetto corrente è un istanza di type allora viene restituito vero
            if (type.isInstance(obj)) {
                return true;
            }
        }
        //altrimenti falso
        return false;
    }

    //metodo getter di componentList
    public ArrayList<Object> getComponents() {
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
}
