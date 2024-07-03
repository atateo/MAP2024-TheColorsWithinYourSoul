package it.uniba.map.giocotestuale.database.domain;

import java.io.Serializable;

/**
 * Classe che rappresenta il modello di Score (punteggio).
 */
public class Score implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Metodo costruttore della classe Score
     * @param id
     * @param player
     * @param time
     */
    public Score(int id, String player, String time) {
        this.id = id;
        this.player = player;
        this.time = time;
    }
    
    /**
     * Identificativo del punteggio.
     */
    private int id;
    
    /**
     * player che ha registrato il punteggio.
     */
    private String player;
    
    /**
     * tempo impiegato per finire il gioco.
     */
    private String time;
    
    /**
     * Costruttore di default.
     */
    public Score() {}

    /**
     * Restituisce l'identificativo del punteggio.
     * @return l'identificativo del punteggio.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del punteggio.
     * @param id l'identificativo del punteggio.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il player che ha ottenuto il punteggio.
     * @return il player che ha ottenuto il punteggio.
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Imposta il player.
     * @param player player da impostare.
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Restituisce il tempo impiegato da un player.
     * @return il tempo impiegato da un player.
     */
    public String getTime() {
        return time;
    }

    /**
     * Imposta il tempo impiegato da un player.
     * @param time tempo impiegato da un player.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Restituisce la rappresentazione in formato stringa dell'oggetto Score.
     * @return una stringa che rappresenta l'oggetto Score.
     */
    @Override
    public String toString() {
        return "Score{" +
            "id=" + getId() +
            ", player=" + getPlayer() +
            ", tempo='" + getTime() + "'" +
            "}";
    }
}
