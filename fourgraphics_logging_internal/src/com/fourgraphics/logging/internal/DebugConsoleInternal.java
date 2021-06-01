package com.fourgraphics.logging.internal;

public class DebugConsoleInternal {
    private static Console console;

    public static Console LaunchConsole(){
        console = new Console();
        return console;
    }

    public static void InfoInternal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,errorName,message,trace));
    }

    protected static void InfoInternal(String errorName, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.INFO,errorName,error,trace));
    }

    protected static void WarnInternal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,errorName,message,trace));
    }

    protected static void WarnInternal(String errorName, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.WARNING,errorName,error,trace));
    }

    protected static void ErrorInternal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,errorName,message,trace));
    }

    protected static void ErrorInternal(String errorName, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,errorName,error,trace));
    }

    protected static void ErrorInternal(String errorName, String message, StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,errorName,message,error,trace));
    }

    protected static void Fatal(String errorName, String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(DebugMessageType.ERROR,errorName, message, trace));
    }

    @Deprecated
    protected static void LogInternal(DebugMessageType logLevel,String errorName,  String message, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(logLevel,errorName,message,trace));
    }
    @Deprecated
    protected static void LogInternal(DebugMessageType logLevel,String errorName,  StackTraceElement[] error, StackTraceElement[] trace)
    {
        console.AddMessage(new DebugMessage(logLevel,errorName,error,trace));
    }
}
