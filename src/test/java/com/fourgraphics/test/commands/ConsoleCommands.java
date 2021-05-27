package com.fourgraphics.test.commands;

import com.fourgraphics.DebugConsole;
import com.fourgraphics.SceneManager;
import com.fourgraphics.commands.Command;
import com.fourgraphics.test.scripts.player.PlayerCombat;
import com.fourgraphics.test.scripts.player.PlayerMovement;

public class ConsoleCommands extends Command {
    @Override
    public Command registerCommands() {
        try {
            getConsole().commandReceivedEvent(this, this::commandInterpreter);
        } catch (Exception e) {
            DebugConsole.Error(e.toString());
        }
        return null;
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
            case "moonJump":
                SceneManager.findObject("player").getComponent(PlayerMovement.class).setMoonJump(arg1.equals("true"));
                break;
            case "god":
                SceneManager.findObject("player").getComponent(PlayerCombat.class).setGod(arg1.equals("true"));
                break;
            case "get":
                switch (arg1) {
                    case "isGod":
                        System.out.println("God status: " + SceneManager.findObject("player").getComponent(PlayerCombat.class).god);
                        break;
                    case "moonJumping":
                        System.out.println("MoonJumping status: " + SceneManager.findObject("player").getComponent(PlayerMovement.class).moonJump);
                        break;
                }
                break;
            case "help":
                System.out.println("Available Commands for ConsoleCommands:\n" +
                        "\tget [attribute]\n\t\t- Get an attribute from the engine.\n\t\t  Available attributes:\n\t\t\t- isGod: returns the godmode status.\n\t\t\t- moonJumping: returns the moonJump status.\n" +
                        "\tgod [true|false]\n\t\t- Set player godmode status.\n" +
                        "\tmoonJump [true|false] \"[message]\"\n\t\t- Set player moonjump status.\n");
                break;
        }
    }
}
