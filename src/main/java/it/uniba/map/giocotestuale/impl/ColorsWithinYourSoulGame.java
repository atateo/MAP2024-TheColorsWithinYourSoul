package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.database.impl.DialogDaoImpl;
import it.uniba.map.giocotestuale.entities.game.*;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.logic.interaction.ChainInteraction;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.type.ParserOutput;
import it.uniba.map.giocotestuale.utility.Mixer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe contenente l'implementazione del gioco The Colors Within Your Soul.
 *
 * @author Yuri Tateo
 * @author Antimo Tateo
 * @author Angelo Vincenti
 */
public class ColorsWithinYourSoulGame extends GameEngine {
    private DialogDaoImpl dialog;

    /**
     * Costruttore di classe. Non ha parametri. Richiama il costruttore della superclasse per
     * l'inizializzazione e successivamente crea la logica di gioco con defineGameInteraction.
     */
    public ColorsWithinYourSoulGame() {
        super();
        dialog = new DialogDaoImpl();
    }

    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();

    /**
     * Metodo che si occuperà di gestire l'intro del gioco. Definizione del metodo di GameEngine.
     */
    @Override
    public void welcomePlayer() {
        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(46));//46

        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(47));//47

        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(48));//48

        super.getGameTimer().start();
    }

    /**
     * Metodo che si occuperà di gestire il comando AIUTO. Definizione del metodo di GameEngine.
     */
    @Override
    public void help() {
        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(55));//55
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
        commands.add(new CommandClass("Back", Command.BACK, List.of("b", "indietro", "esciStanza")));
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

        ArrayList<GameObject> availableObjects = new ArrayList<>();
        availableObjects.addAll(super.getAllObjects());
        availableObjects.addAll(super.getRooms());

        super.getGameInteractions().addAll(logic.getGameLogic(availableObjects));
    }

    /**
     * Metodo che si occuperà di aggiornare il gioco quando l'utente inserisce un comando.
     * A tutti gli effetti è il metodo che esegue i comandi del gioco. Definizione del metodo in GameEngine.
     *
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

        List<Command> movementCommands = List.of(Command.NORD, Command.SUD, Command.OVEST, Command.EST, Command.BACK);
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
            GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(56));//56
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
                return;
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
                if (output.getFirstObject() instanceof ColorClass && ((ColorClass) output.getFirstObject()).isUnlocked()) {
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
            if (!getCurrentRoom().getItemsInRoom().contains((Item) output.getFirstObject())) {
                invalidCommandOutput();
                return;
            }

            if (output.getCommandType() == Command.SPINGI && !((Item) output.getFirstObject()).getMovable()) {
                GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(57));//57
                return;
            } else if (output.getCommandType() == Command.PRENDI) {
                if (!((Item) output.getFirstObject()).getPickable()) {
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(58));//58
                    return;
                } else if (!output.getFirstObject().getName().contains("Pennello")) {
                    //Aggiunge l'oggetto nell'inventario e lo rimuove dalla stanza
                    addItemToInventory((Item) output.getFirstObject());
                    getCurrentRoom().removeItem((Item) output.getFirstObject());
                    GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getName() + ": aggiunto nell'inventario.");

                    //Non fa un return perché potrebbe scatenarsi un'interaction quando il player prende un item
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
                GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(59));//59
            } else {
                //Rimuove l'oggetto nell'inventario e lo aggiunge dalla stanza
                removeItem((Item) output.getFirstObject());
                getCurrentRoom().addItem((Item) output.getFirstObject());
                GameToGUICommunication.getInstance().toGUI(output.getFirstObject().getName() + ": rimosso dall'inventario.");

                //Non fa un return perché potrebbe scatenarsi un'interaction quando il player lascia un item
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

            if (!(output.getFirstObject() instanceof Item) && !(output.getSecondObject() instanceof ColorClass)) {
                invalidCommandOutput();
                return;
            }

            if (!getCurrentRoom().getItemsInRoom().contains((Item) output.getFirstObject()) &&
                    !super.getInventory().contains((Item) output.getFirstObject())) {
                invalidCommandOutput();
                return;
            }

            if (!((ColorClass) output.getSecondObject()).isUnlocked()) {
                GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(60));//60
                return;
            }

            if (!((Item) output.getFirstObject()).getPaintable()) {
                GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(61));//61
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

                if (output.getCommandType() == Command.BACK) {
                    //Il comando back permette di uscire da una stanza con una sola porta, tornando quindi da dove si è entati
                    //Viene eseguito solo se la stanza
                    List<RoomConnection> destination;

                    //Lambda expression che prende tutti i collegamenti non nulli della stanza corrente
                    destination = Arrays.stream(Command.values())
                            .map(getCurrentRoom()::getRoomConnection)
                            .filter(connection -> connection != null)
                            .collect(Collectors.toList());

                    if (destination.size() != 1) {
                        invalidCommandOutput();
                        return;
                    } else if (destination.getFirst().isLocked()) {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(63));//63
                        return;
                    } else {
                        setCurrentRoom(destination.getFirst().getReachableRoom(this));
                        GameToGUICommunication.getInstance().notifyRoomUpdateToGUI();
                        Mixer.getInstance().changRoomMusic(getCurrentRoom().getName());
                    }
                } else {
                    RoomConnection destination = super.getCurrentRoom().getRoomConnection(command);

                    //Verifica ed esegue il comando di movimento
                    if (destination == null) {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(62));//62
                        return;
                    } else if (destination.isLocked()) {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(63));//63
                        return;
                    } else {
                        setCurrentRoom(destination.getReachableRoom(this));
                        GameToGUICommunication.getInstance().notifyRoomUpdateToGUI();
                        Mixer.getInstance().changRoomMusic(getCurrentRoom().getName());
                    }
                }
            }
        }

        //Imposta la lista degli oggetti interessati e scorre le interaction del gioco per poi eseguirle
        //nel caso corrispondano con l'output del Parser
        List<GameObject> gameObjects;

        //Se il primo oggetto non è nella stanza o nell'inventario, non eseguire i comandi
        if (!(output.getFirstObject() instanceof Room) && !(output.getFirstObject().getName().contains("Pennello"))) {
            if (!getCurrentRoom().getItemsInRoom().contains(output.getFirstObject()) && !getInventory().contains(output.getFirstObject())) {
                invalidCommandOutput();
                return;
            }
        }

        if (output.getSecondObject() == null) {
            gameObjects = List.of(output.getFirstObject());
        } else {
            gameObjects = List.of(output.getFirstObject(), output.getSecondObject());

            //Se il secondo oggetto non è nella stanza o nell'inventario, non eseguire i comandi
            if (!(output.getSecondObject() instanceof Room) && !(output.getSecondObject() instanceof ColorClass)) {
                if (!getCurrentRoom().getItemsInRoom().contains(output.getSecondObject()) && !getInventory().contains(output.getSecondObject())) {
                    invalidCommandOutput();
                    return;
                }
            }
        }

        ArrayList<Interaction> toExecute = new ArrayList<>();

        //Cicla prima le interazioni dirette
        for (Interaction interaction : super.getGameInteractions()) {
            if (!(interaction instanceof ChainInteraction) && (interaction.isCorrectInteraction(gameObjects, command))) {
                toExecute.add(interaction);
                didSomething = true;
            }
        }

        //Se ha raccolto delle interazioni dirette, le esegue
        if (didSomething) {
            for (Interaction interaction : toExecute) {
                interaction.executeInteraction(this);
            }

            //Dopo aver eseguito le interazioni dirette, vede se c'è un'interazione a catena da eseguire
            //NB: Il gioco è strutturato in modo che venga eseguita una sola ChainInteraction per volta.
            //Inoltre, le ChainInteraction vengono analizzate solo se sono state eseguite interazioni dirette prima.
            for (Interaction interaction : super.getGameInteractions()) {
                if ((interaction instanceof ChainInteraction) && (interaction.isCorrectInteraction(gameObjects, command))) {
                    interaction.executeInteraction(this);
                    break;
                }
            }
        }

        //Se nessuna interaction è stata effettuata, notifica l'utente
        if (!didSomething) {
            if (command != Command.PRENDI && command != Command.LASCIA && !movementCommands.contains(command)) {
                GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(13));//13
            }
        }

        GameToGUICommunication.getInstance().notifyInventoryUpdateToGUI();

        if (checkIfGameIsOver()) {
            GameToGUICommunication.getInstance().notifyGameOverToGUI();
            goodbyePlayer();
        }
    }


    /**
     * Metodo che notifica l'utente che il comando generico è invalido. Definizione del metodo di GameEngine.
     */
    @Override
    public void invalidCommandOutput() {
        GameToGUICommunication.getInstance().toGUI("Non ho capito cosa mi vuoi dire...\n" +
                "Il comando potrebbe essere sbagliato o non ho trovato l'oggetto che hai chiesto.");//64
    }

    /**
     * Metodo che verifica se il gioco è terminato o meno. Definizione del metodo di GameEngine.
     *
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
        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(52));//52

        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(53));//53

        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(54));//54

        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(51));//51

        super.getGameTimer().stop();
    }
}