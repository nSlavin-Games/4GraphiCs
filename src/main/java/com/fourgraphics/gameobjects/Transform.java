package com.fourgraphics.gameobjects;

public class Transform {

    private Vector2 position;//posizione dell'oggetto
    private Vector2 scale;//dimensione dell'oggetto



    /**
     * Costruttore
     *
     */
    public Transform() {
        this.position = new Vector2();
        this.scale = new Vector2();

    }

    /**
     * restituisce la posizione
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * imposta la posizione delloggetto
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    /**
     * restituisce la dimensione
     * @return scale
     */
    public Vector2 getScale() {
        return scale;
    }

    /**
     * imposta la dimensione delloggetto
     * @param scale
     */
    public void setScale(Vector2 scale) {
        this.scale.set(scale);
    }

    /**
     * imposta la posizione delloggetto
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        position.set(x,y);

    }

    /**
     * imposta la dimensione delloggetto
     * @param x
     * @param y
     */
    public void setScale(float x, float y) {//
        scale.set(x,y);
    }

}