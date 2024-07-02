package it.uniba.map.giocotestuale.utility.jsonutil;

import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;

/**
 * La classe GameToJson costituisce un'utility per la conversione delle informazioni di gioco in formato JSON.
 */
public class GameToJson {
    private String player;
    private List<ColorClass> colors;
    private List<Room> rooms;
    private Room room;
    private List<Item> inventario;

    /**
     * Costruisce un nuovo oggetto GameToJson con i dettagli del gioco specificati.
     *
     * @param player    il nome del giocatore
     * @param colors    la lista dei colori del gioco
     * @param rooms     la lista delle stanze nel gioco
     * @param room      la stanza corrente del giocatore
     * @param inventario la lista degli oggetti nell'inventario del giocatore
     */
    public GameToJson(String player, List<ColorClass> colors, List<Room> rooms, Room room, List<Item> inventario) {
        super();
        this.player = player;
        this.colors = colors;
        this.rooms = rooms;
        this.room = room;
        this.inventario = inventario;
    }

    /**
     * Costruisce un nuovo oggetto GameToJson vuoto.
     */
    public GameToJson() {
    }

    /**
     * Restituisce il nome del player.
     *
     * @return il nome del player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Imposta il nome del player.
     *
     * @param player il nome del player
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Restituisce la lista dei colori del gioco.
     *
     * @return la lista dei colori
     */
    public List<ColorClass> getColors() {
        return colors;
    }

    /**
     * Imposta la lista dei colori del gioco.
     *
     * @param rooms la lista dei colori
     */
    public void setColors(List<ColorClass> colors) {
        this.colors = colors;
    }

    /**
     * Restituisce la lista delle stanze nel gioco.
     *
     * @return la lista delle stanze
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Imposta la lista delle stanze nel gioco.
     *
     * @param rooms la lista delle stanze
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Restituisce la stanza corrente del giocatore.
     *
     * @return la stanza corrente
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Imposta la stanza corrente del giocatore.
     *
     * @param room la stanza corrente
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Restituisce la lista degli oggetti nell'inventario del giocatore.
     *
     * @return la lista degli oggetti nell'inventario
     */
    public List<Item> getInventario() {
        return inventario;
    }

    /**
     * Imposta la lista degli oggetti nell'inventario del giocatore.
     *
     * @param inventario la lista degli oggetti nell'inventario
     */
    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }
}
