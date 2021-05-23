package com.fourgraphics;

class Rescaler
{
    public final static float DEFAULT_WIDTH = 1920;
    public final static float DEFAULT_HEIGHT = 1080;

    public static float currentWidth;
    public static float currentHeight;

    public static void setSize(float w, float h)
    {
        currentWidth = w;
        currentHeight = h;
    }

    public static float resizeH(float value)
    {
        return (value * currentHeight) / DEFAULT_HEIGHT;
    }

    public static float resizeW(float value)
    {
        return (value * currentWidth) / DEFAULT_WIDTH;
    }
}
