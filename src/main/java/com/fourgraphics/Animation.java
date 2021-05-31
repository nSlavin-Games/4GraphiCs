package com.fourgraphics;

import processing.core.PImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Dareisa, Furriolo, Iurcea
 */
public class Animation implements Serializable {
    private final boolean loop;
    private final float frameDelay;
    private final String name;
    protected transient ArrayList<PImage> frames;
    protected ArrayList<String> framesPaths;
    private boolean isPlaying;
    private boolean isEnded;
    private int currentFrame;
    private float timer;
    private File resourcesPath;
    private String fileName;
    private int numberOfFrames;
    private String fileFormat;

    public Animation(float frameDelay, String name) {
        frames = new ArrayList<PImage>();
        framesPaths = new ArrayList<>();
        loop = false;
        isPlaying = false;
        isEnded = false;
        currentFrame = 0;
        this.frameDelay = frameDelay;
        timer = 0;
        this.name = name;
    }

    public Animation(float frameDelay, boolean loop, String name) {
        frames = new ArrayList<PImage>();
        framesPaths = new ArrayList<>();
        this.loop = loop;
        isPlaying = false;
        isEnded = false;
        currentFrame = 0;
        this.frameDelay = frameDelay;
        timer = 0;
        this.name = name;
    }

    public Animation(float frameDelay, boolean loop, String name, File resourcesPath, String fileName, int numberOfFrames, String fileFormat) {
        frames = new ArrayList<PImage>();
        framesPaths = new ArrayList<>();
        this.resourcesPath = resourcesPath;
        this.fileName = fileName;
        this.numberOfFrames = numberOfFrames;
        this.fileFormat = fileFormat;
        this.loop = loop;
        isPlaying = false;
        isEnded = false;
        currentFrame = 0;
        this.frameDelay = frameDelay;
        timer = 0;
        this.name = name;
    }

    protected void stateHandler() {
        if (isPlaying) {
            timer -= SceneManager.deltaTime();
            if (timer <= 0) {
                timer = frameDelay;
                currentFrame++;
                if (currentFrame > frames.size() - 1) {
                    if (loop)
                        currentFrame = 0;
                    else {
                        stop();
                        currentFrame = getFrameAmount() - 1;
                    }
                }
            }
        }
    }

    protected void play() {
        timer = frameDelay;
        isEnded = false;
        isPlaying = true;
        currentFrame = 0;
    }

    protected void stop() {
        isEnded = true;
        isPlaying = false;
    }

    protected void pause() {
        isPlaying = !isPlaying;
    }

    public int getCurrentFrameIndex() {
        return currentFrame;
    }

    public void setFrames(ArrayList<PImage> frames) {
        this.frames = frames;
    }
    public void setFramesFromPaths(ArrayList<String> framesPaths) {
        this.framesPaths = framesPaths;
        for (String framesPath : framesPaths) {
            frames.add(SceneManager.getApp().loadImage(framesPath));
        }
    }

    public void addFrame(PImage frame) {
        frames.add(frame);
    }
    public void addFrame(String path) {
        framesPaths.add(path);
//        PImage img = new PImage(new ImageIcon(path).getImage());
//        img.format = 2;
//        frames.add(img);
        frames.add(SceneManager.getApp().loadImage(path));
        System.out.println(SceneManager.getApp().loadImage(path));
        System.out.println(path);
        frames.forEach(System.out::println);
        framesPaths.forEach(System.out::println);
    }

