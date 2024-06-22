package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.entities.CommandClass;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColorsWithinYourSoulGame extends GameEngine {

    public ColorsWithinYourSoulGame() {
        super();
    }

    @Override
    public void welcomePlayer() {
        //Scrivere qui il codice che gestirà l'intro di gioco
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
        commands.add(new CommandClass("Usa", Command.USA, List.of("u", "use", "utilizza", "poggia", "appoggia", "poni")));
        commands.add(new CommandClass("Colora", Command.COLORA, List.of("pittura", "paint", "tinteggia")));

        return commands;
    }

    @Override
    public void defineGameInteractions() {
        //Scrivere qui il codice che definirà effettivamente il flow del gioco, nello specifico le interazioni
    }

    @Override
    public void update() {
        //Scrivere qui il codice che scorrerà le varie interactions per eseguire quelle da effettuare
    }

    @Override
    public boolean checkIfGameIsOver() {
        //Scrivere qui il codice che verificherà se il gioco è terminato

        return false;
    }

    @Override
    public void goodbyePlayer() {
        //Scrivere qui il codice che gestirà la fine del gioco
    }
}
