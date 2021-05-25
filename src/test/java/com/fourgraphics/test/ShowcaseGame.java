package com.fourgraphics.test;

import com.fourgraphics.*;
import com.fourgraphics.test.scripts.enemies.*;
import com.fourgraphics.test.scripts.generic.FellOff;
import com.fourgraphics.test.scripts.player.*;
import com.fourgraphics.test.scripts.ui.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
    //region Slime
    //region Idle
    public static Animation slimeIdle = new Animation(0.1f, true, "slimeIdle");
    //endregion
    //region Attack
    public static Animation slimeChaseLeft = new Animation(0.08f, true, "slimeChaseLeft");
    public static Animation slimeChaseRight = new Animation(0.08f, true, "slimeChaseRight");
    //endregion
    //endregion
    //region Mage
    //region Idle
    public static Animation playerIdleLeft = new Animation(0.2f, true, "playerIdleLeft");
    public static Animation playerIdleRight = new Animation(0.2f, true, "playerIdleRight");
    //endregion
    //region Run
    public static Animation playerRunLeft = new Animation(0.1f, true, "playerRunLeft");
    public static Animation playerRunRight = new Animation(0.1f, true,"playerRunRight");
    //endregion
    //region Slash
    public static Animation playerSlashLeft = new Animation(0.05f, "playerSlashLeft");
    public static Animation playerSlashRight = new Animation(0.05f, "playerSlashRight");
    //endregion
    //region Throw
    public static Animation playerThrowLeft = new Animation(0.08f, "playerThrowLeft");
    public static Animation playerThrowRight = new Animation(0.08f, "playerThrowRight");
    //endregion
    //region Jump
    public static Animation playerJumpLeft = new Animation(0.08f, "playerJumpLeft");
    public static Animation playerJumpRight = new Animation(0.08f, "playerJumpRight");
    //endregion
    //region Fall
    public static Animation playerFallLeft = new Animation(0.08f, true,"playerFallLeft");
    public static Animation playerFallRight = new Animation(0.08f, true,"playerFallRight");
    //endregion
    //region Damage
    public static Animation playerDamageLeft = new Animation(0.5f,"playerDamageLeft");
    public static Animation playerDamageRight = new Animation(0.5f,"playerDamageRight");
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
        SceneManager.initialize(this, 2);
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
        //TODO(sv-molinari): DOBBIAMO TROVARE UNA SOLUZIONE A QUESTO SCHIFO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
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
            //Slime Idle
            for (int i = 0; i < 5; i++)
            {
                slimeIdle.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Slime/Idle/slime_" + i + ".png")).getPath()));
            }
            //Slime Attack
            for (int i = 0; i < 12; i++)
            {
                if (i < 6)
                    slimeChaseLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Slime/Attack/slime_attack_" + i + ".png")).getPath()));
                else
                    slimeChaseRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Slime/Attack/slime_attack_" + i + ".png")).getPath()));
            }

            //Player Idle
            for (int i = 0; i < 8; i++)
            {
                if (i < 4)
                    playerIdleLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Idle/Player_Idle_" + i + ".png")).getPath()));
                else
                    playerIdleRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Idle/Player_Idle_" + i + ".png")).getPath()));
            }
            //Player Run
            for (int i = 0; i < 8; i++)
            {
                if (i < 4)
                    playerRunRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Run/Player_Run_" + i + ".png")).getPath()));
                else
                    playerRunLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Run/Player_Run_" + i + ".png")).getPath()));
            }
            //Player Slash
            for (int i = 0; i < 10; i++)
            {
                if (i < 5)
                    playerSlashRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Slash/Player_Slash_" + i + ".png")).getPath()));
                else
                    playerSlashLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Slash/Player_Slash_" + i + ".png")).getPath()));
            }
            //Player Throw
            for (int i = 0; i < 8; i++)
            {
                if (i < 4)
                    playerThrowRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Throw/Player_Throw_" + i + ".png")).getPath()));
                else
                    playerThrowLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Throw/Player_Throw_" + i + ".png")).getPath()));
            }
            //Player Jump
            playerJumpRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Jump/Player_Jump_0.png")).getPath()));
            playerJumpLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Jump/Player_Jump_1.png")).getPath()));
            //Player Fall
            for (int i = 0; i < 4; i++)
            {
                if (i < 2)
                    playerFallRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Jump/Player_Fall_" + i + ".png")).getPath()));
                else
                    playerFallLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Jump/Player_Fall_" + i + ".png")).getPath()));
            }
            //Player Damage
            playerDamageRight.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Hit/Player_Hit_0.png")).getPath()));
            playerDamageLeft.addFrame(loadImage(Objects.requireNonNull(classLoader.getResource("Animations/Player/Hit/Player_Hit_1.png")).getPath()));
        } catch (Exception ignored)
        {
            ignored.printStackTrace();
        }

        //SceneManager.addIntroLogo(loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/NSG_Transparent.png")).getPath()));
    }

    public void setup()
    {
        //TODO(samu): prendere il framerate cap da options.ini(/config, DA FARE, tbd), e vedere se è possibile prendere il refresh rate del monitor alla creazione del config base
        SceneManager.setProjectTitle("4GraphiCs Showcase");
        ((PGraphicsOpenGL) g).textureSampling(3);
        MainMenu();
        NiceTutorial();
        FirstLevel();
        SceneManager.startGame();
    }

    void MainMenu()
    {
        PImage title = loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/MainMenu/Title.png")).getPath());

        SceneManager.addScene(new SceneBlueprint()
                .setObjectList(
                        GameObject.Compose(
                                "menu manager",
                                0,0,
                                0,0,
                                new MainMenuManager()
                        ),
                        GameObject.Compose(
                                "Title",
                                0,-350,
                                630,120,
                                new Panel(" ", title,false)
                        ),
                        GameObject.Compose(
                                "play button",
                                0,-75,
                                150,50,
                                new Button(" ", title,false)
                        ),
                        GameObject.Compose(
                                "exit button",
                                0,0,
                                150,50,
                                new Button(" ", title,false)
                        )
                ).setBackground(firstLevelBackground)
        );
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
        PImage terrainWide = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_wide.png")).getPath());
        PImage terrainSquare = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_square.png")).getPath());
        PImage terrainTall = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_tall.png")).getPath());
        PImage terrainDot = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_dot.png")).getPath());
        PImage terrainMWide = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_mWide.png")).getPath());
        PImage terrainMTall = loadImage(Objects.requireNonNull(classLoader.getResource("Images/Terrain/Level_1/terrain_mTall.png")).getPath());

        PImage heartImage = loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Heart.png")).getPath());

        int terrainWidth = 500;
        int terrainHeight = (int) (terrainWidth * 0.3379f);

        int tallHeight = 288;
        int tallWidth = (int)(tallHeight * 0.333333333f);

        int wideWidth = 200;
        int wideHeight = wideWidth/3;

        int playerHeight = 85;
        int playerWidth = (int)(playerHeight * 0.9375f);

        int heartHeight = 60;
        int heartWidth = (int) (heartHeight * 1.16666666f);

        int ultSize = 150;

        int slimeHeight = 54;
        int slimeWidth = (int)(slimeHeight * 2.22222f);

        SceneManager.addScene(new SceneBlueprint()
                .setObjectList(
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*1.5f-tallWidth*3f-25,-terrainWidth/2f+30,
                                tallWidth,tallHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainTall)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*1.5f-tallWidth/2f,-terrainWidth/2f-80,
                                tallWidth,tallHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainTall)
                        ),
                        GameObject.Compose(
                                "terrain",
                                0,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*2 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*3 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*4 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*5 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*6 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*11 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*12 + 450,0,
                                terrainWidth,terrainWidth,
                                new RectCollider(false, false),
                                new Renderer(terrainSquare)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*5 + 450,-terrainWidth/2f-200,
                                wideWidth,wideHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainWide)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*6 + 450,-terrainWidth/2f-350,
                                wideWidth,wideHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainWide)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*7 + 450,-terrainWidth/2f-350,
                                wideWidth,wideHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainWide)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*8 + 450,-terrainWidth/2f-350,
                                wideWidth,wideHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainWide)
                        ),
                        GameObject.Compose(
                                "terrain",
                                terrainWidth*9 + 450,-terrainWidth/2f-350,
                                wideWidth,wideHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainWide)
                        ),
                        GameObject.Compose(
                                "slime",
                                terrainWidth*2.5f + 450, -terrainWidth/2f,
                                slimeWidth, slimeHeight,
                                new RectCollider(true, false),
                                new Animator(slimeIdle.clone(), slimeChaseRight.clone(), slimeChaseLeft.clone()),
                                new Slime(false,false)
                        ),
                        GameObject.Compose(
                                "slime",
                                terrainWidth*4f + 450, -terrainWidth/2f,
                                slimeWidth, slimeHeight,
                                new RectCollider(true, false),
                                new Animator(slimeIdle.clone(), slimeChaseRight.clone(), slimeChaseLeft.clone()),
                                new Slime(true,true)
                        ),
                        GameObject.Compose(
                                "slime",
                                terrainWidth*9 + wideWidth/2.5f + 450,-terrainWidth/2f-370,
                                slimeWidth, slimeHeight,
                                new RectCollider(true, false),
                                new Animator(slimeIdle.clone(), slimeChaseRight.clone(), slimeChaseLeft.clone()),
                                new Slime(true,false)
                        ),
                        GameObject.Compose(
                                "slime",
                                terrainWidth*11.5f + 450, -terrainWidth/2f,
                                slimeWidth, slimeHeight,
                                new RectCollider(true, false),
                                new Animator(slimeIdle.clone(), slimeChaseRight.clone(), slimeChaseLeft.clone()),
                                new Slime(true,true)
                        ),
                        GameObject.Compose(
                                "player",
                                -200,-terrainWidth/2f,
                                playerWidth, playerHeight,
                                new RectCollider(true, false),
                                new Animator(
                                        playerFallLeft.clone(), playerFallRight.clone(),
                                        playerIdleLeft.clone(), playerIdleRight.clone(),
                                        playerRunLeft.clone(), playerRunRight.clone(),
                                        playerJumpLeft.clone(), playerJumpRight.clone(),
                                        playerSlashLeft.clone(), playerSlashRight.clone(),
                                        playerThrowLeft.clone(), playerThrowRight.clone(),
                                        playerDamageLeft.clone(), playerDamageRight.clone()
                                ),
                                new PlayerMovement(),
                                new PlayerCombat()
                        ),
                        GameObject.Compose(
                                "Health 1",
                                -1920/2f+heartWidth/2f+25,-1080/2f+heartHeight/2f+25,
                                heartWidth,heartHeight,
                                new Panel(" ", heartImage, false)
                        ),
                        GameObject.Compose(
                                "Health 2",
                                -1920/2f+heartWidth*2f,-1080/2f+heartHeight/2f+25,
                                heartWidth,heartHeight,
                                new Panel(" ", heartImage, false)
                        ),
                        GameObject.Compose(
                                "Health 3",
                                -1920/2f+heartWidth*2.5f+45,-1080/2f+heartHeight/2f+25,
                                heartWidth,heartHeight,
                                new Panel(" ", heartImage, false)
                        ),
                        GameObject.Compose(
                                "ult indicator",
                                1920/2f-ultSize/2f-25,1080/2f-ultSize/2f-25,
                                ultSize,ultSize,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Ultimate/UltiBar0.png")).getPath()), false),
                                new UltimateIndicator()
                        ),
                        GameObject.Compose(
                                "Death Limit",
                                0,250500,
                                500000,500000,
                                new RectCollider(true,true),
                                new FellOff()
                        )
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

        int playerHeight = 115;
        int playerWidth = (int)(playerHeight * 0.9375f);

        //Ultimate indicator
        int ultSize = 150;


        //Creazione mage
        int mageHeight = 115;
        int mageWidth = (int) (mageHeight * 0.7826f);

        int slimeHeight = 54;
        int slimeWidth = (int)(slimeHeight * 2.22222f);


        SceneManager.addScene(new SceneBlueprint()
                .setObjectList(
                        //region Creazione Terreno
                        GameObject.Compose(
                                "terrain",
                                0, 0,
                                terrainWidth, terrainHeight,
                                new RectCollider(false, false),
                                new Renderer(terrainWide)
                        ),
                        //endregion
                        //region Creazione Personaggio
                        GameObject.Compose(
                                "player",
                                0, -terrainHeight/2f,
                                playerWidth, playerHeight,
                                new RectCollider(true, false),
                                new Animator(
                                        playerFallLeft.clone(), playerFallRight.clone(),
                                        playerIdleLeft.clone(), playerIdleRight.clone(),
                                        playerRunLeft.clone(), playerRunRight.clone(),
                                        playerJumpLeft.clone(), playerJumpRight.clone(),
                                        playerSlashLeft.clone(), playerSlashRight.clone(),
                                        playerThrowLeft.clone(), playerThrowRight.clone(),
                                        playerDamageLeft.clone(), playerDamageRight.clone()
                                ),    //NOTE(samu): god bless varargs
                                new PlayerMovement(), new PlayerCombat()          //NOTE(davide): god bless you for remembering they exist <3
                        ),
                        //endregion
                        //region Cuori UI
                        GameObject.Compose(
                                "Health 1",
                                -1920 / 2f + heartWidth / 2f + 20, -1080 / 2f + hearthHeight / 2f + 20,
                                heartWidth, hearthHeight,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Heart.png")).getPath()), false)
                        ),
                        GameObject.Compose(
                                "Health 2",
                                -1920 / 2f + heartWidth * 1.75f + 20, -1080 / 2f + hearthHeight / 2f + 20,
                                heartWidth, hearthHeight,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("resources/Images/UI/Heart.png")).getPath()), false)
                        ),
                        GameObject.Compose(
                                "Health 3",
                                -1920 / 2f + heartWidth * 3f + 20, -1080 / 2f + hearthHeight / 2f + 20,
                                heartWidth, hearthHeight,
                                new Panel(" ", loadImage(Objects.requireNonNull(classLoader.getResource("Images/UI/Heart.png")).getPath()), false)
                        ),
                        //endregion
                        //region Ultimate Indicator
                        GameObject.Compose(
                                "ult indicator",
                                1920 / 2f - ultSize / 2f - 20, 1080 / 2f - ultSize / 2f - 20,
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
                                new RectCollider(true, false),
                                new Animator(mageIdleLeft.clone(), mageIdleRight.clone(), mageAttackLeft.clone(), mageAttackRight.clone()),
                                new Mage()
                        ),
                        //endregion
                        //region Slime
                        GameObject.Compose(
                                "enemy",
                                -550, -terrainHeight/2f,
                                slimeWidth, slimeHeight,
                                new RectCollider(true, false),
                                new Animator(slimeIdle.clone(), slimeChaseRight.clone(), slimeChaseLeft.clone()),
                                new Slime(true,true)
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