    public String getName() {
        return name;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public int getFrameAmount() {
        return frames.size();
    }

    protected ArrayList<String> getFramesPaths(){
        return framesPaths;
    }

    //region utils
    public Animation clone() {
        Animation anim = new Animation(frameDelay, loop, name);
        anim.setFrames(frames);
        return anim;
    }

    public Animation[] load(boolean hasSides) {
        Animation[] loadedAnimations = new Animation[0];
        if (hasSides) {
            loadedAnimations = new Animation[2];
            for (int i = 0; i < numberOfFrames; i++) {
                if (i < numberOfFrames / 2)
                    loadedAnimations[0].addFrame(SceneManager.getApp().loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resourcesPath.getPath() + "/" + fileName + i + fileFormat)).getPath()));
                else
                    loadedAnimations[1].addFrame(SceneManager.getApp().loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resourcesPath.getPath() + "/" + fileName + i + fileFormat)).getPath()));
            }
        } else {
            for (int i = 0; i < numberOfFrames; i++) {
                loadedAnimations = new Animation[1];
                loadedAnimations[0].addFrame(SceneManager.getApp().loadImage(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resourcesPath.getPath() + "/" + fileName + i + fileFormat)).getPath()));
            }
        }
        return loadedAnimations;
    }

    //region Caching
    public void serialize() {
//        framesPaths.forEach(System.out::println);
        try {
            File outFile = new File(resourcesPath.getPath() + "/" + fileName + ".sac");
            if (outFile.createNewFile()) {
                FileOutputStream outFileStream = new FileOutputStream(resourcesPath.getPath() + "/" + fileName + ".sac");
                ObjectOutputStream out = new ObjectOutputStream(outFileStream);
                out.writeObject(this);
                out.writeInt(frames.size());
                for (PImage frame : frames) {
                    Image img = (Image) frame.getNative();
                    if (img instanceof BufferedImage)
                        ImageIO.write((BufferedImage) img, "png", out);
                }
                out.close();
            } else {
                DebugConsole.InfoInternal("Animation | Serialization: Animation Cache already exists.", "The current animation is already cached.\nUse Animation.forceSerialize() if you need to regenerate the file.", Thread.currentThread().getStackTrace());
                System.err.println("[INFO] Animation | Serialization: Animation Cache already exists.\nUse Animation.forceSerialize() if you need to regenerate the file.");
            }
        } catch (IOException e) {
            DebugConsole.ErrorInternal("Animation | Serialization Error: Path not found", "The path to the indicated resource is invalid.", e.getStackTrace());
            System.err.println("[ERROR] Animation | Animation Serialization: The path to the indicated resource is invalid.");
            e.printStackTrace();
        }
    }

    public void forceSerialize() {
        try {
            File outFile = new File(resourcesPath.getPath() + fileName + ".sac");
            outFile.createNewFile();
            FileOutputStream outFileStream = new FileOutputStream(resourcesPath.getPath() + "/" + fileName + ".sac");
            ObjectOutputStream out = new ObjectOutputStream(outFileStream);
            out.writeObject(this);
            out.close();
            outFileStream.close();
        } catch (IOException e) {
            DebugConsole.ErrorInternal("Animation | Serialization Error: Path not found", "The path to the indicated resource is invalid.", e.getStackTrace());
            System.err.println("[ERROR] Animation | Animation Serialization: The path to the indicated resource is invalid.");
            e.printStackTrace();
        }
    }
    public static Animation loadCachedAnimation(File path) {
        try {
            FileInputStream inFile = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(inFile);
            Animation loadedAnimation = (Animation) in.readObject();
            loadedAnimation.setFrames(new ArrayList<>());
            int framesNumber = in.readInt();
            for (int i = 0; i < framesNumber; i++) {
                loadedAnimation.addFrame(new PImage(ImageIO.read(in)));
            }
            in.close();
            inFile.close();
//            loadedAnimation.setFramesFromPaths(loadedAnimation.getFramesPaths());

            return loadedAnimation;
        } catch (Exception e) {
            DebugConsole.ErrorInternal("Animation | Deserialization Error: Path not found", "The path to the indicated resource is invalid.", Thread.currentThread().getStackTrace());
            System.err.println("[ERROR] Animation | Animation Deserialization: The path to the indicated resource is invalid.");
            e.printStackTrace();
            return null;
        }
    }


    //endregion
    public File getResourcesPath(){
        return resourcesPath;
    }
    //endregion
}
