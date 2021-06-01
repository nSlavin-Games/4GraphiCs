package com.fourgraphics.objects;

public abstract class Component
{
    public GameObject gameObject;
    public Transform transform;

    public abstract Component Clone();
}
