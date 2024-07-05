package it.uniba.map.giocotestuale.utility;

import java.io.Serializable;

/**
 * Classe che rappresenta il timer di gioco. Tutti i valori temporali sono in ms.
 */
public class GameTimer implements Serializable {
    /**
     * Tempo di partenza del timer.
     */
    private long startTime;
    /**
     * Tempo di fine del timer.
     */
    private long endTime;
    /**
     * Tempo trascorso col timer attivo.
     */
    private long elapsedTime;
    /**
     * Booleano che indica se il timer è attivo o meno.
     */
    private boolean isRunning;

    /**
     * Costanti utili per la formattazione del tempo in hh:mm:ss.
     */
    final static int DIVISOR = 1000;
    final static int FORMAT = 60;
    final static int DAY = 24;

    /**
     * Costruttore senza parametri di Timer. Invoca il metodo reset.
     */
    public GameTimer() {
        reset();
    }

    /**
     * Inizializza e fa partire il timer.
     */
    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    /**
     * Inizializza e fa partire il timer, usando il tempo di partenza passato come parametro.
     * @param elapsedTime Tempo di partenza.
     */
    public void startAgain(long elapsedTime) {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            this.elapsedTime = elapsedTime;
            isRunning = true;
        }
    }

    /**
     * Ferma il timer e calcola il nuovo valore di elapsedTime.
     */
    public void stop() {
        if (isRunning) {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            isRunning = false;
        }
    }

    /**
     * Resetta il timer, reimpostando tutti gli attributi a 0 e fermandolo.
     */
    public void reset() {
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    /**
     * Metodo getter per il tempo trascorso col timer attivo.
     * @return Tempo trascorso col timer attivo.
     */
    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return elapsedTime;
        }
    }

    /**
     * Metodo setter per il tempo trascorso col timer attivo.
     * @param elapsedTime Nuovo tempo trascorso col timer attivo.
     */
    public void setElapsedTime(final long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Metodo che restituisce lo stato del timer.
     * @return Booleano che indica se il timer è attivo o meno.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Il metodo getStartTime() restituisce il tempo di inizio.
     * @return tempo di inizio
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Restituisce una stringa formattata che rappresenta il tempo attuale segnato dal timer.
     * @return Tempo formattato in hh::mm:ss.
     */
    public String getTimeFormatted() {
        long time = getElapsedTime();
        long second = (time / DIVISOR) % FORMAT;
        long minute = (time / (DIVISOR * FORMAT)) % FORMAT;
        long hour = (time / (DIVISOR * FORMAT * FORMAT)) % DAY;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
