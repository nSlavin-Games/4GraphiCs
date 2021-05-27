package com.fourgraphics.commands;

/**
 * Evento per l'invio del comando
 */
public interface ICommandEvent {
    void commandSent(String command) throws Exception;
}
