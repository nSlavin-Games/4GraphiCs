package com.fourgraphics;

public class Transform {
    private Vector2 position;//posizione dell'oggetto
    private Vector2 scale;//dimensione dell'oggetto

    /**
     * Costruttore default
     */
    protected Transform() {
        this.position = new Vector2();
        this.scale = new Vector2();
    }

    /**
     * Costruttore con 2 Vector2
     *
     * @param position Posizione
     * @param scale    Dimensione
     */
    protected Transform(Vector2 position, Vector2 scale) {
        this.position = position;
        this.scale = scale;
    }

    /**
     * Costruttore con float, genera al suo interno 2 Vector2 con gli attributi passati
     *
     * @param x      Coordinata X
     * @param y      Coordinata Y
     * @param width  Larghezza
     * @param height Altezza
     */
    protected Transform(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.scale = new Vector2(width, height);
    }

    /**
     * Costruttore con float e un parametro per la dimensione, genera al suo interno 2 Vector2 con gli attributi passati.
     * NOTA: Questo costruttore crea transform quadrati in quanto esiste solo un parametro per la dimensione!
     *
     * @param x    Coordinata X
     * @param y    Coordinata Y
     * @param size Dimensione
     */
    public Transform(float x, float y, float size) {
        this.position = new Vector2(x, y);
        this.scale = new Vector2(size, size);
    }

    /**
     * restituisce la posizione
     *
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * imposta la posizione dell'oggetto
     *
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    /**
     * restituisce la dimensione
     *
     * @return scale
     */
    public Vector2 getScale() {
        return scale;
    }

    /**
     * imposta la dimensione dell'oggetto
     *
     * @param scale
     */
    public void setScale(Vector2 scale) {
        this.scale.set(scale);
    }

    /**
     * imposta la posizione dell'oggetto
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        position.set(x, y);

    }

    /**
     * imposta la dimensione dell'oggetto
     *
     * @param x
     * @param y
     */
    public void setScale(float x, float y) {//
        scale.set(x, y);
    }
}