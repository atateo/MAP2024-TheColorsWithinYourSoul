package it.uniba.map.giocotestuale.utility;

public final class Timer {
    private long startTime;
    private long endTime;
    private long elapsedTime;
    private boolean isRunning;

    final static int DIVISOR = 1000;
    final static int FORMAT = 60;
    final static int DAY = 24;

    /**
     * Costruttore privato della classe.
     */
    public Timer() {
        reset();
    }

    /**
     * Il metodo start() inizializza il cronometro.
     */
    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    /**
     * Il metodo stop() ferma il cronometro.
     */
    public void stop() {
        if (isRunning) {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            isRunning = false;
        }
    }

    /**
     * Il metodo reset() resetta il cronometro.
     */
    public void reset() {
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    /**
     * Il metodo getElapsedTime() restituisce il tempo trascorso.
     * @return tempo trascorso
     */
    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return elapsedTime;
        }
    }

    /**
     * Il metodo setElapsedTime() imposta il tempo trascorso.
     * @param trascorsoTime tempo trascorso
     */
    public void setElapsedTime(final long trascorsoTime) {
        this.elapsedTime = trascorsoTime;
    }

    /**
     * Il metodo isRunning() restituisce true se il cronometro è in esecuzione.
     * @return true se il cronometro è in esecuzione, false altrimenti
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
     * Il metodo getTimeFormatted() restituisce il tempo formattato in maniera leggibile nel formato hh:mm:ss.
     * @return tempo formattato
     */
    public String getTimeFormatted() {
        long time = getElapsedTime();
        long second = (time / DIVISOR) % FORMAT;
        long minute = (time / (DIVISOR * FORMAT)) % FORMAT;
        long hour = (time / (DIVISOR * FORMAT * FORMAT)) % DAY;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
