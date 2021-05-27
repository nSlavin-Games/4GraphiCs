package com.fourgraphics.commands;

public interface ICommandEvent {
    void commandSent(String command) throws Exception;
}
