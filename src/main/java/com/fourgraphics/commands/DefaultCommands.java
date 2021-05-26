package com.fourgraphics.commands;

import com.fourgraphics.DebugConsole;
import com.fourgraphics.Input;
import com.fourgraphics.SceneManager;

public class DefaultCommands extends Command {
    String currentlyBinded = "";

    public DefaultCommands() {
    }

    @Override
    public Command registerCommands() {
        try{
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
        if (command.equals("log")) {
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
        }
        if (command.equals("loadScene")) {
            SceneManager.loadScene(Integer.parseInt(arg1));
        }
        if (command.equals("get")) {
            if (arg1.equals("numberOfScenes")) {
                System.out.println("There are " + (SceneManager.getNumberOfScenes() - 1) + " scenes.");
            }
        }
        if (command.equals("restartScene")) {
            SceneManager.loadScene(SceneManager.getActiveSceneIndex());
        }

        if (command.equals("bind")) {
            if (arg1.contains("\"")) {
                currentlyBinded = arg1 + text;
                currentlyBinded = currentlyBinded.substring(currentlyBinded.indexOf("\"") + 1, currentlyBinded.lastIndexOf("\""));
                String[] args = fullCommand.replace("bind \"" + currentlyBinded + "\" ", "").replace("\n", "").split(" ");
                Input.createButton("commandBind", args[0], args[1]);
                System.out.println("Successfully bound key " + args[0] + " (and alt key " + args[1] + ") to command " + currentlyBinded + ".");
            } else {
                System.out.println("ERROR: the bind command requires the command to be in commas (\"command\").");
            }
        }

        if (command.equals("reloadConsole"))
            getConsole().loadCommands();

        if (command.equals("help")) {
            System.out.println("Available Commands for DefaultCommands:\n" +
                    "bind \"[command]\" [button] [altButton]\n\t- Binds a key to a command, only works for one command at a time for now and does not support extra commands :(\n" +
                    "exit \n\t- Closes the program.\n" +
                    "get [attribute]\n\t- Get an attribute from the engine.\n\t Available attributes:\n\t\t- numberOfScenes: returns the number of available scenes.\n" +
                    "loadScene [sceneIndex]\n\t- Load a scene with the specified index.\n" +
                    "log [info|warn|error] \"[message]\"\n\t- Sends a message to the Debug Console.\n" +
                    "restartScene\n\t- Restarts the current scene.\n");
        }
        if (command.equals("exit")){
            SceneManager.getApp().exit();
        }
    }

    @Override
    public void updateCycle() {
        if (!currentlyBinded.equals("")) {
            if (Input.getButtonDown("commandBind")) {
                commandInterpreter(currentlyBinded);
            }
        }
    }
}
