package com.fourgraphics;

import javax.swing.*;
import java.util.Date;

class DebugMessage extends JButton
{
    DebugMessageType messageType; //Tipo di messaggio

    String content; //Messaggio da scrivere in console

    Date timeReceived; //quando è stato ricevuto

    public DebugMessage(DebugMessageType type, String message)
    {
        messageType = type;
        content = message;
        timeReceived = new Date();
    }

    protected String GetLayoutMessage()
    {
        return "[" + timeReceived.getHours() + ":" + timeReceived.getMinutes() + ":" + timeReceived.getSeconds() + "] " + messageType.toString() + ": " + content;
    }
}
