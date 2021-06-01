package com.fourgraphics.logging;

import com.fourgraphics.logging.internal.*;

public class DebugConsole
{
    private static Console console;

    public static void LaunchConsole()
    {
        console = DebugConsoleInternal.LaunchConsole();
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

    @Deprecated
    public static void Log(DebugMessageType logLevel, String message){
        console.AddMessage(new DebugMessage(logLevel,message));
    }
}
