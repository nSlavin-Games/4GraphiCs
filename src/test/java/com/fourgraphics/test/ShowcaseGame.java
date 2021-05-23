package com.fourgraphics.test;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.Enemies.*;
import com.fourgraphics.test.scripts.Player.*;
import com.fourgraphics.test.scripts.UI.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.opengl.PGraphicsOpenGL;

import java.util.Objects;

public class ShowcaseGame extends PApplet
{
    //region Animations
    //region Ultimate
    public static Animation ultimateLeft = new Animation(0.1f, true, "ultimateLeft");
    public static Animation ultimateRight = new Animation(0.1f, true, "ultimateRight");
    //endregion
    //region Fireball
    public static Animation fireballAnimationLeft = new Animation(0.1f, true, "fireballLeft");
    public static Animation fireballAnimationRight = new Animation(0.1f, true, "fireballRight");
    //endregion
    //region Melee
    public static Animation meleeAnimationRight = new Animation(0.05f, "meleeRight");
    public static Animation meleeAnimationLeft = new Animation(0.05f, "meleeLeft");
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

    ClassLoader classLoader;

    public static void main(String[] args)
    {
        //TODO(samu): settings override fatto bene con args (appendere args a appletArgs e sfruttarlo per le options (file options.ini?))
        String[] appletArgs = {"Test Game"};
        ShowcaseGame sketch = new ShowcaseGame();
        PApplet.runSketch(appletArgs, sketch);
    }


