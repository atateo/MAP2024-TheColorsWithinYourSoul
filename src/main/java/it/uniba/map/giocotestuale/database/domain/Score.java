package it.uniba.map.giocotestuale.database.domain;

import java.io.Serializable;

/**
 * Classe che rappresenta il modello di Score (punteggio).
 */
public class Score implements Serializable {

    /**
     * Metodo costruttore della classe Score
     *
     * @param id ID da impostare.
     * @param player Nome del player da impostare.
     * @param time Tempo da impostare.
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
     * Tempo impiegato per finire il gioco.
     */
    private String time;

    /**
     * Costruttore di default.
     */
    public Score() {
    }

    /**
     * Restituisce l'identificativo del punteggio.
     *
     * @return l'identificativo del punteggio.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del punteggio.
     *
     * @param id l'identificativo del punteggio.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il player che ha ottenuto il punteggio.
     *
     * @return il player che ha ottenuto il punteggio.
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Imposta il player.
     *
     * @param player player da impostare.
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Restituisce il tempo impiegato da un player.
     *
     * @return il tempo impiegato da un player.
     */
    public String getTime() {
        return time;
    }

    /**
     * Imposta il tempo impiegato da un player.
     *
     * @param time tempo impiegato da un player.
     */
    public void setTime(long time) {
        this.time = timeFormatted(time);
    }

    /**
     * Restituisce la rappresentazione in formato stringa dell'oggetto Score.
     *
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

    /**
     * Metodo che restituisce il tempo formattato (hh:mm:ss)
     *
     * @param timeTaken Tempo da formattare, espresso in millisecondi.
     * @return Tempo espresso in una stringa formattata.
     */
    public static String timeFormatted(long timeTaken) {
        long second = (timeTaken / 1000) % 60;
        long minute = (timeTaken / (1000 * 60)) % 60;
        long hour = (timeTaken / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
