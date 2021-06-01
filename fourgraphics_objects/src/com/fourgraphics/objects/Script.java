package com.fourgraphics.objects;

public abstract class Script extends Component
{
    public void Start() {}

    public abstract void Update();

    public void FixedUpdate() {}

    public Script Clone()
    {
        return this;
    }
}
