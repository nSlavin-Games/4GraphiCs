package com.fourgraphics.logging.internal;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.*;
import static java.util.Objects.*;

public class Console
{
    private JFrame console;
    private JPanel consoleBase;
    protected JPanel messageList;
    private JScrollPane scroll;
    private JPanel infoPanel;
    private JTextArea displayMessage;
    private JScrollPane infoScroll;
    private JSplitPane splitPane;
    GridBagConstraints constraint = new GridBagConstraints();
    JPanel filler = new JPanel();

    private DebugMessage currentMessage;

    private ArrayList<DebugMessage> existingMessages = new ArrayList<>();


    public Console()
    {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}
        console = new JFrame("4GraphiCs Console");
        consoleBase.setBackground(Color.getHSBColor(180,0.042f,0.094f));
        console.setContentPane(consoleBase);
        console.setAlwaysOnTop(true);
        console.pack();
        console.setMinimumSize(new Dimension(400,400));
        console.setVisible(true);
        console.setIconImage(new ImageIcon(requireNonNull(currentThread().getContextClassLoader().getResource("4GC_Logo.png")).getPath()).getImage());
        splitPane.setDividerLocation(500);

        infoScroll.setMinimumSize(new Dimension(400,50));

        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportView(messageList);
        scroll.getViewport().getView().setBackground(Color.getHSBColor(180,0.042f,0.094f));

        infoScroll.setBorder(BorderFactory.createEmptyBorder());
        infoScroll.setViewportView(displayMessage);
        infoScroll.getViewport().getView().setBackground(Color.getHSBColor(180,0.042f,0.094f));

        Border border = BorderFactory.createLineBorder(Color.getHSBColor(0,0.813f,0.42f),5);
        infoPanel.setBorder(border);
        console.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        consoleBase.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                UpdateMessageList();
                UpdateCurrentInfo();
            }
        });

        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                UpdateMessageList();
                UpdateCurrentInfo();
            }
        });

        messageList.addContainerListener(new ContainerListener()
        {
            @Override
            public void componentAdded(ContainerEvent e)
            {
                UpdateMessageList();
                UpdateCurrentInfo();
            }

            @Override
            public void componentRemoved(ContainerEvent e)
            {
                UpdateMessageList();
                UpdateCurrentInfo();
            }
        });


        GridBagConstraints fillerConstraints = new GridBagConstraints();
        filler.setOpaque(false);
        fillerConstraints.weightx = 2f;
        fillerConstraints.weighty = 2f;
        fillerConstraints.gridy = 100;
        messageList.add(filler, fillerConstraints);
    }

    public void AddMessage(DebugMessage message)
    {
        //System.out.println(existingMessages.size());
        if(existingMessages.size() == 0)
        {
            message.occurrences++;
            existingMessages.add(message);
            CreateMessage(message);
        }
        else
        {
            boolean found = false;
            for(int i = existingMessages.size() -1; i >= 0; i--)
            {
                if(existingMessages.get(i).content.equalsIgnoreCase(message.content) && existingMessages.get(i).messageType == message.messageType
                && existingMessages.get(i).callerFile.equalsIgnoreCase(message.callerFile) && existingMessages.get(i).callerLine == message.callerLine)
                {
                    existingMessages.get(i).occurrences++;
                    existingMessages.get(i).setText(existingMessages.get(i).ButtonTextLayout());
                    existingMessages.get(i).timeReceived = new Date();
                    found = true;
                }
            }
            if(!found)
            {
                message.occurrences++;
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
                logIcon = new ImageIcon(requireNonNull(currentThread().getContextClassLoader().getResource("consoleInfo.png")));
                break;
            case ERROR:
                logIcon = new ImageIcon(requireNonNull(currentThread().getContextClassLoader().getResource("consoleError.png")));
                break;
            case WARNING:
                logIcon = new ImageIcon(requireNonNull(currentThread().getContextClassLoader().getResource("consoleWarning.png")));
                break;
        }
        message.setIcon(logIcon);
        message.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(107,20,20)));
        message.setText(message.ButtonTextLayout());
        message.addActionListener(e -> {
                currentMessage = message;
                UpdateCurrentInfo();
        });
        message.setHorizontalAlignment(SwingConstants.LEADING);
        message.setVisible(true);
        message.setBackground(new Color(23,24,24));
        message.setForeground(Color.decode("#e5e5e5"));
        message.addPropertyChangeListener(JButton.TEXT_CHANGED_PROPERTY, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                message.setBackground(new Color(107,20,20));
                Timer timer = new Timer(350, new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        message.setBackground(new Color(23, 24, 24));
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        if(currentMessage == null)
        {
            currentMessage = message;
            UpdateCurrentInfo();
        }

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

    private void UpdateCurrentInfo()
    {
        if(currentMessage != null)
        {
            displayMessage.setText(currentMessage.GetLayoutMessage());
            int lineAmount = currentMessage.GetLayoutMessage().split(System.lineSeparator()).length;
            //System.out.println(lineAmount);
            //System.out.println(new Dimension(displayMessage.getSize().width, (int)(displayMessage.getFont().getSize()*lineAmount*1.3f)));
            displayMessage.setPreferredSize(new Dimension(displayMessage.getSize().width, (int)(displayMessage.getFont().getSize()*lineAmount*1.3f)));
            displayMessage.setSize(new Dimension(displayMessage.getSize().width, (int)(displayMessage.getFont().getSize()*lineAmount*1.3f)));
        }
    }
}
