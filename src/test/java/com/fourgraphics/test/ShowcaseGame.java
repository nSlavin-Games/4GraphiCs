package com.fourgraphics.test;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.Enemies.Mage;
import com.fourgraphics.test.scripts.Player.PlayerCombat;
import com.fourgraphics.test.scripts.Player.PlayerMovement;
import com.fourgraphics.test.scripts.UI.Intro;
import com.fourgraphics.utils.ObjectComposer;
import processing.core.PApplet;
import processing.core.PImage;
import processing.opengl.PGraphicsOpenGL;

import java.util.ArrayList;

public class ShowcaseGame extends PApplet {
    //region Animations
    //region Fireball
    public static Animation fireballAnimationLeft = new Animation(0.1f, true, "fireballLeft");
    public static Animation fireballAnimationRight = new Animation(0.1f, true, "fireballRight");
    //endregion
    //region Melee
    public static Animation meleeAnimationRight = new Animation(0.1f, "meleeRight");
    public static Animation meleeAnimationLeft = new Animation(0.1f, "meleeLeft");
    //endregion
    //region Mage
    //region Idle
    public static Animation mageIdleLeft = new Animation(0.1f, true, "mageIdleLeft");
    public static Animation mageIdleRight = new Animation(0.1f, true, "mageIdleRight");
    //endregion
    //region Attack
    public static Animation mageAttackLeft = new Animation(0.08f, "mageAttackLeft");
    public static Animation mageAttackRight = new Animation(0.08f, "mageAttackRight");
    //endregion
    //endregion
    //endregion

    private PImage firstLevelBackground;
    private PImage secondLevelBackground;
    private PImage thirdLevelBackground;
    private PImage fourthLevelBackground;

    public static void main(String[] args) {
        //TODO(samu): settings override fatto bene con args (appendere args a appletArgs e sfruttarlo per le options (file options.ini?))
        String[] appletArgs = {"Test Game"};
        ShowcaseGame sketch = new ShowcaseGame();
        PApplet.runSketch(appletArgs, sketch);
    }


    public void settings() {
        fullScreen(P3D, 2);
        noSmooth();
        SceneManager.initialize(this);
        //TODO(samu): usare un file di config per i keybinds e creare un file default se assente
        Input.createAxis("Horizontal", "d", "a", "", "");
        Input.createButton("Jump", "space", "");
        Input.createButton("Melee", "f", "");
        Input.createButton("Ranged", "g", "");

        firstLevelBackground = loadImage("../resources/Images/Backgrounds/BG1.png");
        secondLevelBackground = loadImage("../resources/Images/Backgrounds/BG2.png");
        thirdLevelBackground = loadImage("../resources/Images/Backgrounds/BG3.png");
        fourthLevelBackground = loadImage("../resources/Images/Backgrounds/BG4.png");

        //Carica le animazioni
        try {
            //Fireball
            for (int i = 0; i < 12; i++) {
                if (i < 6)
                    fireballAnimationLeft.addFrame(loadImage("../resources/Animations/Fireball/fireball_" + i + ".png"));
                else
                    fireballAnimationRight.addFrame(loadImage("../resources/Animations/Fireball/fireball_" + i + ".png"));
            }
            //Melee
            for (int i = 0; i < 10; i++) {
                if (i < 5)
                    meleeAnimationRight.addFrame(loadImage("../resources/Animations/Melee Attack/melee_" + i + ".png"));
                else
                    meleeAnimationLeft.addFrame(loadImage("../resources/Animations/Melee Attack/melee_" + i + ".png"));
            }
            //Mage Idle
            for (int i = 0; i < 12; i++) {
                if (i < 6)
                    mageIdleLeft.addFrame(loadImage("../resources/Animations/Mage/Idle/mage_idle_" + i + ".png"));
                else
                    mageIdleRight.addFrame(loadImage("../resources/Animations/Mage/Idle/mage_idle_" + i + ".png"));
            }

            //Mage Attack
            for (int i = 0; i < 14; i++) {
                if (i < 7)
                    mageAttackLeft.addFrame(loadImage("../resources/Animations/Mage/Attack/mage_attack_" + i + ".png"));
                else
                    mageAttackRight.addFrame(loadImage("../resources/Animations/Mage/Attack/mage_attack_" + i + ".png"));
            }
        } catch (Exception ignored) {
        }
    }

    public void setup() {
        //TODO(samu): prendere il framerate cap da options.ini(/config, DA FARE, tbd), e vedere se è possibile prendere il refresh rate del monitor alla creazione del config base
        //frameRate(1000);
        ((PGraphicsOpenGL) g).textureSampling(3);
        Intro();
        FirstLevel();
        SceneManager.loadScene(0);
    }

