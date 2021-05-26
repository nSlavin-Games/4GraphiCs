package com.fourgraphics;

import java.awt.*;
import java.util.ArrayList;

public class DebugConsole
{
    private static Console console;

    protected static void LaunchConsole()
    {
        console = new Console();
        Info("Initializing console...");
        Info("Testing Warning...");
        Warn("Works!");
        Info("Testing Errors...");
        Error("Works!");
    }

    public static void Info(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,message));
    }

    protected static void InfoInternal(String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,message,trace));
    }

    protected static void InfoInternal(StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,error,trace));
    }

    public static void Error(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,message));
    }

    protected static void ErrorInternal(String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,message,trace));
    }

    protected static void ErrorInternal(StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,error,trace));
    }

    public static void Warn(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,message));
    }

    protected static void WarnInternal(String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,message,trace));
    }

    protected static void WarnInternal(StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,error,trace));
    }

    public static void Log(DebugMessageType logLevel, String message){
        console.AddMessage(new DebugMessage(logLevel,message));
    }

    protected static void LogInternal(DebugMessageType logLevel, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(logLevel,message,trace));
    }

    protected static void LogInternal(DebugMessageType logLevel, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(logLevel,error,trace));
    }
}
