package it.uniba.map.giocotestuale.database.domain;

/**
 * Classe che rappresenta il modello dell'entity Color (colore).
 */
public class ColorRecord {

    /**
     * Identificativo univoco del Colore.
     */
    private int id;

    /**
     * Descrizione dell'item.
     */
    private String descrizione;

    /**
     * Costruttore di default.
     */
    public ColorRecord() {
    }

    /**
     * Restituisce l'identificativo del colore.
     *
     * @return l'identificativo del colore.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del colore.
     *
     * @param id l'identificativo del colore.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce la descrizione del colore.
     *
     * @return la descrizione del colore.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del colore.
     *
     * @param descrizione la descrizione del colore.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce la rappresentazione in formato stringa dell'oggetto Color.
     *
     * @return una stringa che rappresenta l'oggetto Color.
     */
    @Override
    public String toString() {
        return "Color{" +
                "id=" + getId() +
                ", descrizione='" + getDescrizione() + "'" +
                "}";
    }
}
