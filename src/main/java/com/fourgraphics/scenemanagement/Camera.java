package com.fourgraphics.scenemanagement;

import com.fourgraphics.gameobjects.GameObject;
import com.fourgraphics.gameobjects.Vector2;

/**
 * Gestione della telecamera di gioco e impostazione di un sistema per seguire un target(per esempio un giocatore)
 */
public class Camera {
    /**
     * Il target da seguire, se non c’è un target allora rimane ferma
     */
    private GameObject target;
    /**
     * La posizione della telecamera
     */
    private Vector2 position;
    /**
     * L’offset della telecamera dalla sua posizione
     */
    private Vector2 offset;

    /**
     * Getter per target
     *
     * @return Restituisce l'oggetto che la camera deve seguire/alla quale è legata
     */
    public GameObject getTarget() {
        return target;
    }

    /**
     * @return Restituisce la posizione della camera
     */
    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public void calculateCamera() {
        //TODO(samu): not implemented
    }
}
