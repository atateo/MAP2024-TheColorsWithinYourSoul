package it.uniba.map.giocotestuale.entities.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta le stanze di cui è composta la mappa di gioco.
 */
public class Room extends GameObject {
    /**
     * Oggetto che rappresenta il collegamento verso nord della stanza.
     */
    private RoomConnection north;
    /**
     * Oggetto che rappresenta il collegamento verso sud della stanza.
     */
    private RoomConnection south;
    /**
     * Oggetto che rappresenta il collegamento verso ovest della stanza.
     */
    private RoomConnection west;
    /**
     * Oggetto che rappresenta il collegamento verso est della stanza.
     */
    private RoomConnection east;

    /**
     * Lista degli item contenuti nella stanza.
     */
    private List<Item> itemsInRoom;

    /**
     * Costruttore con parametri della classe Room. Inizializza gli attributi della superclasse GameObject
     * e crea la lista degli item.
     * @param id ID della stanza.
     * @param name Nome della stanza.
     * @param state Stato corrente della stanza.
     */
    public Room(final int id, final String name, final String state) {
        super(id, name, null, state);
        itemsInRoom = new ArrayList<>();
    }

    /**
     * Stabilisce un collegamento a nord con la stanza di questa istanza e la stanza passata come parametro.
     * Col secondo parametro è possibile definire se il collegamento è bloccato o meno.
     * @param reachableRoom Stanza raggiungibile a nord.
     * @param isLocked Booleano che indica se il collegamento è bloccato o no.
     */
    public void setNorthRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.north = new RoomConnection(reachableRoom, isLocked);
    }

    /**
     * Stabilisce un collegamento a sud con la stanza di questa istanza e la stanza passata come parametro.
     * Col secondo parametro è possibile definire se il collegamento è bloccato o meno.
     * @param reachableRoom Stanza raggiungibile a sud.
     * @param isLocked Booleano che indica se il collegamento è bloccato o no.
     */
    public void setSouthRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.south = new RoomConnection(reachableRoom, isLocked);
    }

    /**
     * Stabilisce un collegamento a ovest con la stanza di questa istanza e la stanza passata come parametro.
     * Col secondo parametro è possibile definire se il collegamento è bloccato o meno.
     * @param reachableRoom Stanza raggiungibile a ovest.
     * @param isLocked Booleano che indica se il collegamento è bloccato o no.
     */
    public void setWestRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.west = new RoomConnection(reachableRoom, isLocked);
    }

    /**
     * Stabilisce un collegamento a est con la stanza di questa istanza e la stanza passata come parametro.
     * Col secondo parametro è possibile definire se il collegamento è bloccato o meno.
     * @param reachableRoom Stanza raggiungibile a est.
     * @param isLocked Booleano che indica se il collegamento è bloccato o no.
     */
    public void setEastRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.east = new RoomConnection(reachableRoom, isLocked);
    }

    /**
     * Metodo setter per la lista di item nella stanza.
     * @param itemsInRoom Nuova lista di item nella stanza.
     */
    public void setItemsInRoom(final List<Item> itemsInRoom) {
        this.itemsInRoom = itemsInRoom;
    }

    /**
     * Metodo che aggiunge l'item passato come parametro alla lista di item nella stanza.
     * @param item Item da aggiungere nella stanza.
     */
    public void addItem(final Item item) {
        this.itemsInRoom.add(item);
    }

    /**
     * Metodo che rimuove l'item passato come parametro alla lista di item nella stanza.
     * @param item Item da rimuovere nella stanza.
     */
    public void removeItem(final Item item) {
        this.itemsInRoom.remove(item);
    }

    /**
     * Metodo getter per il collegamento a nord della stanza.
     * @return Collegamento a nord della stanza.
     */
    public RoomConnection getNorthRoomConnection() {
        return this.north;
    }

    /**
     * Metodo getter per il collegamento a sud della stanza.
     * @return Collegamento a sud della stanza.
     */
    public RoomConnection getSouthRoomConnection() {
        return this.south;
    }

    /**
     * Metodo getter per il collegamento a ovest della stanza.
     * @return Collegamento a ovest della stanza.
     */
    public RoomConnection getWestRoomConnection() {
        return this.west;
    }

    /**
     * Metodo getter per il collegamento a est della stanza.
     * @return Collegamento a est della stanza.
     */
    public RoomConnection getEastRoomConnection() {
        return this.east;
    }

    /**
     * Metodo getter per la lista di item nella stanza.
     * @return Lista di item nella stanza.
     */
    public List<Item> getItemsInRoom() {
        return this.itemsInRoom;
    }

    /**
     * Metodo che interroga il DB sulla base degli attributi della stanza per ottenerne la descrizione.
     * Definisce il metodo getDescriptionFromDB ereditato dalla superclasse.
     * @return Descrizione della stanza in questione.
     */
    @Override
    public String getDescriptionFromDB() {
        //Placeholder, sarà implementato insieme al DB
        return null;
    }
}
