package it.uniba.map.giocotestuale.database.domain;

/**
 * Classe che rappresenta il modello dell'entity Item (oggetto).
 */
public class ItemRecord {

    /**
     * Identificativo univoco dell'Item (PK tecnica).
     */
    private int id;

    /**
     * Stato dell'item.
     */
    private String stato;

    /**
     * Descrizione dell'item.
     */
    private String descrizione;

    /**
     * Identificativo dell'item nel gioco.
     */
    private int idItem;

    /**
     * Costruttore di default.
     */
    public ItemRecord() {
    }

    /**
     * Restituisce l'identificativo dell'item.
     *
     * @return l'identificativo dell'item.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo dell'item.
     *
     * @param id l'identificativo dell'item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce lo stato dell'item.
     *
     * @return lo stato dell'item.
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta lo stato dell'item.
     *
     * @param stato lo stato dell'item.
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce la descrizione dell'item.
     *
     * @return la descrizione dell'item.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione dell'item.
     *
     * @param descrizione la descrizione dell'item.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce l'identificativo dell'item nel gioco.
     *
     * @return l'identificativo dell'item nel gioco.
     */
    public int getIdItem() {
        return idItem;
    }

    /**
     * Imposta l'identificativo dell'item nel gioco.
     *
     * @param idItem l'identificativo dell'item nel gioco.
     */
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    /**
     * Restituisce la rappresentazione in formato stringa dell'oggetto Contenuto.
     *
     * @return una stringa che rappresenta l'oggetto Contenuto.
     */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + getId() +
                ", stato=" + getStato() +
                ", descrizione='" + getDescrizione() + "'" +
                ", id_item='" + getIdItem() + "'" +
                "}";
    }
}
