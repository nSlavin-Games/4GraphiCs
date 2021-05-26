package com.fourgraphics;

import java.awt.*;
import java.util.ArrayList;

public class DebugConsole
{
    private static Console console;

    protected static void LaunchConsole()
    {
        Console.main(new String[0]);
        console = Console.Instance;
        Info("Console Initialized...");
    }

    public static void Info(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,message));
    }

    public static void Error(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,message));
    }

    public static void Warn(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,message));
    }

    public static void Log(DebugMessageType logLevel, String message){
        console.AddMessage(new DebugMessage(logLevel,message));
    }
}
