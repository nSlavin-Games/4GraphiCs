package com.fourgraphics.test;

import com.fourgraphics.*;
import com.fourgraphics.utils.ObjectComposer;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class ShowcaseGame extends PApplet
{
    public static Animation fireballAnimation = new Animation(0.1f,true,"fireball");;

    private PImage firstLevelBackground;
    private PImage secondLevelBackground;
    private PImage thirdLevelBackground;
    private PImage fourthLevelBackground;


    public static void main(String[] args) {
        String[] appletArgs = { "Test Game" };
        ShowcaseGame sketch = new ShowcaseGame();
        PApplet.runSketch(appletArgs, sketch);
    }

    public void settings()
    {
        size(1000, 1000, P3D);
        smooth(0);
        SceneManager.initialize(this);
        Input.createAxis("Horizontal", "d", "a", "", "");
        Input.createButton("Jump", "space", "w");
        Input.createButton("Melee", "f", "");
        Input.createButton("Ranged", "g", "");
        TestScene();
        SceneManager.loadScene(0);

        firstLevelBackground = loadImage("../resources/Images/Backgrounds/BG1.png");
        secondLevelBackground = loadImage("../resources/Images/Backgrounds/BG2.png");
        thirdLevelBackground = loadImage("../resources/Images/Backgrounds/BG3.png");
        fourthLevelBackground = loadImage("../resources/Images/Backgrounds/BG4.png");

        try
        {
            for(int i = 0; i < 6; i++)
            {
                fireballAnimation.addFrame(loadImage("../resources/Animations/Fireball/fireball_" + i + ".png"));
            }
        } catch(Exception e){}

    }

    public void setup()
    {
        //frameRate(1000);
    }

    void TestScene() {
        SceneBlueprint scene = new SceneBlueprint();
        ArrayList<Object> componentList = new ArrayList<>();
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        //Creazione Terreno
        Collider coll = new RectCollider(false);
        Renderer renderer = new Renderer(color(0, 255, 0), DrawType.RECT);
        componentList.add(coll);
        componentList.add(renderer);
        GameObject object = new GameObject(componentList, "terrain");
        object.transform.setScale(2000, 100);
        gameObjects.add(object);

        //Creazione Personaggio
        componentList = new ArrayList<>();
        coll = new RectCollider(true);
        renderer = new Renderer(color(100), DrawType.RECT);
        PlayerMovement movement = new PlayerMovement();
        PlayerCombat combat = new PlayerCombat();
        componentList.add(coll);
        componentList.add(renderer);
        componentList.add(movement);
        componentList.add(combat);
        object = new GameObject(componentList, "player");
        object.transform.setScale(50, 75);
        object.transform.setPosition(50, -100);
        gameObjects.add(object);

        //Cuore UI
        componentList = new ArrayList<>();
        Panel panel = new Panel(" ",loadImage("../resources/Images/UI/Heart.png"),false);
        componentList.add(panel);
        object = new GameObject(componentList, "Health 1");
        int hearthHeight = 55;
        int heartWidth = (int)(hearthHeight*1.16666666f);
        object.transform.setScale(heartWidth, hearthHeight);
        object.transform.setPosition(-width/2+heartWidth/2+20, height/2-hearthHeight/2-20);
        gameObjects.add(object);
        object = object.clone();
        object.transform.setPosition(-width / 2 + heartWidth * 1.75f + 20, height / 2 - hearthHeight / 2 - 20);
        object.setName("Health 2");
        gameObjects.add(object);
        object = object.clone();
        object.transform.setPosition(-width / 2 + heartWidth * 3f + 20, height / 2 - hearthHeight / 2 - 20);
        object.setName("Health 3");
        gameObjects.add(object);

        //Creazione Nemico
//        componentList = new ArrayList<>();
//        coll = new RectCollider(true);
//        renderer = new Renderer(color(100), DrawType.RECT);
        Enemy enemy = new Enemy();
//        enemy.type = Enemy.EnemyType.Mage;
//        componentList.add(coll);
//        componentList.add(renderer);
//        componentList.add(enemy);
//        object = new GameObject(componentList, "enemy");
//        object.transform.setScale(50, 75);
//        object.transform.setPosition(550, -100);
//        gameObjects.add(object);
        gameObjects.add(new ObjectComposer(
                "enemy",
                new Enemy(Enemy.EnemyType.Mage),
                550, -100,
                50, 75,
                new RectCollider(true),
                new Renderer(color(100), DrawType.RECT)
        ).compose());
        //Copia nemico
        componentList = new ArrayList<>();
        coll = new RectCollider(true);
        renderer = new Renderer(color(100), DrawType.RECT);
        enemy = new Enemy();
        enemy.type = Enemy.EnemyType.Mage;
        componentList.add(coll);
        componentList.add(renderer);
        componentList.add(enemy);
        object = new GameObject(componentList, "enemy");
        object.transform.setScale(50, 75);
        object.transform.setPosition(-550, -100);
        gameObjects.add(object);

        scene.setObjectList(gameObjects);
        SceneManager.addScene(scene);
    }

    public void draw() {
        Camera mainCamera = SceneManager.getActiveScene().getCamera();
        imageMode(CENTER);
        switch (SceneManager.getActiveSceneIndex())
        {
            case 0:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(),mainCamera.getOffsetPosition().getY());
                image(firstLevelBackground, 0,0,height*1.7777f,height);
                popMatrix();
                break;
            case 2:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(),mainCamera.getOffsetPosition().getY());
                image(secondLevelBackground, 0,0,height*1.7777f,height);
                popMatrix();
                break;
            case 3:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(),mainCamera.getOffsetPosition().getY());
                image(thirdLevelBackground, 0,0,height*1.7777f,height);
                popMatrix();
                break;
            case 4:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(),mainCamera.getOffsetPosition().getY());
                image(fourthLevelBackground, 0,0,height*1.7777f,height);
                popMatrix();
                break;
        }
        SceneManager.executeScene();
    }
}
