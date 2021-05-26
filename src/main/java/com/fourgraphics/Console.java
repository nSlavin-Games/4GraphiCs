package com.fourgraphics;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

class Console
{
    private JFrame console;
    private JPanel consoleBase;
    protected JPanel messageList;
    private JScrollPane scroll;
    private JPanel infoPanel;
    private JTextArea displayMessage;
    GridBagConstraints constraint = new GridBagConstraints();
    JPanel filler = new JPanel();

    private ArrayList<DebugMessage> existingMessages = new ArrayList<>();

    public Console()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}
        console = new JFrame("4GraphiCs Console");
        consoleBase.setBackground(Color.getHSBColor(180,0.042f,0.094f));
        console.setContentPane(consoleBase);
        console.pack();
        console.setVisible(true);
        createUIComponents();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportView(messageList);
        scroll.getViewport().getView().setBackground(Color.getHSBColor(180,0.042f,0.094f));

        Border border = BorderFactory.createLineBorder(Color.getHSBColor(0,0.813f,0.42f),5);
        infoPanel.setBorder(border);
        console.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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


        GridBagConstraints fillerConstraints = new GridBagConstraints();
        filler.setOpaque(false);
        fillerConstraints.weightx = 2f;
        fillerConstraints.weighty = 2f;
        fillerConstraints.gridy = 100;
        messageList.add(filler, fillerConstraints);
    }

    protected void AddMessage(DebugMessage message)
    {
        //System.out.println(existingMessages.size());
        if(existingMessages.size() == 0)
        {
            message.occurencies++;
            existingMessages.add(message);
            CreateMessage(message);
        }
        else
        {
            boolean found = false;
            for(int i = existingMessages.size() -1; i >= 0; i--)
            {
                if(existingMessages.get(i).content.equalsIgnoreCase(message.content) && existingMessages.get(i).messageType == message.messageType)
                {
                    existingMessages.get(i).occurencies++;
                    existingMessages.get(i).setText(existingMessages.get(i).content + " | " + existingMessages.get(i).occurencies);
                    found = true;
                }
            }
            if(!found)
            {
                message.occurencies++;
                existingMessages.add(message);
                CreateMessage(message);
            }
        }
    }

    private void CreateMessage(DebugMessage message)
    {
        if(constraint.gridy == 99)
            messageList.remove(filler);
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.weightx = 1.0f;
//        constraint.weighty = 0.1f;
        constraint.insets = new Insets(0,0,0,0);

        Icon logIcon = new ImageIcon();
        switch (message.messageType)
        {
            case INFO:
                logIcon = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("consoleInfo.png")));
                break;
            case ERROR:
                logIcon = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("consoleError.png")));
                break;
            case WARNING:
                logIcon = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("consoleWarning.png")));
                break;
        }
        message.setIcon(logIcon);
        message.setText(message.content);
        message.addActionListener(e -> UpdateCurrentInfo(message));
        message.setHorizontalAlignment(SwingConstants.LEADING);
        message.setVisible(true);
        messageList.add(message, constraint);
    }

    private void UpdateMessageList()
    {
        for (int i = 0; i < messageList.getComponentCount(); i++)
        {
            messageList.getComponent(i).setPreferredSize(new Dimension(console.getBounds().width, 50));
            messageList.getComponent(i).setSize(new Dimension(console.getBounds().width, 50));
            messageList.getComponent(i).setVisible(true);
//            ((JButton)messageList.getComponent(i)).setVerticalAlignment(JButton.TOP);
        }
        messageList.revalidate();
    }

    private void createUIComponents()
    {
        messageList = new JPanel(new GridBagLayout());

    }

    private void UpdateCurrentInfo(DebugMessage message)
    {
        displayMessage.setText(message.GetLayoutMessage());
    }
}
