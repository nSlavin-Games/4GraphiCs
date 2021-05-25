package com.fourgraphics;

import jdk.nashorn.internal.runtime.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.Objects;

class Console
{
    private JFrame console;
    protected JLabel displayedMessage;
    private JPanel consoleBase;
    protected JPanel messageList;
    private JScrollPane scroll;

    public Console()
    {
        console = new JFrame("4GraphiCs Console");
        console.setContentPane(consoleBase);
        console.pack();
        console.setVisible(true);
        createUIComponents();
        scroll.setViewportView(messageList);
        consoleBase.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                UpdateMessageList();
            }
        });

        messageList.addContainerListener(new ContainerListener()
        {
            @Override
            public void componentAdded(ContainerEvent e)
            {
                UpdateMessageList();
            }

            @Override
            public void componentRemoved(ContainerEvent e)
            {
                UpdateMessageList();
            }
        });
    }

    protected void AddMessage(DebugMessage message)
    {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.anchor = GridBagConstraints.NORTH;
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 0;
        constraint.gridy = GridBagConstraints.RELATIVE;
        constraint.weightx = 1.0f;
        constraint.weighty = 1.0f;
        Icon logIcon = new ImageIcon();
        switch (message.messageType)
        {
            case INFO:
                logIcon = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("consoleInfo.png")));
                break;
            case ERROR:
                break;
            case WARNING:
                break;
        }
        message.setIcon(logIcon);
        message.setText(message.content);
        message.addActionListener(e -> UpdateCurrentInfo(message));
        message.setPreferredSize(new Dimension(messageList.getBounds().width, 50));
        message.setSize(new Dimension(messageList.getBounds().width, 50));
        message.setVisible(true);
        messageList.add(message, constraint);
    }

    private void UpdateMessageList()
    {
        for (int i = 0; i < messageList.getComponentCount(); i++)
        {
            messageList.getComponent(i).setPreferredSize(new Dimension(console.getBounds().width - 5, 50));
            messageList.getComponent(i).setSize(new Dimension(console.getBounds().width - 5, 50));
            messageList.getComponent(i).setVisible(true);
        }
        messageList.revalidate();
    }

    private void createUIComponents()
    {
        messageList = new JPanel(new GridBagLayout());
    }

    private void UpdateCurrentInfo(DebugMessage message)
    {
        displayedMessage.setText(message.GetLayoutMessage());
    }
}
