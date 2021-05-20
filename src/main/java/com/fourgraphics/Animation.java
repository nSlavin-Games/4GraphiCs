/**
 * 
 */
package com.fourgraphics;

import java.util.ArrayList;

import com.fourgraphics.SceneManager;

import processing.core.PImage;

/**
 * @author Dareisa, Furriolo, Iurcea
 *
 */
public class Animation {
	protected ArrayList<PImage>frames;
	private boolean loop;
	private boolean isPlaying;
	private boolean isEnded;
	private int currentFrame;
	private float frameDelay;
	private float timer;
	private String name;

	public Animation(float frameDelay, String name) {
		frames=new ArrayList<PImage>();
		loop=false;
		isPlaying=false;
		isEnded=false;
		currentFrame=0;
		this.frameDelay=frameDelay;
		timer=0;
		this.name=name;
	}

	public Animation(float frameDelay, boolean loop, String name) {
		frames=new ArrayList<PImage>();
		this.loop=loop;
		isPlaying=false;
		isEnded=false;
		currentFrame=0;
		this.frameDelay=frameDelay;
		timer=0;
		this.name=name;
	}

	protected void stateHandler() {
		if(isPlaying) {
			timer-= SceneManager.deltaTime();
			if(timer<=0) {
				timer=frameDelay;
				currentFrame++;
				if(currentFrame>frames.size()-1) {
					if(loop)
						currentFrame=0;
					else
						stop();
				}
			}
		}
	}

	protected void play() {
		timer=frameDelay;
		isEnded=false;
		isPlaying=true;
		currentFrame=0;
	}

	protected void stop() {
		isEnded=true;
		isPlaying=false;
		currentFrame=0;
	}

	protected void pause() {
		isPlaying=!isPlaying;
	}

	public int getCurrentFrameIndex() {
		return currentFrame;
	}

	public void setFrames(ArrayList<PImage>frames) {
		this.frames=frames;
	}

	public void addFrame(PImage frame) {
		frames.add(frame);
	}

	public String getName() {
		return name;
	}
	
	public boolean isEnded() {
		return isEnded;
	}

	public int getFrameAmount() { return frames.size(); }

	public Animation clone()
	{
		Animation anim = new Animation(frameDelay,loop,name);
		anim.setFrames(frames);
		return anim;
	}
}
