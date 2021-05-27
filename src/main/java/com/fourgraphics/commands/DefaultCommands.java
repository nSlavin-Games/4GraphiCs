package com.fourgraphics.commands;

import com.fourgraphics.DebugConsole;
import com.fourgraphics.Input;
import com.fourgraphics.SceneManager;

public class DefaultCommands extends Command {
    String currentlyBound = "";

    public DefaultCommands() {
    }

    @Override
    public Command registerCommands() {
        try {
            getConsole().commandReceivedEvent(this, this::commandInterpreter);
        } catch (Exception e) {
            DebugConsole.Error(e.toString());
        }
        return this;
    }

    private void commandInterpreter(String fullCommand) {
        String command = fullCommand.split(" ")[0];
        String arg1 = "";
        try {
            arg1 = fullCommand.split(" ")[1];
        } catch (Exception ignored) {
        }
        String text = fullCommand.replace(command + " " + arg1, "");
        switch (command) {
            case "log":
                if (text.contains("\"")) {
                    text = text.replace("\"", "");

                    switch (arg1) {
                        case "warn":
                            DebugConsole.Warn(text);
                            break;
                        case "error":
                            DebugConsole.Error(text);
                            break;
                        default:
                            DebugConsole.Info(text);
                    }
                } else {
                    System.out.println("ERROR: the log command requires the message to be in commas (\"message\").");
                }
                break;
            case "loadScene":
                SceneManager.loadScene(Integer.parseInt(arg1));
                break;
            case "get":
                switch (arg1) {
                    case "numberOfScenes":
                        System.out.println("There are " + (SceneManager.getNumberOfScenes() - 1) + " scenes.");
                        break;
                    case "currentSceneIndex":
                        System.out.println("Current scene index: " + (SceneManager.getActiveSceneIndex()));
                }
                break;
            case "restartScene":
                SceneManager.loadScene(SceneManager.getActiveSceneIndex());
                break;
            case "bind":
                if (arg1.contains("\"")) {
                    currentlyBound = arg1 + text;
                    currentlyBound = currentlyBound.substring(currentlyBound.indexOf("\"") + 1, currentlyBound.lastIndexOf("\""));
                    String[] args = fullCommand.replace("bind \"" + currentlyBound + "\" ", "").replace("\n", "").split(" ");
                    Input.createButton("commandBind", args[0], args[1]);
                    System.out.println("Successfully bound key " + args[0] + " (and alt key " + args[1] + ") to command " + currentlyBound + ".");
                } else {
                    System.out.println("ERROR: the bind command requires the command to be in commas (\"command\").");
                }
                break;
            case "reloadConsole":
                getConsole().loadCommands();
                break;
            case "clear":
                for (int i = 0; i < 50; i++) {
                    System.out.println("\n");
                }
                System.out.flush();
                break;
            case "help":
                System.out.println("Available Commands for DefaultCommands:\n" +
                        "\tbind \"[command]\" [button] [altButton]\n\t\t- Binds a key to a command, only works for one command at a time for now and does not support extra commands :(\n" +
                        "\tclear \n\t\t- Clears the console.\n" +
                        "\texit \n\t\t- Terminates the program.\n" +
                        "\tget [attribute]\n\t\t- Get an attribute from the engine.\n\t\t  Available attributes:\n\t\t\t- numberOfScenes: returns the number of available scenes.\n\t\t\t- currentSceneIndex: returns the ID of the current scene.\n" +
                        "\tloadScene [sceneIndex]\n\t\t- Load a scene with the specified index.\n" +
                        "\tlog [info|warn|error] \"[message]\"\n\t\t- Sends a message to the Debug Console.\n" +
                        "\trestartScene\n\t\t- Restarts the current scene.\n");
                break;
            case "quit":
            case "exit":
                SceneManager.getApp().exit();
                break;
        }
    }

    @Override
    public void updateCycle() {
        if (!currentlyBound.equals("")) {
            if (Input.getButtonDown("commandBind")) {
                commandInterpreter(currentlyBound);
            }
        }
    }
}
