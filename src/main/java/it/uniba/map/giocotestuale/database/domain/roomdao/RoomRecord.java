package it.uniba.map.giocotestuale.database.domain.roomdao;

/**
 * Classe che rappresenta il modello dell'entity Room (stanza).
 */
public class RoomRecord {
    
    /**
     * Identificativo univoco della stanza (PK tecnica).
     */
    private int id;
    
    /**
     * stato della stanza.
     */
    private String stato;
    
    /**
     * descrizione della stanza.
     */
    private String descrizione;
    
    /**
     * Identificativo della stanza nel gioco.
     */
    private int idRoom;

    /**
     * Costruttore di default.
     */
    public RoomRecord() {}

    /**
     * Restituisce l'identificativo della stanza.
     * 
     * @return l'identificativo della stanza.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo della stanza.
     * 
     * @param id l'identificativo della stanza.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce lo stato della stanza.
     * 
     * @return lo stato della stanza.
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta lo stato della stanza.
     * 
     * @param stato lo stato della stanza.
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce la descrizione della stanza.
     * 
     * @return la descrizione della stanza.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione della stanza.
     * 
     * @param descrizione la descrizione della stanza.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce l'identificativo della stanza nel gioco.
     * 
     * @return l'identificativo della stanza nel gioco.
     */
    public int getIdRoom() {
        return idRoom;
    }

    /**
     * Imposta l'identificativo della stanza nel gioco.
     * 
     * @param idRoom l'identificativo della stanza nel gioco.
     */
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    /**
     * Restituisce la rappresentazione in formato stringa dell'oggetto Contenuto.
     * 
     * @return una stringa che rappresenta l'oggetto Contenuto.
     */
    @Override
    public String toString() {
        return "Room{" +
            "id=" + getId() +
            ", stato=" + getStato() +
            ", descrizione='" + getDescrizione() + "'" +
            ", id_room='" + getIdRoom() + "'" +
            "}";
    }
}
