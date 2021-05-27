package com.fourgraphics.commands;

import com.fourgraphics.CheatConsole;
import com.fourgraphics.Input;
import com.fourgraphics.SceneManager;

/**
 * Classe astratta per la creazione di un provider dei comandi
 */
public abstract class Command {
    /**
     * Riferimento alla CheatsConsole
     */
    private CheatConsole console;

    /**
     * Metodo per impostare la CheatsConsole, generalmente non va usato.
     *
     * @param console Riferimento alla CheatsConsole
     */
    public void registerConsole(CheatConsole console) {
        this.console = console;
    }

    /**
     * Metodo che registra e abilita i comandi contenuti in un Command Provider.
     * All'interno deve contenere il seguente blocco di codice per funzionare correttamente:
     * try {
     * getConsole().commandReceivedEvent((commandSource, fullCommand) -> {
     * commandSource = super;
     * if(fullCommand.equals("test")) System.out.println("test :D");
     * });
     * } catch (Exception e) {
     * DebugConsole.Error(e.toString());
     * }
     * <p>
     * Quello che fa è registrare la classe all'evento e tramite un sistema di switch/ifelse osserva l'input e ne determina un output per la console.
     * Questo sistema verrà rifatto per la prossima major version di CheatsConsole per essere più efficente, intelligente e ordinato visto che la versione attuale è un test.
     *
     * @return Riferimento al CommandProvider. [per utility futura, TBD]
     */
    public abstract Command registerCommands();

    public CheatConsole getConsole() {
        return console;
    }

    /**
     * In questo metodo vengono inseriti tutti gli eventi che devono essere controllati ogni frame.
     * Per esempio, se voglio controllare l'input dell'utente in seguito a un comando, devo inserire il blocco di  codice relativo al controllo dell'utente qui.
     * Questo metodo viene eseguito ogni frame all'interno di {@link SceneManager#executeScene()} e viene eseguito alla fine del metodo, prima di {@link Input#updateKeyStatus()} per garantire il raccoglimento di tutti i dati e la sicurezza nella ricezione degli input.
     */
    public void updateCycle() {
    }
}
