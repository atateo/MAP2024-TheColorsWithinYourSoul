package it.uniba.map.giocotestuale.entities;

import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class CommandClass {
    private String commandName;
    private Command commandType;
    private List<String> commandAliases;

    public CommandClass(final String commandName, final Command commandType, final List<String> commandAliases) {
        this.commandName = commandName;
        this.commandType = commandType;
        this.commandAliases = commandAliases;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public Command getCommandType() {
        return this.commandType;
    }

    public List<String> getCommandAliases() {
        return this.commandAliases;
    }
}
