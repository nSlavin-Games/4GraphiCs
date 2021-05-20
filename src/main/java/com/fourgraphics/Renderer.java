
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
        renderType = DrawType.TEXTURED;
        sketch = SceneManager.getApp();
    }


    public Renderer(int color, DrawType renderType) {
        this.color = color;
        this.renderType = renderType;
        sketch = SceneManager.getApp();
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void render() {
        sketch.noStroke();
        float x = transform.getPosition().getX();
        float y = transform.getPosition().getY();
        float w = transform.getScale().getX();
        float h = transform.getScale().getY();
        switch (renderType) {
            case TEXTURED:
                sketch.imageMode(sketch.CENTER);
                sketch.image(texture, x, y, w, h);
                sketch.imageMode(sketch.CORNER);
                break;
            case RECT:
                sketch.rectMode(sketch.CENTER);
                sketch.fill(color);
                sketch.rect(x, y, w, h);
                sketch.rectMode(sketch.CORNER);
                break;
            case CIRCLE:
                sketch.fill(color);
                sketch.circle(x, y, w);
                break;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return new Renderer((PImage) this.texture.clone());
        } catch (Exception ignored) {
            return new Renderer(this.color, this.renderType);
        }
    }
}
