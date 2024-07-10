package it.uniba.map.giocotestuale.entities.game;

import it.uniba.map.giocotestuale.logic.GameEngine;

import java.io.Serializable;

/**
 * Classe che rappresenta il collegamento unilaterale da una stanza all'altra.
 * Nonostante non siano implementati collegamenti del genere nel nostro gioco The Colors Within Your Soul,
 * si è preferito definire il collegamento come unilaterale così da lasciare la possibilità di creare
 * dei collegamenti che vadano bene da una stanza all'altra ma non viceversa, cioè a una via.
 * Nel nostro gioco, ogni RoomConnection è quindi specchiata tra le due stanze.
 */
public class RoomConnection implements Serializable {
    /**
     * ID della stanza raggiungibile.
     */
    private int IDReachableRoom;
    /**
     * Booleano che definisce se il collegamento è bloccato o meno.
     */
    private boolean isLocked;

    /**
     * Costruttore con parametri della classe RoomConnection. Inizializza tutti gli attributi.
     *
     * @param reachableRoom Stanza raggiungibile.
     * @param isLocked      Booleano che definisce se il collegamento è bloccato o meno.
     */
    public RoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.IDReachableRoom = reachableRoom.getId();
        this.isLocked = isLocked;
    }

    /**
     * Metodo getter per la stanza raggiungibile. Prende l'istanza di gioco e restituisce la stanza con l'ID cercato.
     *
     * @param gameEngine Istanza del gioco in cui va cercata la stanza.
     * @return Stanza raggiungibile. Se non esiste, restituisce null.
     */
    public Room getReachableRoom(final GameEngine gameEngine) {
        for (Room room : gameEngine.getRooms()) {
            if (room.getId() == IDReachableRoom) {
                return room;
            }
        }
        return null;
    }

    /**
     * Metodo setter per la stanza raggiungibile.
     *
     * @param reachableRoom Nuova stanza raggiungibile.
     */
    public void setReachableRoom(final Room reachableRoom) {
        this.IDReachableRoom = reachableRoom.getId();
    }

    /**
     * Metodo getter per la proprietà isLocked.
     *
     * @return Booleano che indica se il collegamento è bloccato o meno.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Metodo setter per la proprietà isLocked.
     *
     * @param isLocked Nuovo valore che indica se il collegamento è bloccato o meno.
     */
    public void setLocked(final boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Metodo che sblocca il collegamento. Equivalente alla chiamata setLocked(false).
     */
    public void unlock() {
        setLocked(false);
    }
}
