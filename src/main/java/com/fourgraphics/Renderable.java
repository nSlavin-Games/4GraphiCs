
package com.fourgraphics;

import processing.core.PApplet;

import java.io.Serializable;

/**
 * @author Dareisa, Furriolo, Iurcea
 */
public abstract class Renderable extends Component implements Serializable
{

    public transient boolean renderElement = true;

    abstract protected void render();

    public abstract Renderable clone();
}


