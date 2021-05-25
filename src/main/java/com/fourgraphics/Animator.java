package com.fourgraphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Dareisa, Furriolo, Iurcea
 */
public class Animator extends Renderable
{

    private ArrayList<Animation> animationList;//la lista delle animazioni per l'animator
    private Animation currentAnimation;// l'animazione attualmente in riproduzione

    public Animator()
    {//costruttore
        animationList = new ArrayList<>();
        currentAnimation = null;
    }

    public Animator(ArrayList<Animation> animationList)
    {//costruttore
        this.animationList = animationList;
        currentAnimation = null;
        if(this.animationList.size() > 0)
            playAnimation(0);
    }

    public Animator(Animation... animations)
    {//costruttore
        animationList = new ArrayList<>();
        animationList.addAll(Arrays.asList(animations));
        currentAnimation = null;
        if(animationList.size() > 0)
            playAnimation(0);
    }

    protected void render()
    {//Esegue le animazioni in base agli state handler
        if (renderElement && animationList.size() > 0)
        {
            float rx = Rescaler.resizeH(transform.getPosition().getX());
            float ry = Rescaler.resizeH(transform.getPosition().getY());
            float rw = Rescaler.resizeH(transform.getScale().getX());
            float rh = Rescaler.resizeH(transform.getScale().getY());

            currentAnimation.stateHandler();
            sketch.imageMode(sketch.CENTER);
            sketch.image(currentAnimation.frames.get(currentAnimation.getCurrentFrameIndex()), rx, ry, rw, rh);
        }
    }

    public void playAnimation(String name)
    {// ferma l'animazione in corso e fa partire l'animazione in base al nome
        for (Animation animation : animationList)
        {
            if (animation.getName().equals(name))
            {
                currentAnimation = animation;
                currentAnimation.play();
            }
        }
    }

    protected void playAnimation(int index)
    {// ferma l'animazione in corso e fa partire l'animazione in base al nome
        currentAnimation = animationList.get(0);
        currentAnimation.play();
    }

    public Animation getCurrentAnimation()
    {
        return currentAnimation;
    }

    public void stopAnimation()
    {// ferma l'animazione
        currentAnimation.stop();
    }

    public void pauseAnimation()
    {//mette in pausa l'animazione in base al currentAnimation
        currentAnimation.pause();
    }

    public void setAnimationList(ArrayList<Animation> list)
    {// imposta la lista delle animazioni
        animationList = list;
    }

    public void addAnimation(Animation anim)
    {// aggiunge un'animazione alla lista
        animationList.add(anim);
    }

    @Override
    public Animator clone()
    {
        ArrayList<Animation> clonedAnimationList = new ArrayList<>();
        animationList.forEach(animation ->
        {
            clonedAnimationList.add(animation.clone());
        });
        return new Animator(clonedAnimationList);
    }
}
