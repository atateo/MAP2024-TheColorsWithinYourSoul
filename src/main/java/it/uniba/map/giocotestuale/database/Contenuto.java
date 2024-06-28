package it.uniba.map.giocotestuale.database;

/**
 * Classe che rappresenta il modello di un Contenuto.
 */
public class Contenuto {
    
    /**
     * Identificativo del contenuto.
     */
    private int id;
    
    /**
     * Etichetta del contenuto.
     */
    private String label;
    
    /**
     * Messaggio del contenuto.
     */
    private String messaggio;
    
    /**
     * Indica se il contenuto è una risposta.
     */
    private boolean isRisposta;
    
    /**
     * Identificativo dell'item associato al contenuto.
     */
    private int idItem;

    /**
     * Costruttore di default.
     */
    public Contenuto() {}

    /**
     * Restituisce l'identificativo del contenuto.
     * 
     * @return l'identificativo del contenuto.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del contenuto.
     * 
     * @param id l'identificativo del contenuto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce l'etichetta del contenuto.
     * 
     * @return l'etichetta del contenuto.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Imposta l'etichetta del contenuto.
     * 
     * @param label l'etichetta del contenuto.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Restituisce il messaggio del contenuto.
     * 
     * @return il messaggio del contenuto.
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il messaggio del contenuto.
     * 
     * @param messaggio il messaggio del contenuto.
     */
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    /**
     * Restituisce se il contenuto è una risposta.
     * 
     * @return true se il contenuto è una risposta, false altrimenti.
     */
    public boolean isRisposta() {
        return isRisposta;
    }

    /**
     * Imposta se il contenuto è una risposta.
     * 
     * @param isRisposta true se il contenuto è una risposta, false altrimenti.
     */
    public void setRisposta(boolean isRisposta) {
        this.isRisposta = isRisposta;
    }

    /**
     * Restituisce l'identificativo dell'item associato al contenuto.
     * 
     * @return l'identificativo dell'item associato al contenuto.
     */
    public int getIdItem() {
        return idItem;
    }

    /**
     * Imposta l'identificativo dell'item associato al contenuto.
     * 
     * @param idItem l'identificativo dell'item associato al contenuto.
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
        return "Contenuto{" +
            "id=" + getId() +
            ", label=" + getLabel() +
            ", messaggio='" + getMessaggio() + "'" +
            ", isRisposta='" + isRisposta() + "'" +
            ", idTest='" + getIdItem() + "'" +
            "}";
    }
}
