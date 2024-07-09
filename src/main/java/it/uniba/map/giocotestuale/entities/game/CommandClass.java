package it.uniba.map.giocotestuale.entities.game;

import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

/**
 * Classe per la gestione dei comandi con nome, alias del comando ed enum del tipo del comando.
 */
public class CommandClass {
    /**
     * Nome del comando.
     */
    private final String commandName;

    /**
     * Tipo del comando relativamente all'enum Command.
     */
    private final Command commandType;

    /**
     * Lista contenente gli alias del comando.
     */
    private final List<String> commandAliases;

    /**
     * Costruttore con parametri della classe CommandClass. Inizializza tutti gli attributi.
     *
     * @param commandName    Nome dell'istanza di questo comando.
     * @param commandType    Tipo dell'istanza di questo comando.
     * @param commandAliases Aliases dell'istanza di questo comando.
     */
    public CommandClass(final String commandName, final Command commandType, final List<String> commandAliases) {
        this.commandName = commandName;
        this.commandType = commandType;
        this.commandAliases = commandAliases;
    }

    /**
     * Metodo getter per il nome del comando.
     *
     * @return Nome dell'istanza di questo comando.
     */
    public String getCommandName() {
        return this.commandName;
    }

    /**
     * Metodo getter per il tipo del comando.
     *
     * @return Tipo dell'istanza di questo comando.
     */
    public Command getCommandType() {
        return this.commandType;
    }

    /**
     * Metodo getter per la lista di alias del comando.
     *
     * @return Aliases dell'istanza di questo comando.
     */
    public List<String> getCommandAliases() {
        return this.commandAliases;
    }
}
