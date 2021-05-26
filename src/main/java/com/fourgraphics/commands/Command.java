package com.fourgraphics.commands;

import com.fourgraphics.CheatConsole;

public abstract class Command {
    private CheatConsole console;

    public void registerConsole(CheatConsole console) {
        this.console = console;
    }

    public abstract Command registerCommands();

    public CheatConsole getConsole() {
        return console;
    }

    public void setConsole(CheatConsole console) {
        this.console = console;
    }

    public void updateCycle(){}
}
