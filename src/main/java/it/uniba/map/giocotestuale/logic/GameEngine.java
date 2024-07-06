package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.game.*;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.ParserOutput;
import it.uniba.map.giocotestuale.utility.GameTimer;

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
     * Lista contenente tutti i colori del gioco.
     */
    private ArrayList<ColorClass> colors;
    /**
     * Lista che contiene tutte le interazioni di gioco.
     */
    private ArrayList<Interaction> gameInteractions;
    /**
     * Timer di gioco.
     */
    private GameTimer gameTimer;
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
        colors = new ArrayList<>();
        gameInteractions = new ArrayList<>();
        gameTimer = new GameTimer();
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
     * Metodo getter per la lista dei colori del gioco.
     * @return Lista dei colori del gioco.
     */
    public ArrayList<ColorClass> getColors() {
        return this.colors;
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
    public GameTimer getGameTimer() {
        return this.gameTimer;
    }

    /**
     * Metodo setter per il timer di gioco.
     * @param gameTimer Istanza del timer da impostare come nuovo timer.
     */
    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    /**
     * Restituisce la lista di tutti gli item e colori presenti nel gioco.
     * Scorre quindi tutte le stanze e l'inventario del player per ricavarne gli item.
     * @return Lista contenente tutti gli item del gioco.
     */
    public ArrayList<GameObject> getAllObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for (Room room : rooms) {
            gameObjects.addAll(room.getItemsInRoom());
        }

        gameObjects.addAll(colors);
        gameObjects.addAll(inventory);

        return gameObjects;
    }

    /**
     * Dato un nome passato come parametro, restituisce l'item con quel nome.
     * @param searchedName Nome da cercare.
     * @return Oggetto con il nome cercato. Se non esiste, restituisce null.
     */
    public Item getItemByName(final String searchedName) {
        for (Room room : rooms) {
            for (Item item : room.getItemsInRoom()) {
                if (item.getName().equalsIgnoreCase(searchedName)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Dato un nome passato come parametro, restituisce il colore con quel nome.
     * @param searchedName Nome da cercare.
     * @return Colore con il nome cercato. Se non esiste, restituisce null.
     */
    public ColorClass getColorByName(final String searchedName) {
        for (ColorClass color: colors) {
            if (color.getName().equalsIgnoreCase(searchedName)) {
                return color;
            }
        }
        return null;
    }

    /**
     * Dato un nome passato come parametro, restituisce la stanza con quel nome.
     * @param searchedName Nome da cercare.
     * @return Stanza con il nome cercato. Se non esiste, restituisce null.
     */
    public Room getRoomByName(final String searchedName) {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(searchedName)) {
                return room;
            }
        }
        return null;
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