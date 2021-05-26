package com.fourgraphics;

import javax.swing.*;
import java.util.Date;

class DebugMessage extends JButton
{
    DebugMessageType messageType; //Tipo di messaggio

    String content; //Messaggio da scrivere in console

    Date timeReceived; //quando è stato ricevuto

    String stackTraceInfo; //info prese dalla chiamata del metodo

    int occurencies;

    public DebugMessage(DebugMessageType type, String message)
    {
        occurencies = 0;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        stackTraceInfo = "Called at: ";
        stackTraceInfo += stackTrace[3].getClassName();
        stackTraceInfo += "(" + stackTrace[3].getLineNumber() + ")";
        messageType = type;
        content = message;
        timeReceived = new Date();
    }

    public DebugMessage(DebugMessageType type, String message, StackTraceElement[] stackTrace)
    {
        occurencies = 0;
        stackTraceInfo = "Called at: ";
        stackTraceInfo += stackTrace[3].getClassName();
        stackTraceInfo += "(" + stackTrace[3].getLineNumber() + ")";
        messageType = type;
        content = message;
        timeReceived = new Date();
    }

    public DebugMessage(DebugMessageType type, StackTraceElement[] error, StackTraceElement[] stackTrace)
    {
        occurencies = 0;
        content = "Unknown Error: Check your code at " + error[0].getClassName() + "(" + error[0].getLineNumber() + ")";
        stackTraceInfo = "Called at: ";
        stackTraceInfo += error[0].getClassName();
        stackTraceInfo += "(" + error[0].getLineNumber() + ")";
        messageType = type;
        timeReceived = new Date();
    }

    protected String GetLayoutMessage()
    {
        return "[" + timeReceived.getHours() + ":" + timeReceived.getMinutes() + ":" + timeReceived.getSeconds() + "] " + messageType.toString() + ": " + content + System.lineSeparator() + stackTraceInfo;
    }
}
