
package com.fourgraphics;

import processing.core.PApplet;

/**
 * @author Dareisa, Furriolo, Iurcea
 */
public abstract class Renderable extends Component
{

    public boolean renderElement = true;

    abstract protected void render();

    public abstract Renderable clone();
}


