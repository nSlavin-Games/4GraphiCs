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

    protected static void InfoInternal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,errorName,message,trace));
    }

    protected static void InfoInternal(String errorName, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,errorName,error,trace));
    }

    public static void Error(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,message));
    }

    protected static void ErrorInternal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,errorName,message,trace));
    }

    protected static void ErrorInternal(String errorName, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,errorName,error,trace));
    }

    public static void Warn(String message)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,message));
    }

    protected static void WarnInternal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,errorName,message,trace));
    }

    protected static void WarnInternal(String errorName, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,errorName,error,trace));
    }

    public static void Log(DebugMessageType logLevel, String message){
        console.AddMessage(new DebugMessage(logLevel,message));
    }

    protected static void LogInternal(DebugMessageType logLevel,String errorName,  String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(logLevel,errorName,message,trace));
    }

    protected static void LogInternal(DebugMessageType logLevel,String errorName,  StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(logLevel,errorName,error,trace));
    }
}