    void Intro() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        int logoSize = height / 2;

        gameObjects.add(new ObjectComposer(
                "4GC Logo",
                0, 0,
                logoSize, logoSize,
                new Renderer(loadImage("../resources/Images/UI/4GC Logo.png")),
                new Intro()
        ).compose());

        SceneBlueprint scene = new SceneBlueprint();
        scene.setObjectList(gameObjects);
        SceneManager.addScene(scene);
    }

    void FirstLevel() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        PImage terrainWide = loadImage("../resources/Images/Terrain/Level 1/terrain_wide.png");

        int terrainWidth = 2000;
        int terrainHeight = (int) (terrainWidth * 0.3379f);

        //Creazione Terreno
        gameObjects.add(new ObjectComposer(
                "terrain",
                0, 0,
                terrainWidth, terrainHeight,
                new RectCollider(false),
                new Renderer(terrainWide)
        ).compose());

        //Creazione Personaggio
        gameObjects.add(new ObjectComposer(
                "player",
                50, -100,
                50, 75,
                new RectCollider(true),
                new Renderer(color(100), DrawType.RECT),
                new PlayerMovement(), new PlayerCombat()    //NOTE(samu): god bless varargs
        ).compose());                                       //NOTE(davide): god bless you for remembering they exist <3

        //Cuori UI
        int hearthHeight = 55;
        int heartWidth = (int) (hearthHeight * 1.16666666f);
        gameObjects.add(new ObjectComposer(
                "Health 1",
                -width / 2f + heartWidth / 2f + 20, -height / 2f + hearthHeight / 2f + 20,
                heartWidth, hearthHeight,
                new Panel(" ", loadImage("../resources/Images/UI/Heart.png"), false)
        ).compose());

        gameObjects.add(new ObjectComposer(
                "Health 2",
                -width / 2f + heartWidth * 1.75f + 20, -height / 2f + hearthHeight / 2f + 20,
                heartWidth, hearthHeight,
                new Panel(" ", loadImage("../resources/Images/UI/Heart.png"), false)
        ).compose());

        gameObjects.add(new ObjectComposer(
                "Health 3",
                -width / 2f + heartWidth * 3f + 20, -height / 2f + hearthHeight / 2f + 20,
                heartWidth, hearthHeight,
                new Panel(" ", loadImage("../resources/Images/UI/Heart.png"), false)
        ).compose());

        //Creazione mage
        int mageHeight = 115;
        int mageWidth = (int) (mageHeight * 0.7826f);
        gameObjects.add(new ObjectComposer(
                "enemy",
                550, -100,
                mageWidth, mageHeight,
                new RectCollider(true),
                new Animator(mageIdleLeft.clone(), mageIdleRight.clone(), mageAttackLeft.clone(), mageAttackRight.clone()),
                new Mage()
        ).compose());

        //Copia nemico
        gameObjects.add(new ObjectComposer(
                "enemy",
                -550, -100,
                mageWidth, mageHeight,
                new RectCollider(true),
                new Animator(mageIdleLeft.clone(), mageIdleRight.clone(), mageAttackLeft.clone(), mageAttackRight.clone()),
                new Mage()
        ).compose());

        SceneBlueprint scene = new SceneBlueprint();
        scene.setObjectList(gameObjects);
        SceneManager.addScene(scene);
    }

    public void draw() {
        Camera mainCamera = SceneManager.getActiveScene().getCamera();
        imageMode(CENTER);
        /*FIXME(samu): questa roba non dovrebbe stare in SceneManager.executeScene()/SceneBlueprint?
           Tecnicamente stiamo impostando lo sfondo e la camera di Processing...
           */
        switch (SceneManager.getActiveSceneIndex()) {
            case 0:
                background(23, 24, 24);
                break;
            case 1:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(), mainCamera.getOffsetPosition().getY());
                image(firstLevelBackground, 0, 0, height * 1.7777f, height);
                popMatrix();
                break;

            case 4:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(), mainCamera.getOffsetPosition().getY());
                image(secondLevelBackground, 0, 0, height * 1.7777f, height);
                popMatrix();
                break;

            case 6:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(), mainCamera.getOffsetPosition().getY());
                image(thirdLevelBackground, 0, 0, height * 1.7777f, height);
                popMatrix();
                break;

            case 8:
                pushMatrix();
                translate(mainCamera.getOffsetPosition().getX(), mainCamera.getOffsetPosition().getY());
                image(fourthLevelBackground, 0, 0, height * 1.7777f, height);
                popMatrix();
                break;
        }
        SceneManager.executeScene();
    }
}
