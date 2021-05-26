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
        if (command.equals("moonJump")) {
            SceneManager.findObject("player").getComponent(PlayerMovement.class).setMoonJump(arg1.equals("true"));
        }
        if (command.equals("god")) {
            SceneManager.findObject("player").getComponent(PlayerCombat.class).setGod(arg1.equals("true"));
        }
        if (command.equals("get")) {
            if (arg1.equals("isGod")) {
                System.out.println("God status: " + SceneManager.findObject("player").getComponent(PlayerCombat.class).god);
            }
            if (arg1.equals("moonJumping")) {
                System.out.println("MoonJumping status: " + SceneManager.findObject("player").getComponent(PlayerMovement.class).moonJump);
            }
        }
        if (command.equals("help")) {
            System.out.println("Available Commands for ConsoleCommands:\n" +
                    "get [attribute]\n\t- Get an attribute from the engine.\n\t Available attributes:\n\t\t- isGod: returns the godmode status.\n\t\t- moonJumping: returns the moonJump status.\n" +
                    "god [true|false]\n\t- Set player godmode status.\n" +
                    "moonJump [true|false] \"[message]\"\n\t- Set player moonjump status.\n");
        }
    }
}
