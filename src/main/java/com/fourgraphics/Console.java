package com.fourgraphics;

import jdk.nashorn.internal.runtime.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class Console
{
    protected JLabel displayedMessage;
    private JPanel consoleBase;
    protected JPanel messageList;
    private JScrollPane scroll;

    public Console()
    {
        JFrame frame = new JFrame("4GraphiCs Console");
        frame.setContentPane(consoleBase);
        frame.pack();
        frame.setVisible(true);
        createUIComponents();
        scroll.setViewportView(messageList);
        consoleBase.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                UpdateMessageList();
            }
        });
    }

    protected void AddMessage(DebugMessage message)
    {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.anchor = GridBagConstraints.CENTER;
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 0;
        constraint.gridy = GridBagConstraints.RELATIVE;
        constraint.weightx = 1.0f;
        constraint.weighty = 1.0f;
        message.setText(message.content);
        message.addActionListener(e -> DebugConsole.UpdateCurrentInfo(message));
        message.setVisible(true);
        messageList.add(message,constraint);
        UpdateMessageList();
    }

    private void UpdateMessageList()
    {
        for(int i = 0; i < messageList.getComponentCount(); i++)
        {
            messageList.getComponent(i).setPreferredSize(new Dimension((int)messageList.getSize().getWidth(),50));
            messageList.getComponent(i).setVisible(true);
        }
    }

    private void createUIComponents()
    {
        messageList = new JPanel(new GridBagLayout());
    }
}
