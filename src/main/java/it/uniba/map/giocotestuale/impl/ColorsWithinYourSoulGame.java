package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.entities.CommandClass;
import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.type.ParserOutput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColorsWithinYourSoulGame extends GameEngine {

    public ColorsWithinYourSoulGame() {
        super();
        defineGameInteractions();
    }

    @Override
    public void welcomePlayer() {
        //Scrivere qui il codice che gestirà l'intro di gioco
    }

    @Override
    public void help() {
        //Scrivere qui l'implementazione del comando AIUTO
    }

    @Override
    public Set<CommandClass> getAllCommands() {
        Set<CommandClass> commands = new HashSet<>();

        commands.add(new CommandClass("Aiuto", Command.AIUTO, List.of("h", "help", "comandi", "comando", "guida")));
        commands.add(new CommandClass("Nord", Command.NORD, List.of("n", "north", "avanti", "vaiAvanti")));
        commands.add(new CommandClass("Sud", Command.SUD, List.of("s", "south", "indietro", "vaiIndietro")));
        commands.add(new CommandClass("Ovest", Command.OVEST, List.of("o", "west", "sinistra", "vaiSinistra", "vaiASinistra")));
        commands.add(new CommandClass("Est", Command.EST, List.of("e", "east", "destra", "vaiDestra", "vaiADestra")));
        commands.add(new CommandClass("Osserva", Command.OSSERVA, List.of("g", "l", "look", "vedi", "esamina", "osserva", "ammira", "ispeziona")));
        commands.add(new CommandClass("Inventario", Command.INVENTARIO, List.of("i", "inventory", "borsa", "zaino", "inv")));
        commands.add(new CommandClass("Prendi", Command.PRENDI, List.of("p", "t", "take", "raccogli", "recupera", "intasca")));
        commands.add(new CommandClass("Lascia", Command.LASCIA, List.of("drop", "abbandona", "lancia", "butta", "scarta", "rimuovi")));
        commands.add(new CommandClass("Usa", Command.USA, List.of("u", "use", "utilizza")));
        commands.add(new CommandClass("Colora", Command.COLORA, List.of("pittura", "paint", "tinteggia")));

        return commands;
    }

    @Override
    public void defineGameInteractions() {
        //Scrivere qui il codice che definirà effettivamente il flow del gioco, nello specifico le interazioni
    }

    @Override
    public void update(ParserOutput output) {
        //Scrivere qui il codice che scorrerà le varie interactions per eseguire quelle da effettuare
        //ed eventualmente gestirà i comandi a 0 parametri

        //Si è preferito implementare qui i comandi Aiuto e Inventario, in quanto sono abbastanza generici,
        //ovvero fanno sempre la stampa dei comandi disponibili nel caso di aiuto e la stampa dell'inventario
        //nel caso di inventario. I comandi di movimento, invece, vengono trattati come interaction.
        //Così, è possibile definire azioni particolari come ChainInteractions che vengono scatenate
        //quando un player si muove in una determinata stanza.

        List<Command> movementCommands = List.of(Command.NORD, Command.SUD, Command.OVEST, Command.EST);
        boolean didSomething = false;

        if (output == null) {
            return;
        }

        //Comando aiuto
        if (output.getCommandType() == Command.AIUTO) {
            if (output.getFirstObject() != null || output.getSecondObject() != null) {
                invalidCommandOutput();
            } else {
                help();
            }
            return;
        }

        //Comando inventario
        if (output.getCommandType() == Command.INVENTARIO) {
            //Scrivere qui la stampa dell'inventario
            return;
        }

        //Imposta il comando di movimento come interaction
        if (movementCommands.contains(output.getCommandType())) {
            if (output.getFirstObject() != null || output.getSecondObject() != null) {
                invalidCommandOutput();
                return;
            } else {
                output.setFirstObject(this.getCurrentRoom());
            }
        }

        //Imposta la lista degli oggetti interessati e scorre le interaction del gioco per poi eseguirle
        //nel caso corrispondano con l'output del Parser
        List<GameObject> gameObjects;

        if (output.getSecondObject() == null) {
            gameObjects = List.of(output.getFirstObject());
        } else {
            gameObjects = List.of(output.getFirstObject(), output.getSecondObject());
        }

        for (Interaction interaction : super.getGameInteractions()) {
            if (interaction.isCorrectInteraction(gameObjects, output.getCommandType())){
                interaction.executeInteraction(this);
                didSomething = true;
            }
        }

        if (!didSomething) {
            System.out.println("Non è successo niente.");
        }
    }

    @Override
    public void invalidCommandOutput() {
        System.out.println("Non ho capito cosa mi vuoi dire...");
        System.out.println("Il comando potrebbe essere sbagliato o non ho trovato l'oggetto che hai chiesto.");
    }

    @Override
    public boolean checkIfGameIsOver() {
        return this.getCurrentRoom() == null;
    }

    @Override
    public void goodbyePlayer() {
        //Scrivere qui il codice che gestirà la fine del gioco
    }
}