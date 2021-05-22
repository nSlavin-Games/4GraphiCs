package com.fourgraphics.utils;

import com.fourgraphics.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helper Class che permette una creazione più intuitiva (e one-liner) per entità.
 *
 * @author sv-molinari
 */
public class ObjectComposer {
    public ArrayList<Object> componentList = new ArrayList<>();
    public String name;
    public Collider collider;
    public Renderable renderable;
    public UIElement uiElement;
    public Transform transform;

    //region Name, Behaviour, Position, Scale, Collider, Renderable
    public ObjectComposer(String name, float x, float y, float width, float height, Collider collider, Renderable renderable, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.collider = collider;
        componentList.add(collider);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Vector2 position, Vector2 size, Collider collider, Renderable renderable, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.collider = collider;
        componentList.add(collider);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(position, size);
    }
    //endregion

    //region Name, Behaviour, Position, Scale, Collider
    public ObjectComposer(String name, float x, float y, float width, float height, Collider collider, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.collider = collider;
        componentList.add(collider);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Vector2 position, Vector2 size, Collider collider, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.collider = collider;
        componentList.add(collider);
        transform = new Transform(position, size);
    }

    //endregion

    //region Name, Behaviour, Position, Scale, Renderable
    public ObjectComposer(String name, float x, float y, float width, float height, Renderable renderable, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Vector2 position, Vector2 size, Renderable renderable, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(position, size);
    }
    //endregion

    //region Name, Behaviour, Position, Scale, UIElement
    public ObjectComposer(String name, float x, float y, float width, float height, UIElement uiElement, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.uiElement = uiElement;
        componentList.add(uiElement);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Vector2 position, Vector2 size, UIElement uiElement, Script... scripts) {
        this.name = name;
        componentList.addAll(Arrays.asList(scripts));
        this.uiElement = uiElement;
        componentList.add(uiElement);
        transform = new Transform(position, size);
    }
    //endregion

    public GameObject compose() {
        return new GameObject(componentList, name, transform);
    }
}