    public void settings()
    {
        classLoader = Thread.currentThread().getContextClassLoader();
        SceneManager.initialize(this,2);
        noSmooth();
        //TODO(samu): usare un file di config per i keybinds e creare un file default se assente
        Input.createAxis("Horizontal", "d", "a", "right", "left");
        Input.createButton("Jump", "space", "up");
        Input.createButton("Melee", "f", "z");
        Input.createButton("Ranged", "g", "x");
        Input.createButton("Ultimate", "c", "c");

        firstLevelBackground = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Backgrounds/BG1.png")).getPath());
        secondLevelBackground = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Backgrounds/BG2.png")).getPath());
        thirdLevelBackground = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Backgrounds/BG3.png")).getPath());
        fourthLevelBackground = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Backgrounds/BG4.png")).getPath());

        //Carica le animazioni
        try
        {
            //Ultimate
            for (int i = 0; i < 10; i++)
            {
                if (i < 5)
                    ultimateRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Ultimate/ultimate_" + i + ".png")).getPath()));
                else
                    ultimateLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Ultimate/ultimate_" + i + ".png")).getPath()));
            }
            //Fireball
            for (int i = 0; i < 12; i++)
            {
                if (i < 6)
                    fireballAnimationLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Fireball/fireball_" + i + ".png")).getPath()));
                else
                    fireballAnimationRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Fireball/fireball_" + i + ".png")).getPath()));
            }
            //Melee
            for (int i = 0; i < 10; i++)
            {
                if (i < 5)
                    meleeAnimationRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Melee_Attack/melee_" + i + ".png")).getPath()));
                else
                    meleeAnimationLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Melee_Attack/melee_" + i + ".png")).getPath()));
            }
            //Mage Idle
            for (int i = 0; i < 12; i++)
            {
                if (i < 6)
                    mageIdleLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Mage/Idle/mage_" + i + ".png")).getPath()));
                else
                    mageIdleRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Mage/Idle/mage_" + i + ".png")).getPath()));
            }

            //Mage Attack
            for (int i = 0; i < 14; i++)
            {
                if (i < 7)
                    mageAttackLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Mage/Attack/mage_attack_" + i + ".png")).getPath()));
                else
                    mageAttackRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Mage/Attack/mage_attack_" + i + ".png")).getPath()));
            }
        } catch (Exception ignored)
        {
        }

        SceneManager.addIntroLogo(loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/NSG_Transparent.png")).getPath()));
    }

    public void setup()
    {
        //TODO(samu): prendere il framerate cap da options.ini(/config, DA FARE, tbd), e vedere se è possibile prendere il refresh rate del monitor alla creazione del config base
        SceneManager.setProjectTitle("4GraphiCs Showcase");
        ((PGraphicsOpenGL) g).textureSampling(3);
        //NiceTutorial();
        FirstLevel();
        SceneManager.startGame();
    }

    void NiceTutorial()
    {
        /*
        ━━╮
        ╰┃ ┣▇━▇
        ┃ ┃ ╰━▅╮
        ╰┳╯ ╰━━┳╯ BRUV KEK😎
        ╰╮ ┳━━╯ SUPER EASY🤙
        ▕▔▋ ╰╮╭━╮NICE TUTORIAL😂
        ▔╲▋╰━┻┻╮╲ ▔▔▔╲
        ▏ ▔▔▔▔▔▔▔ O O┃
        ╲ ▔╲▂▂▂▂ ▔╲▂▂▂
        ▏╳▕▇▇▕ ▏╳▕▇▇▕
        ╲▂ ╲▂ ╲▂
         */
        SceneManager.addScene(new SceneBlueprint(

        ).setBackground(firstLevelBackground));
    }

    void FirstLevel() {
        //Terreno
        PImage terrainWide = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_wide.png")).getPath());
        int terrainWidth = 2000;
        int terrainHeight = (int) (terrainWidth * 0.3379f);

        //Cuori UI
        int hearthHeight = 55;
        int heartWidth = (int) (hearthHeight * 1.16666666f);


        //Ultimate indicator
        int ultSize = 150;


        //Creazione mage
        int mageHeight = 115;
        int mageWidth = (int) (mageHeight * 0.7826f);


        SceneManager.addScene(new SceneBlueprint()
                .setObjectList(
                        //region Creazione Terreno
                        GameObject.Compose(
                                "terrain",
                                0, 0,
                                terrainWidth, terrainHeight,
                                new RectCollider(false),
                                new Renderer(terrainWide)
                        ),
                        //endregion
                        //region Creazione Personaggio
                        GameObject.Compose(
                                "player",
                                0, -terrainHeight/2f,
                                50, 75,
                                new RectCollider(true),
                                new Renderer(color(100), DrawType.RECT),    //NOTE(samu): god bless varargs
                                new PlayerMovement(), new PlayerCombat()          //NOTE(davide): god bless you for remembering they exist <3
                        ),
                        //endregion
                        //region Cuori UI
                        GameObject.Compose(
                                "Health 1",
                                -width / 2f + heartWidth / 2f + 20, -height / 2f + hearthHeight / 2f + 20,
                                heartWidth, hearthHeight,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Heart.png")).getPath()), false)
                        ),
                        GameObject.Compose(
                                "Health 2",
                                -width / 2f + heartWidth * 1.75f + 20, -height / 2f + hearthHeight / 2f + 20,
                                heartWidth, hearthHeight,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("resources/Images/UI/Heart.png")).getPath()), false)
                        ),
                        GameObject.Compose(
                                "Health 3",
                                -width / 2f + heartWidth * 3f + 20, -height / 2f + hearthHeight / 2f + 20,
                                heartWidth, hearthHeight,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Heart.png")).getPath()), false)
                        ),
                        //endregion
                        //region Ultimate Indicator
                        GameObject.Compose(
                                "ult indicator",
                                width / 2f - ultSize / 2f - 20, height / 2f - ultSize / 2f - 20,
                                ultSize, ultSize,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Ultimate/UltiBar0.png")).getPath()), false),
                                new UltimateIndicator()
                        ),
                        //endregion
                        //region Creazione Enemy
                        GameObject.Compose(
                                "enemy",
                                550, -terrainHeight/2f,
                                mageWidth, mageHeight,
                                new RectCollider(true),
                                new Animator(mageIdleLeft.clone(), mageIdleRight.clone(), mageAttackLeft.clone(), mageAttackRight.clone()),
                                new Mage()
                        ),
                        //endregion
                        //region Copia nemico
                        GameObject.Compose(
                                "enemy",
                                -550, -terrainHeight/2f,
                                mageWidth, mageHeight,
                                new RectCollider(true),
                                new Animator(mageIdleLeft.clone(), mageIdleRight.clone(), mageAttackLeft.clone(), mageAttackRight.clone()),
                                new Mage()
                        )
                        //endregion
                )
                .setBackground(firstLevelBackground)
        );
    }

    public void draw()
    {
        SceneManager.executeScene();
    }
}
