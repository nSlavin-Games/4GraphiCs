package com.fourgraphics;

import javax.swing.*;
import java.util.Date;

class DebugMessage extends JButton
{
    DebugMessageType messageType; //Tipo di messaggio

    String buttonText;
    String content; //Messaggio da scrivere in console

    Date timeReceived; //quando è stato ricevuto

    String stackTraceInfo; //info prese dalla chiamata del metodo

    int occurrences;

    String callerFile;
    int callerLine;

    public DebugMessage(DebugMessageType type, String message)
    {
        occurrences = 0;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        stackTraceInfo = "Called at: ";
        callerFile = stackTrace[3].getFileName();
        stackTraceInfo += callerFile;
        buttonText = message;
        callerLine = stackTrace[3].getLineNumber();
        stackTraceInfo += "(" + callerLine + ")";
        messageType = type;
        content = message;
        timeReceived = new Date();
    }

    public DebugMessage(DebugMessageType type, String errorName, String message, StackTraceElement[] stackTrace)
    {
        occurrences = 0;
        stackTraceInfo = "Called at: ";
        callerFile = stackTrace[3].getFileName();
        stackTraceInfo += callerFile;
        buttonText = errorName + " " + callerFile;
        callerLine = stackTrace[3].getLineNumber();
        stackTraceInfo += "(" + callerLine + ")";
        messageType = type;
        content = errorName + ": " + message;
        timeReceived = new Date();
    }

    public DebugMessage(DebugMessageType type, String errorName, StackTraceElement[] error, StackTraceElement[] stackTrace)
    {
        occurrences = 0;
        callerFile = error[0].getFileName();
        stackTraceInfo += callerFile;
        callerLine = error[0].getLineNumber();
        stackTraceInfo += "(" + callerLine + ")";
        buttonText = errorName + " " + callerFile;
        content = errorName + System.lineSeparator();
        for(int i = 0; i < error.length; i++)
        {
            content += error[i] + System.lineSeparator();
        }
        stackTraceInfo = "Called at: ";
        stackTraceInfo += callerFile;
        stackTraceInfo += "(" + callerLine + ")";
        messageType = type;
        timeReceived = new Date();
    }

    protected String GetLayoutMessage()
    {
        return "[" + timeReceived.getHours() + ":" + timeReceived.getMinutes() + ":" + timeReceived.getSeconds() + "] " + messageType.toString() + ": " + content + System.lineSeparator() + stackTraceInfo;
    }

    protected String ButtonTextLayout()
    {
        String layout = "[" + timeReceived.getHours() + ":" + timeReceived.getMinutes() + ":" + timeReceived.getSeconds() + "] " + buttonText;
        if(occurrences > 1)
            layout += " | " + occurrences;
        return layout;
    }
}
