package com.fourgraphics.test;

import com.fourgraphics.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet
{
    public static void main(String[] args) {
        String[] appletArgs = { "Test Game" };
        Main sketch = new Main();
        PApplet.runSketch(appletArgs, sketch);
    }

    public void settings()
    {
        size(1000,1000, P3D);
        SceneManager.initialize(this);
        Input.createAxis("Horizontal","d","a","","");
        Input.createButton("Jump","space","w");
        TestScene();
        SceneManager.loadScene(0);
    }

    public void setup()
    {
        //frameRate(1000);
    }

    void TestScene()
    {
        SceneBlueprint scene = new SceneBlueprint();
        ArrayList<Object> componentList = new ArrayList<>();
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        //Creazione Terreno
        Collider coll = new RectCollider(false);
        Renderer renderer = new Renderer(color(0,255,0),DrawType.RECT);
        componentList.add(coll);
        componentList.add(renderer);
        GameObject object = new GameObject(componentList,"terrain");
        object.transform.setScale(300,100);
        gameObjects.add(object);

        //Creazione Personaggio
        componentList = new ArrayList<>();
        coll = new RectCollider(true);
        renderer = new Renderer(color(100),DrawType.RECT);
        PlayerMovement test = new PlayerMovement();
        componentList.add(coll);
        componentList.add(renderer);
        componentList.add(test);
        object = new GameObject(componentList,"player");
        object.transform.setScale(50,75);
        object.transform.setPosition(50,-1000);
        gameObjects.add(object);

        scene.setObjectList(gameObjects);
        SceneManager.addScene(scene);
    }

    public void draw()
    {
        background(180);
        SceneManager.executeScene();
    }
}
