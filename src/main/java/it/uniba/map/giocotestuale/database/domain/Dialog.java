package it.uniba.map.giocotestuale.database.domain;

/**
 * Classe che rappresenta il modello dell'entity Dialog (dialogo).
 */
public class Dialog {

    /**
     * Identificativo univoco del dialogo.
     */
    private int id;

    /**
     * testo del dialogo.
     */
    private String testo;

    /**
     * Costruttore di default.
     */
    public Dialog() {
    }

    /**
     * Restituisce l'identificativo del dialogo.
     *
     * @return l'identificativo del dialogo.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del dialogo.
     *
     * @param id l'identificativo del dialogo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il testo del dialogo.
     *
     * @return il testo del dialogo.
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Imposta il testo del dialogo.
     *
     * @param testo il testo del dialogo.
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * Restituisce la rappresentazione in formato stringa dell'oggetto Dialog.
     *
     * @return una stringa che rappresenta l'oggetto Dialog.
     */
    @Override
    public String toString() {
        return "Dialog{" +
                "id=" + getId() +
                ", testo='" + getTesto() + "'" +
                "}";
    }
}
