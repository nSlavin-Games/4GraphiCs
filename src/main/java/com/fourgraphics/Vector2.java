package com.fourgraphics;

/**
 * Il modello di un vettore bidimensionale, contiene il riferimento
 * ai valori su due assi e tutti i metodi di utility per impostazioni e operazioni.
 */
public class Vector2 {
    //attributi
    private float x; // il valore sull’asse x
    private float y; //il valore sull’asse y

    //costruttori

    /**
     * @param x
     * @param y
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Costruttore non parametrico. Valori impostati a 0.
     */
    public Vector2() {
        x = 0;
        y = 0;
    }

    //metodi

    /**
     * @param x
     * @param y Il metodo controlla se i valori del vettore sono uguali a quelli passati
     * @return true or false
     */
    public boolean equals(float x, float y) {
        if (this.x == x && this.y == y)
            return true;
        return false;
    }

    /**
     * @param vec Il metodo controlla se il vettore è uguale al vettore passato
     * @return true or false
     */
    public boolean equals(Vector2 vec) {
        if (this.x == vec.x && this.y == vec.y)
            return true;
        return false;
    }

    /**
     * Il metodo restituisce un nuovo vettore che indica la direzione verso destra (probabilmente [1,0])
     */
    public static Vector2 RIGHT() {
        Vector2 vec = new Vector2(1, 0);
        return vec;
    }

    /**
     * Il metodo restituisce un nuovo vettore che indica la direzione verso destra (probabilmente [-1,0])
     */
    public static Vector2 LEFT() {
        Vector2 vec = new Vector2(-1, 0);
        return vec;
    }

    /**
     * Il metodo restituisce un nuovo vettore che indica la direzione verso destra (probabilmente [0,1])
     */
    public static Vector2 DOWN() {
        Vector2 vec = new Vector2(0, 1);
        return vec;
    }

    /**
     * Il metodo restituisce un nuovo vettore che indica la direzione verso destra (probabilmente [0,-1])
     */
    public static Vector2 UP() {
        Vector2 vec = new Vector2(0, -1);
        return vec;
    }

    /**
     * @param value Il metodo somma sia x che y con il valore indicato
     */
    public Vector2 sum(float value) {
        x += value;
        y += value;
        return this;
    }

    /**
     * @param x
     * @param y Il metodo somma il vettore con i valori specificati
     */
    public Vector2 sum(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * @param vec Il metodo somma il vettore con il secondo vettore specificato
     */
    public Vector2 sum(Vector2 vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    /**
     * @param value Il metodo moltiplica sia x che y per il valore indicato
     */
    public Vector2 multiply(float value) {
        x *= value;
        y *= value;
        return this;
    }

    /**
     * @param x
     * @param y Il metodo moltiplica il vettore per i valori specificati
     */
    public Vector2 multiply(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    /**
     * @param vec Il metodo moltiplica il vettore per il secondo vettore specificato
     */
    public Vector2 multiply(Vector2 vec) {
        x *= vec.x;
        y *= vec.y;
        return this;
    }

    /**
     * @param value Il metodo divide sia x che y per il valore indicato
     */
    public Vector2 divide(float value) {
        x /= value;
        y /= value;
        return this;
    }

    /**
     * @param x
     * @param y Il metodo divide il vettore con i valori specificati
     */
    public Vector2 divide(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    /**
     * @param vec Il metodo divide il vettore con il secondo vettore specificato
     */
    public Vector2 divide(Vector2 vec) {
        x /= vec.x;
        y /= vec.y;
        return this;
    }

    /**
     * @param x
     * @param y Il metodo imposta i valori del vettore
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param vec Il metodo imposta i valori del vettore
     */
    public void set(Vector2 vec) {
        //System.out.print("Setting X from " + this.x + " to " + vec.x);
        this.x = vec.x;
        //System.out.println("! X is now " + this.x + "!");
        this.y = vec.y;
    }

    /**
     * @param x Il metodo imposta il valore della x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @param y Il metodo imposta il valore della y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Il metodo restituisce il valore della x
     */
    public float getX() {
        return x;
    }

    /**
     * Il metodo restituisce il valore della y
     */
    public float getY() {
        return y;
    }


    /*NOTE(samu):
     * -----------------------------------------------------------------
     * "No samu, non serve fare le operazioni sui vector senza salvare."
     * -----------------------------------------------------------------
     */

    /**
     * Il metodo somma sia x che y con il valore indicato senza modificare il vettore originale
     *
     * @param value valore con il quale moltiplicare il vettore
     */
    public Vector2 sumN(float value) {
        return new Vector2(x + value, y + value);
    }

    /**
     * Il metodo somma il vettore con i valori specificati senza modificare il vettore originale
     *
     * @param x valore per x
     * @param y valore per y
     */
    public Vector2 sumN(float x, float y) {
        return new Vector2(this.x + x, this.y + y);
    }

    /**
     * Il metodo somma il vettore con il secondo vettore specificato senza modificare il vettore originale
     *
     * @param vec Vettore da sommare
     */
    public Vector2 sumN(Vector2 vec) {
        return new Vector2(x + vec.x, y + vec.y);
    }

    /**
     * Il metodo moltiplica sia x che y per il valore indicato senza modificare il vettore originale
     *
     * @param value Valore con quale moltiplicare il vettore
     */
    public Vector2 multiplyN(float value) {
        return new Vector2(x * value, y * value);
    }

    /**
     * Il metodo moltiplica il vettore per i valori specificati  senza modificare il vettore originale
     *
     * @param x Valore per x
     * @param y Valore per y
     */
    public Vector2 multiplyN(float x, float y) {
        return new Vector2(this.x * x, this.y * y);
    }

    /**
     * Il metodo moltiplica il vettore per il secondo vettore specificato senza modificare il vettore originale
     *
     * @param vec Vettore da moltiplicare al vettore di origine
     */
    public Vector2 multiplyN(Vector2 vec) {
        return new Vector2(x * vec.x, y * vec.y);
    }

    /**
     * Il metodo divide sia x che y per il valore indicato senza modificare il vettore originale
     *
     * @param value Valore da dividere al vettore originale
     */
    public Vector2 divideN(float value) {
        return new Vector2(x / value, y / value);
    }

    /**
     * Il metodo divide il vettore con i valori specificati senza modificare il vettore originale
     *
     * @param x valore per x
     * @param y valore per y
     */
    public Vector2 divideN(float x, float y) {
        return new Vector2(this.x / x, this.y / y);
    }

    /**
     * Il metodo divide il vettore con il secondo vettore specificato senza modificare il vettore originale
     *
     * @param vec Vettore da dividere al vettore originale
     */
    public Vector2 divideN(Vector2 vec) {
        return new Vector2(x / vec.x, y / vec.y);
    }
}
