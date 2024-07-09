package it.uniba.map.giocotestuale.utility.jsonutil;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;
import it.uniba.map.giocotestuale.impl.ColorsWithinYourSoulGame;
import it.uniba.map.giocotestuale.utility.GameTimer;

/**
 * La classe GameToJson costituisce un'utility per la conversione delle informazioni di gioco in formato JSON.
 */
public class GameToJson implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    private GameTimer timer;
    private List<ColorClass> colors;
    private List<Room> rooms;
    private Room room;
    private List<Item> inventario;

    /**
     * Costruisce un nuovo oggetto GameToJson con i dettagli del gioco specificati.
     *
     * @param timer      tempo di gioco
     * @param colors     la lista dei colori del gioco
     * @param rooms      la lista delle stanze nel gioco
     * @param room       la stanza corrente del giocatore
     * @param inventario la lista degli oggetti nell'inventario del giocatore
     */
    public GameToJson(GameTimer timer, List<ColorClass> colors, List<Room> rooms, Room room, List<Item> inventario) {
        this.timer = timer;
        this.colors = colors;
        this.rooms = rooms;
        this.room = room;
        this.inventario = inventario;
    }

    /**
     * Costruisce un nuovo oggetto GameToJson partendo da un'istanza di gioco in corso.
     *
     * @param game L'istanza di gioco da convertire in GameToJson.
     */
    public GameToJson(final ColorsWithinYourSoulGame game) {
        this.timer = game.getGameTimer();
        this.colors = game.getColors();
        this.rooms = game.getRooms();
        this.room = game.getCurrentRoom();
        this.inventario = game.getInventory();
    }

    /**
     * Costruisce un nuovo oggetto GameToJson vuoto.
     */
    public GameToJson() {
    }

    /**
     * Partendo dall'istanza corrente di GameToJson, restituisce un'istanza equivalente di ColorsWithinYourSoulGame.
     *
     * @return Istanza del gioco equivalente all'istanza corrente di GameToJson.
     */
    public ColorsWithinYourSoulGame convertFromJsonObject() {
        ColorsWithinYourSoulGame game = new ColorsWithinYourSoulGame();

        if (this.inventario == null) {
            this.inventario = new ArrayList<>();
        }

        if (this.room == null) {
            this.room = this.rooms.getFirst();
        }

        if (this.timer == null) {
            this.timer = new GameTimer();
        }

        //Riempie l'inventario
        for (Item item : this.inventario) {
            game.addItemToInventory(item);
        }

        //Riempie le stanze e imposta la stanza corrente
        for (Room stanza : this.rooms) {
            game.addRoom(stanza);

            if (this.room.getId() == stanza.getId()) {
                game.setCurrentRoom(stanza);
            }
        }

        //Riempie i colori
        for (ColorClass color : this.colors) {
            game.getColors().add(color);
        }

        //Imposta il timer
        game.setGameTimer(this.timer);

        return game;
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
     * @param colors la lista dei colori
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

    /**
     * Restituisce il tempo di gioco dell'utente.
     *
     * @return il tempo di gioco dell'utente.
     */
    public GameTimer getTimer() {
        return timer;
    }

    /**
     * Imposta il tempo di gioco dell'utente.
     *
     * @param timer il tempo di gioco dell'utente.
     */
    public void setTimer(GameTimer timer) {
        this.timer = timer;
    }

}
