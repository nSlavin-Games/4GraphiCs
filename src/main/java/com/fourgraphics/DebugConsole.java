package com.fourgraphics;

import java.awt.*;
import java.util.ArrayList;

public class DebugConsole
{
    private static ArrayList<DebugMessage> messages = new ArrayList<>();

    private static Console console;

    protected static void LaunchConsole()
    {
        console = new Console();
        Info("Console Initialized...");
    }

    public static void Info(String message)
    {
        messages.add(new DebugMessage(DebugMessageType.INFO,message));
        UpdateConsole();
    }

    public static void Error(String message)
    {
        messages.add(new DebugMessage(DebugMessageType.ERROR,message));
        UpdateConsole();
    }

    public static void Warn(String message)
    {
        messages.add(new DebugMessage(DebugMessageType.WARNING,message));
        UpdateConsole();
    }

    public static void Log(DebugMessageType logLevel, String message){
        messages.add(new DebugMessage(logLevel,message));
        UpdateConsole();
    }

    private static void UpdateConsole()
    {
        DebugMessage message = getLastMessage();
        console.AddMessage(getLastMessage());
    }

    private static DebugMessage getLastMessage()
    {
         return messages.get(messages.size()-1);
    }

    protected static void UpdateCurrentInfo(DebugMessage message)
    {
        console.displayedMessage.setText(message.GetLayoutMessage());
    }
}
