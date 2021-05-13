package com.fourgraphics;

import java.util.ArrayList;

/**
 * @author Dareisa, Furriolo, Iurcea
 *
 */
public class Animator extends Renderable {

	private ArrayList<Animation> animationList ;//la lista delle animazioni per l�animator


	private Animation currentAnimation ;// l�animazione attualmente in riproduzione


	public Animator(){//costruttore

		animationList =new ArrayList<>();

		currentAnimation=null;

	}



	protected void render(){//Esegue le animazioni in base agli state handler
		currentAnimation.stateHandler();
		sketch.imageMode(sketch.CENTER);
		sketch.image(currentAnimation.frames.get(currentAnimation.getCurrentFrameIndex()), transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());

	}


	public void playAnimation(String name) {// ferma l�animazione in corso e fa partire l�animazione in base al nome

		for(int i=0;i<animationList.size();i++) {

			if(animationList.get(i).getName().equals(name) ) {

				currentAnimation=animationList.get(i);

				currentAnimation.play();

			}

		}

	}


	public void stopAnimation() {// ferma l�animazione

		currentAnimation.stop();

	}


	public void pauseAnimation() {//mette in pausa l�animazione in base al currentAnimation

		currentAnimation.pause();

	}


	public void setAnimationList(ArrayList<Animation> list) {// imposta la lista delle animazioni

		animationList = list; 	

	}


	public void addAnimation(Animation anim) {// aggiunge un�animazione alla lista

		animationList.add(anim);

	}


}
