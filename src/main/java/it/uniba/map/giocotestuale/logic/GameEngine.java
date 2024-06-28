package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.artwork.CommandClass;
import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.ParserOutput;
import it.uniba.map.giocotestuale.utility.Timer;

import java.util.ArrayList;
import java.util.Set;

/**
 * Classe astratta che rappresenta un possibile stampo che le avventure testuali possono seguire.
 * Per scrivere la propria avventura testuale con la nostra repo, sarà sufficiente creare una
 * classe che estenda GameEngine e definirne i metodi astratti.
 */
public abstract class GameEngine {
    /**
     * Lista di stanze che compongono il gioco.
     */
    private ArrayList<Room> rooms;
    /**
     * Lista che rappresenta l'inventario del player.
     */
    private ArrayList<Item> inventory;
    /**
     * Lista che contiene tutte le interazioni di gioco.
     */
    private ArrayList<Interaction> gameInteractions;
    /**
     * Timer di gioco.
     */
    private final Timer gameTimer;
    /**
     * Nome del player.
     */
    private String playerName;
    /**
     * Stanza in cui si trova attualmente il player.
     */
    private Room currentRoom;

    /**
     * Costruttore della classe GameEngine. Crea le varie liste e istanzia il timer.
     */
    public GameEngine() {
        rooms = new ArrayList<>();
        inventory = new ArrayList<>();
        gameInteractions = new ArrayList<>();
        gameTimer = new Timer();
    }

    /**
     * Metodo setter per il nome del player.
     * @param playerName Nome del player da impostare.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Metodo getter per il nome del player.
     * @return Nome del player.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Metodo setter per la stanza corrente. Verifica che la stanza non sia nulla e che sia presente nel gioco.
     * @param room Nuova stanza corrente.
     * @return Booleano che indica se il cambio di stanza è andato a buon fine.
     */
    public boolean setCurrentRoom(Room room) {
        if (room != null && rooms.contains(room)) {
            this.currentRoom = room;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo che imposta la stanza corrente a null.
     */
    public void setCurrentRoomToNull() {
        this.currentRoom = null;
    }

    /**
     * Metodo getter per la stanza corrente.
     * @return Stanza corrente.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Aggiunge la stanza passata come parametro alla lista di stanze del gioco.
     * @param room Stanza da aggiungere.
     */
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    /**
     * Aggiunge l'item passato come parametro all'inventario del player.
     * @param item Item da aggiungere.
     */
    public void addItemToInventory(Item item) {
        this.inventory.add(item);
    }

    /**
     * Rimuove l'item passato come parametro dall'inventario del player.
     * @param item Item da rimuovere.
     */
    public void removeItem(Item item) {
        if (inventory.contains(item)) {
            this.inventory.remove(item);
        }
    }

    /**
     * Metodo getter per la lista di stanze del gioco.
     * @return Lista di stanze del gioco.
     */
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Metodo getter per l'inventario del player.
     * @return Inventario del player.
     */
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    /**
     * Metodo getter per la lista di interazioni di gioco.
     * @return Lista di interazioni di gioco.
     */
    public ArrayList<Interaction> getGameInteractions() {
        return this.gameInteractions;
    }

    /**
     * Metodo getter per il timer di gioco.
     * @return Istanza del timer del gioco.
     */
    public Timer getGameTimer() {
        return this.gameTimer;
    }

    /**
     * Restituisce la lista di tutti gli item presenti nel gioco.
     * Scorre quindi tutte le stanze per ricavarne gli item.
     * @return Lista contenente tutti gli item del gioco.
     */
    public ArrayList<GameObject> getAllGameItems() {
        ArrayList<GameObject> gameItems = new ArrayList<>();
        for (Room room : rooms) {
            gameItems.addAll(room.getItemsInRoom());
        }
        return gameItems;
    }

    /**
     * Metodo astratto che si occuperà di gestire l'intro del gioco.
     */
    public abstract void welcomePlayer();

    /**
     * Metodo astratto che si occuperà di gestire il comando help.
     */
    public abstract void help();

    /**
     * Metodo astratto che restituirà tutti i comandi di gioco.
     */
    public abstract Set<CommandClass> getAllCommands();

    /**
     * Metodo astratto che si occuperà di definire la logica del gioco, nello specifico le interazioni
     * tra gli oggetti. Questo viene fatto istanziando oggetti di tipo Interaction
     * e inserendoli nell'ArrayList gameInteractions.
     */
    public abstract void defineGameInteractions();

    /**
     * Metodo astratto che si occuperà di aggiornare il gioco quando l'utente inserisce un comando.
     * A tutti gli effetti è il metodo che esegue i comandi del gioco.
     * @param output Oggetto di tipo ParserOutput ostruito sull'input dell'utente dalla classe Parser.
     */
    public abstract void update(ParserOutput output);

    /**
     * Metodo astratto che notifica l'utente che il comando generico è invalido.
     */
    public abstract void invalidCommandOutput();

    /**
     * Metodo astratto che verifica se il gioco è terminato o meno.
     * @return Booleano che indica se il gioco è terminato o meno.
     */
    public abstract boolean checkIfGameIsOver();

    /**
     * Metodo astratto che gestisce la sequenza finale del gioco.
     */
    public abstract void goodbyePlayer();
}