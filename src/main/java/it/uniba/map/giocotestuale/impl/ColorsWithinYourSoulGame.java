package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.entities.game.*;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.logic.interaction.InteractionFactory;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.type.ParserOutput;
import it.uniba.map.giocotestuale.utility.Mixer;
import org.glassfish.grizzly.compression.lzma.impl.Base;

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
    }

    /**
     * Metodo che si occuperà di gestire l'intro del gioco. Definizione del metodo di GameEngine.
     */
    @Override
    public void welcomePlayer() {
        GameToGUICommunication.getInstance().toGUI("Sono passati ormai diversi anni da quando ti sei trasferito a " +
                "New York. Ormai hai fatto carriera, eppure senti di aver perso qualcosa per strada, forse proprio te stesso. " +
                "Un giorno, però, ricevi una lettera da Vieste. Il tuo caro nonno, con il quale sei cresciuto, è passato a" +
                "miglior vita e ti ha lasciato in eredità la sua amata villa di famiglia. La notizia ti ha lasciato scosso, " +
                "ma allo stesso tempo forse questa è l'opportunità di cui avevi bisogno per ritrovare i colori nella tua anima...");

        GameToGUICommunication.getInstance().toGUI("Decidi quindi di partire per tornare nella villa di tuo nonno. Al tuo " +
                "arrivo, decidi di fare un giro per la villa per rivivere qualche ricordo d'infanzia. Ti ricordi, però, che tuo " +
                "nonno ti aveva sempre impedito di salire all'ultimo piano della villa. La curiosità prende il meglio di te e " +
                "decidi di andare a controllarlo. Forse tuo nonno ti ha lasciato la villa perché ti ritiene pronto per essa?");

        GameToGUICommunication.getInstance().toGUI("Salite le scale, ti ritrovi davanti al portone d'ingresso della stanza " +
                "principale del piano. Lo apri e ti ritrovi in un'enorme stanza che ti pare un misto tra un attico e una serra. La " +
                "porta si chiude alle tue spalle e su di essa vedi scritto \"Recupera i 6 colori che ormai la tua anima ha perduto. " +
                "Dovrai poi abbattere l'albero maestro. Solo allora potrai uscire\". La tua avventura comincia qui.");

        super.getGameTimer().start();
    }

    /**
     * Metodo che si occuperà di gestire il comando AIUTO. Definizione del metodo di GameEngine.
     */
    @Override
    public void help() {
        GameToGUICommunication.getInstance().toGUI("Premi il tasto ? in alto per avere un chiarimento sui " +
                "comandi di gioco.");
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
        commands.add(new CommandClass("Colora", Command.COLORA, List.of("co", "pittura", "paint", "tinteggia")));

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

        //Per comodità, si è deciso di scrivere la logica di gioco nell'apposita classe BaseGameLogic.
        BaseGameLogic logic = new BaseGameLogic();

        super.getGameInteractions().addAll(logic.getGameLogic());
    }

    /**
     * Metodo che si occuperà di aggiornare il gioco quando l'utente inserisce un comando.
     * A tutti gli effetti è il metodo che esegue i comandi del gioco. Definizione del metodo in GameEngine.
     * @param output Oggetto di tipo ParserOutput costruito sull'input dell'utente dalla classe Parser.
     */
    @Override
    public void update(ParserOutput output) {
        //Si è preferito implementare direttamente i casi più generici che non dipendono da interazioni. Ovvero:
        // 1. AIUTO
        // 2. INVENTARIO
        // 3. Casi di errore di USA, SPINGI, PRENDI, COLORA e LASCIA
        // 4. Parte di LASCIA (il drop di un item potrebbe essere legato a un'interazione a catena)
        // 5. Parte di PRENDI (prendere un item può essere legato a un'interazione a catena)

        //Tutti gli altri comando saranno trattati come interactions. Alla fine del metodo ci sarà un for
        //che itererà tutti gli oggetti di tipo Interaction per eseguire quelle interessate dall'output.
        //Le Interaction vengono istanziate all'avvio del gioco richiamando il metodo defineGameInteractions.

        //I comandi di movimento vengono trattati come interactions sulla stanza corrente.
        //Così, è possibile definire azioni particolari come ChainInteractions che vengono scatenate
        //quando un player si muove in una determinata stanza.

        //OSSERVA è trattato come un'interaction sulla stanza corrente, così da poter definire
        //comportamenti diversi del comando sulla base dello stato della stanza.

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

        //Comando osserva
        if (command == Command.OSSERVA) {
            //Se il secondo oggetto non è nullo, il comando è invalido
            if (output.getSecondObject() != null) {
                invalidCommandOutput();
                return;
            }

            //Se il primo oggetto è nullo, osserva la stanza
            if (output.getFirstObject() == null) {
                GameToGUICommunication.getInstance().toGUI(getCurrentRoom().getDescriptionFromDB());
            } else {
                //Se l'oggetto è un item nella stanza, stampane la descrizione a video
                if (getCurrentRoom().getItemsInRoom().contains(output.getFirstObject())) {
                    GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getDescriptionFromDB());
                    return;
                }

                //Se l'oggetto è un item nell'inventario, stampane la descrizione a video
                if (getInventory().contains(output.getFirstObject())) {
                    GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getDescriptionFromDB());
                    return;
                }

                //Se l'oggetto è un colore sbloccato, stampane la descrizione a video
                if(output.getFirstObject() instanceof ColorClass && ((ColorClass) output.getFirstObject()).isUnlocked()){
                    GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getDescriptionFromDB());
                    return;
                }

                invalidCommandOutput();
                return;
            }
        }

        //Verifica che per il comando spingi e prendi l'oggetto sia nella stanza e che
        //l'oggetto ammetta quell'azione su di esso, inoltre, implementa il comando PRENDI
        if (command == Command.SPINGI || command == Command.PRENDI) {
            if (!getCurrentRoom().getItemsInRoom().contains((Item) output.getFirstObject())){
                invalidCommandOutput();
                return;
            }

            if (output.getCommandType() == Command.SPINGI && !((Item) output.getFirstObject()).getMovable()) {
                GameToGUICommunication.getInstance().toGUI("Non vale la pena spostare questo oggetto.");
                return;
            } else if (output.getCommandType() == Command.PRENDI) {
                if ( !((Item) output.getFirstObject()).getPickable()) {
                    GameToGUICommunication.getInstance().toGUI("Non puoi mettere una cosa del genere nell'inventario!!");
                    return;
                } else if (!output.getFirstObject().getName().contains("Pennello")) {
                    //Aggiunge l'oggetto nell'inventario e lo rimuove dalla stanza
                    addItemToInventory((Item) output.getFirstObject());
                    getCurrentRoom().removeItem((Item) output.getFirstObject());
                    GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getName() + ": aggiunto nell'inventario.");

                    //Non fa un return perché potrebbe scatenarsi una reazione a catena quando il player prende un item
                    //Inoltre non mette i pennelli nell'inventario perché sbloccano direttamente i colori
                } else {
                    //Rimuove il pennello dalla stanza
                    getCurrentRoom().removeItem((Item) output.getFirstObject());
                }
            }
        }

        //Implementa il comando LASCIA
        if (command == Command.LASCIA) {
            if (output.getFirstObject() == null) {
                GameToGUICommunication.getInstance().toGUI("Non stai lasciando nulla!!");
            } else {
                //Rimuove l'oggetto nell'inventario e lo aggiunge dalla stanza
                removeItem((Item) output.getFirstObject());
                getCurrentRoom().addItem((Item) output.getFirstObject());
                GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getName() + ": rimosso dall'inventario.");

                //Non fa un return perché potrebbe scatenarsi una reazione a catena quando il player lascia un item
            }
        }

        //Verifica che per il comando usa l'oggetto sia nell'inventario
        if (command == Command.USA) {
            if (!getInventory().contains((Item) output.getFirstObject())) {
                invalidCommandOutput();
                return;
            }
        }

        //Verifica che per il comando colora l'oggetto sia o nella stanza o nell'inventario
        //Verifica che l'oggetto colorabile sia nell'inventario
        if (command == Command.COLORA) {
            if (output.getFirstObject() == null || output.getSecondObject() == null) {
                invalidCommandOutput();
                return;
            }

            if (!getCurrentRoom().getItemsInRoom().contains((Item) output.getFirstObject()) &&
            !super.getInventory().contains((Item) output.getFirstObject())) {
                invalidCommandOutput();
                return;
            }

            if (!(output.getSecondObject() instanceof ColorClass)) {
                invalidCommandOutput();
                return;
            }

            if (!((ColorClass) output.getSecondObject()).isUnlocked()) {
                GameToGUICommunication.getInstance().toGUI("Non hai ancora ritrovato questo colore...");
                return;
            }

            if (!((Item) output.getFirstObject()).getPaintable()) {
                GameToGUICommunication.getInstance().toGUI("Sembra che la pittura non abbia effetto su questo oggetto.");
                return;
            }
        }

        //Se è un comando di movimento, imposta la stanza corrente come primo oggetto dell'output,
        //così da poter eseguire il movimento come un'interazione sulla stanza se ce ne fosse bisogno
        if (movementCommands.contains(command)) {
            if (output.getFirstObject() != null || output.getSecondObject() != null) {
                invalidCommandOutput();
                return;
            } else {
                output.setFirstObject(this.getCurrentRoom());

                RoomConnection destination = super.getCurrentRoom().getRoomConnection(command);

                //Verifica ed esegue il comando di movimento
                if (destination == null) {
                    GameToGUICommunication.getInstance().toGUI("Non c'è nulla in quella direzione.");
                } else if (destination.isLocked()) {
                    GameToGUICommunication.getInstance().toGUI("La porta è chiusa.");
                } else {
                    setCurrentRoom(destination.getReachableRoom());
                    Mixer.getInstance().changRoomMusic(getCurrentRoom().getName());
                }
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
            if (interaction.isCorrectInteraction(gameObjects, command)){
                interaction.executeInteraction(this);
                didSomething = true;
            }
        }

        //Se nessuna interaction è stata effettuata, notifica l'utente
        if (!didSomething) {
            if (command != Command.PRENDI && !movementCommands.contains(command)) {
                GameToGUICommunication.getInstance().toGUI("Non è successo niente.");
            }
        }
    }

    /**
     * Metodo che notifica l'utente che il comando generico è invalido. Definizione del metodo di GameEngine.
     */
    @Override
    public void invalidCommandOutput() {
        GameToGUICommunication.getInstance().toGUI("Non ho capito cosa mi vuoi dire...\n" +
                "Il comando potrebbe essere sbagliato o non ho trovato l'oggetto che hai chiesto.");
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