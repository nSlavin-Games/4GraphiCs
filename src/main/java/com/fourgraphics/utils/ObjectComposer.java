package com.fourgraphics.utils;

import com.fourgraphics.*;

import java.util.ArrayList;

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
    public Script behaviour;
    public Transform transform;
    ArrayList<Script> additionalScripts = new ArrayList<>();

    //region Name, Behaviour, Position, Scale, Collider, Renderable
    public ObjectComposer(String name, Script behaviour, float x, float y, float width, float height, Collider collider, Renderable renderable) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.collider = collider;
        componentList.add(collider);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Script behaviour, Vector2 position, Vector2 size, Collider collider, Renderable renderable) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.collider = collider;
        componentList.add(collider);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(position, size);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, float x, float y, float width, float height, Collider collider, Renderable renderable) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.collider = collider;
        componentList.add(collider);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, Vector2 position, Vector2 size, Collider collider, Renderable renderable) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.collider = collider;
        componentList.add(collider);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(position, size);
    }
    //endregion

    //region Name, Behaviour, Position, Scale, Collider
    public ObjectComposer(String name, Script behaviour, float x, float y, float width, float height, Collider collider) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.collider = collider;
        componentList.add(collider);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Script behaviour, Vector2 position, Vector2 size, Collider collider) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.collider = collider;
        componentList.add(collider);
        transform = new Transform(position, size);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, float x, float y, float width, float height, Collider collider) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.collider = collider;
        componentList.add(collider);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, Vector2 position, Vector2 size, Collider collider) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.collider = collider;
        componentList.add(collider);
        transform = new Transform(position, size);
    }
    //endregion

    //region Name, Behaviour, Position, Scale, Renderable
    public ObjectComposer(String name, Script behaviour, float x, float y, float width, float height, Renderable renderable) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Script behaviour, Vector2 position, Vector2 size, Renderable renderable) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(position, size);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, float x, float y, float width, float height, Renderable renderable) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, Vector2 position, Vector2 size, Renderable renderable) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.renderable = renderable;
        componentList.add(renderable);
        transform = new Transform(position, size);
    }
    //endregion

    //region Name, Behaviour, Position, Scale, UIElement
    public ObjectComposer(String name, Script behaviour, float x, float y, float width, float height, UIElement uiElement) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.uiElement = uiElement;
        componentList.add(uiElement);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, Script behaviour, Vector2 position, Vector2 size, UIElement uiElement) {
        this.name = name;
        this.behaviour = behaviour;
        componentList.add(behaviour);
        this.uiElement = uiElement;
        componentList.add(uiElement);
        transform = new Transform(position, size);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, float x, float y, float width, float height, UIElement uiElement) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.uiElement = uiElement;
        componentList.add(uiElement);
        transform = new Transform(x, y, width, height);
    }

    public ObjectComposer(String name, ArrayList<Script> scripts, Vector2 position, Vector2 size, UIElement uiElement) {
        this.name = name;
        this.additionalScripts = scripts;
        componentList.add(additionalScripts);
        this.uiElement = uiElement;
        componentList.add(uiElement);
        transform = new Transform(position, size);
    }
    //endregion

    public GameObject compose() {
        return new GameObject(componentList, name, transform);
    }
}
