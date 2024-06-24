package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.entities.CommandClass;
import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.entities.Item;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.type.ParserOutput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe contenente l'implementazione del gioco The Colors Within Your Soul.
 * @author Yuri Tateo
 * @author Antimo Tateo
 * @author Angelo Vincenti
 */
public class ColorsWithinYourSoulGame extends GameEngine {

    /**
     * Costruttore di classe. Non ha parametri. Richiama il costruttore della superclasse per
     * l'inizializzazione e successivamente crea la logica di gioco con defineGameInteraction.
     */
    public ColorsWithinYourSoulGame() {
        super();
        defineGameInteractions();
    }

    /**
     * Metodo che si occuperà di gestire l'intro del gioco. Definizione del metodo di GameEngine.
     */
    @Override
    public void welcomePlayer() {
        //Scrivere qui il codice che gestirà l'intro di gioco
    }

    /**
     * Metodo che si occuperà di gestire il comando AIUTO. Definizione del metodo di GameEngine.
     */
    @Override
    public void help() {
        //Scrivere qui l'implementazione del comando AIUTO
    }

    /**
     * Metodo che restituirà tutti i comandi di gioco. Definizione del metodo di GameEngine.
     */
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
        commands.add(new CommandClass("Spingi", Command.SPINGI, List.of("sposta", "muovi", "push")));
        commands.add(new CommandClass("Colora", Command.COLORA, List.of("pittura", "paint", "tinteggia")));

        return commands;
    }

    /**
     * Metodo che si occuperà di definire la logica del gioco, nello specifico le interazioni tra gli oggetti.
     * Questo viene fatto istanziando oggetti di tipo Interaction e inserendoli nell'ArrayList
     * gameInteractions della superclasse. Definizione del metodo di GameEngine.
     */
    @Override
    public void defineGameInteractions() {
        //Scrivere qui il codice che definirà effettivamente il flow del gioco, nello specifico le interazioni

        //NOTA BENE: Le interazioni dirette devono essere inserite nella lista PRIMA delle interazioni a catena.
        //Altrimenti se un'interazione diretta ne scatena una a catena, quella a catena non verrà eseguita subito.
    }

    /**
     * Metodo che si occuperà di aggiornare il gioco quando l'utente inserisce un comando.
     * A tutti gli effetti è il metodo che esegue i comandi del gioco. Definizione del metodo in GameEngine.
     * @param output Oggetto di tipo ParserOutput costruito sull'input dell'utente dalla classe Parser.
     */
    @Override
    public void update(ParserOutput output) {
        //Scrivere qui il codice che scorrerà le varie interactions per eseguire quelle da effettuare
        //ed eventualmente gestirà i comandi a 0 parametri

        //Si è preferito implementare direttamente i casi più generici che non dipendono da interazioni,
        //come il comando AIUTO, INVENTARIO
        //I comandi di movimento, invece, vengono trattati come interactions.
        //Così, è possibile definire azioni particolari come ChainInteractions che vengono scatenate
        //quando un player si muove in una determinata stanza.

        List<Command> movementCommands = List.of(Command.NORD, Command.SUD, Command.OVEST, Command.EST);
        boolean didSomething = false;

        if (output == null) {
            invalidCommandOutput();
            return;
        }

        Command command = output.getCommandType();

        //Comando aiuto
        if (command == Command.AIUTO) {
            if (output.getFirstObject() != null || output.getSecondObject() != null) {
                invalidCommandOutput();
            } else {
                help();
            }
            return;
        }

        //Comando inventario
        if (command == Command.INVENTARIO) {
            //Scrivere qui la stampa dell'inventario
            return;
        }

        //Verifica che per il comando usa l'oggetto sia nell'inventario
        if (command == Command.USA) {
            if (!getInventory().contains( (Item) output.getFirstObject())) {
                invalidCommandOutput();
                return;
            }
        }

        //Verifica che per il comando spingi, prendi e colora l'oggetto sia nella stanza
        //e che l'oggetto ammetta quell'azione su di esso
        if (command == Command.SPINGI || command == Command.PRENDI || command == Command.COLORA) {
            if (!getCurrentRoom().getItemsInRoom().contains( (Item) output.getFirstObject())){
                invalidCommandOutput();
                return;
            }

            if (output.getCommandType() == Command.SPINGI && !((Item) output.getFirstObject()).getMovable()) {
                System.out.println("Provi a spingerlo, ma l'oggetto non si muove.");
                return;
            } else if (output.getCommandType() == Command.PRENDI && !((Item) output.getFirstObject()).getPickable()) {
                System.out.println("Non puoi mettere una cosa del genere nell'inventario!!");
                return;
            } else if (output.getCommandType() == Command.COLORA && !((Item) output.getFirstObject()).getPaintable()) {
                System.out.println("Sembra che la pittura non abbia effetto su questo oggetto.");
                return;
            }
        }

        //Se è un comando di movimento, imposta la stanza corrente come primo oggetto dell'output,
        //così da poter eseguire il movimento come un'interazione sulla stanza
        if (movementCommands.contains(command)) {
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

        //Se nessuna interaction è stata effettuata, notifica l'utente
        if (!didSomething) {
            System.out.println("Non è successo niente.");
        }
    }

    /**
     * Metodo che notifica l'utente che il comando è invalido. Definizione del metodo di GameEngine.
     */
    @Override
    public void invalidCommandOutput() {
        System.out.println("Non ho capito cosa mi vuoi dire...");
        System.out.println("Il comando potrebbe essere sbagliato o non ho trovato l'oggetto che hai chiesto.");
    }

    /**
     * Metodo che verifica se il gioco è terminato o meno. Definizione del metodo di GameEngine.
     * @return Booleano che indica se il gioco è terminato o meno.
     */
    @Override
    public boolean checkIfGameIsOver() {
        return this.getCurrentRoom() == null;
    }

    /**
     * Metodo che gestisce la sequenza finale del gioco. Definizione del metodo di GameEngine.
     */
    @Override
    public void goodbyePlayer() {
        //Scrivere qui il codice che gestirà la fine del gioco
    }
}