package com.fourgraphics;

import processing.core.PApplet;

public abstract class Component
{
    public GameObject gameObject; //l’oggetto a cui è attaccato questo componente
    public Transform transform; //la posizione e la dimensione dell’oggetto a cui è attaccato questo componente
    public PApplet sketch; //l'app principale

    public abstract Component clone();
}
