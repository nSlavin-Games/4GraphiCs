package com.fourgraphics.objects;

import com.fourgraphics.math.Vector3;

public class Transform
{
    /**
     * The object this transform is attached to
     */
    public GameObject gameObject;

    /**
     * Object's position in the world
     */
    public Vector3 position;

    /**
     * Object's position relative to it's parent
     */
    public Vector3 localPosition;

    /**
     * Object's scale in the world
     */
    public Vector3 scale;

    /**
     * Object's scale relative to it's parent
     */
    public Vector3 localScale;

    //TODO: Implement rotations with -> QUATERNIONS <-
}
