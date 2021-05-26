package com.fourgraphics;

import com.fourgraphics.commands.Command;
import com.fourgraphics.commands.DefaultCommands;
import com.fourgraphics.commands.ICommandEvent;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author William Matrix Peckham
 */
public class CheatConsole {

    static DocOutputStream out;
    static PrintStream pout;
    static DocInputStream in;
    private static HashMap<Command, ICommandEvent> commands = new HashMap<>();
    JFrame frame;
    StyledDocument doc;
    private JTextField consoleInput;
    private JTextPane consoleOutput;
    private JPanel cheatsConsolePanel;
    private JLabel cheatsConsoleForProjectLabel;
    private JScrollPane outputScrollPane;
    //    JTextField inputBox;

    public CheatConsole() {
        super();
        cheatsConsoleForProjectLabel.setText(cheatsConsoleForProjectLabel.getText() + SceneManager.getProjectTitle());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }

        consoleOutput.setPreferredSize(new Dimension(500, 500));
        doc = consoleOutput.getStyledDocument();
        doc.getLogicalStyle(0).addAttribute("overflow-wrap", "anywhere");
        outputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        out = new DocOutputStream(doc, consoleOutput);
        pout = new PrintStream(out);
        in = new DocInputStream();
        consoleOutput.setEditable(false);
        System.setIn(in);
        System.setOut(pout);
        System.setErr(pout);
        setFGColor(Color.white);
        consoleOutput.setBackground(Color.blue);
        frame = new JFrame("Console");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cheatsConsolePanel);
        frame.pack();
        frame.setVisible(true);

        consoleInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == "\n".toCharArray()[0]) {
                    try {
                        out.write(("> " + consoleInput.getText() + "\n").getBytes(StandardCharsets.UTF_8));
//                        String command = consoleInput.getText().split(" ")[0];
//                        ArrayList<String> listArgs = new ArrayList<>(Arrays.asList(consoleInput.getText().split(" ")));
//                        listArgs.remove(0);
//                        String[] args = listArgs.toArray(new String[0]);
                        commands.forEach((commandProvider, commandEvent) -> {
                            commandEvent.commandSent(consoleInput.getText());
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        consoleInput.setText("");
                    }
                    consoleInput.setText("");
                }
            }
        });

        registerCommand(new DefaultCommands());

        commands.forEach((commandProvider, commandEvent) -> {
            commandProvider.registerConsole(this);
            commandProvider.registerCommands();
        });
    }

    public static void updateCycle() {
        commands.forEach((command, event) -> command.updateCycle());
    }

    public static InputStream getIn() {
        return in;
    }

    public static PrintStream getOut() {
        return pout;
    }

    public void updateConsoleName(String name) {
        cheatsConsoleForProjectLabel.setText("Cheats Console for project " + SceneManager.getProjectTitle());
        new JTextArea().setWrapStyleWord(true);
    }

    public void registerCommand(Command command) {
        command.registerConsole(this);
        command.registerCommands();
    }

    public void loadCommands() {
        commands.forEach((command, event) -> {
            command.registerCommands();
            command.registerConsole(this);
        });
    }

    public void commandReceivedEvent(Command provider, ICommandEvent event) {
        if (commands.containsKey(provider)) commands.replace(provider, event);
        else commands.put(provider, event);
    }

    public void setFGColor(Color c) {
        StyleConstants.setForeground(out.cur, c);
    }

    public void setBGColor(Color c) {
        StyleConstants.setBackground(out.cur, c);
    }

    public void setData(CustomTextPane data) {
    }

    public void getData(CustomTextPane data) {
    }

    public boolean isModified(CustomTextPane data) {
        return false;
    }

    private static class DocOutputStream extends OutputStream {

        StyledDocument doc;
        MutableAttributeSet cur;
        JTextPane pane;

        public DocOutputStream(StyledDocument doc, JTextPane pane) {
            this.doc = doc;
            this.pane = pane;
            cur = new SimpleAttributeSet();
        }

        @Override
        public void write(int b) throws IOException {
            try {
                doc.insertString(doc.getLength(), (char) b + "", cur);
                pane.setCaretPosition(doc.getLength());
            } catch (BadLocationException ex) {
                Logger.getLogger(CheatConsole.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

        }
    }

    private static class DocInputStream extends InputStream implements KeyListener {

        ArrayBlockingQueue<Integer> queue;

        public DocInputStream() {
            queue = new ArrayBlockingQueue<Integer>(1024);
        }

        @Override
        public int read() throws IOException {
            Integer i = null;
            try {
                i = queue.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(CheatConsole.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            if (i != null)
                return i;
            return -1;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (b == null) {
                throw new NullPointerException();
            } else if (off < 0 || len < 0 || len > b.length - off) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }
            int c = read();
            if (c == -1) {
                return -1;
            }
            b[off] = (byte) c;

            int i = 1;
            try {
                for (; i < len && available() > 0; i++) {
                    c = read();
                    if (c == -1) {
                        break;
                    }
                    b[off + i] = (byte) c;
                }
            } catch (IOException ee) {
            }
            return i;

        }


        @Override
        public int available() {
            return queue.size();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            int c = e.getKeyChar();
            try {
                queue.put(c);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheatConsole.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}