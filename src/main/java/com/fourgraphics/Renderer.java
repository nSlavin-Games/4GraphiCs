
package com.fourgraphics;

import processing.core.PImage;

/**
 * @author Dareisa, Furriolo, Iurcea
 */
public class Renderer extends Renderable {
    private final DrawType renderType;
    private int color;
    private PImage texture;

    public Renderer(PImage texture) {
        this.color = 0;
        this.texture = texture;
        renderType = DrawType.TEXTURED;
    }


    public Renderer(int color, DrawType renderType) {
        this.color = color;
        this.renderType = renderType;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void render() {
        sketch.noStroke();

        float rx = Rescaler.resizeH(transform.getPosition().getX());
        float ry = Rescaler.resizeH(transform.getPosition().getY());
        float rw = Rescaler.resizeH(transform.getScale().getX());
        float rh = Rescaler.resizeH(transform.getScale().getY());

        switch (renderType) {
            case TEXTURED:
                sketch.imageMode(sketch.CENTER);
                sketch.image(texture, rx, ry, rw, rh);
                sketch.imageMode(sketch.CORNER);
                break;
            case RECT:
                sketch.rectMode(sketch.CENTER);
                sketch.fill(color);
                sketch.rect(rx, ry, rw, rh);
                sketch.rectMode(sketch.CORNER);
                break;
            case CIRCLE:
                sketch.fill(color);
                sketch.circle(rx, ry, rw);
                break;
        }
    }

    public Renderer clone() {
        if (this.renderType == DrawType.TEXTURED) {
            return new Renderer(this.texture);
        } else {
            return new Renderer(this.color, this.renderType);
        }
    }

    public void setTexture(PImage texture)
    {
        this.texture = texture;
    }
}
