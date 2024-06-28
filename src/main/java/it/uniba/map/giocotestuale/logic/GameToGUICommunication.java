package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.gui.HandlerGUI;

/**
 * Classe che gestisce la comunicazione tra la GUI di gioco e il gioco stesso.
 */
public class GameToGUICommunication {
    /**
     * Istanza della GUI di gioco.
     */
    private final HandlerGUI gameGUI;
    /**
     * Istanza del gioco vero e proprio.
     */
    private final GameEngine gameEngine;

    /**
     * Costruttore con parametri della classe. Istanzia tutti gli attributi.
     * @param gameGUI Istanza della GUI di gioco.
     * @param gameEngine Istanza del gioco.
     */
    public GameToGUICommunication(HandlerGUI gameGUI, GameEngine gameEngine) {
        this.gameGUI = gameGUI;
        this.gameEngine = gameEngine;
    }

    /**
     * Metodo getter per l'istanza della GUI di gioco.
     * @return Istanza della GUI di gioco.
     */
    public HandlerGUI getGameGUI() {
        return this.gameGUI;
    }

    /**
     * Metodo getter per l'istanza del gioco.
     * @return Istanza del gioco.
     */
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * Metodo che gestirà l'input ricevuto dall'utente tramite GUI.
     * In parole povere, chiamerà il Parser sull'input dell'utente preso dalla GUI
     * e ne comunicherà il risultato all'istanza di gioco affinché possa gestirlo.
     */
    public void GUIToGame() {
        //Scrivere qui il codice per la gestione della comunicazione tra gioco e GUI
    }

    /**
     * Metodo che gestirà l'output che l'utente vedrà sulla GUI:
     * In parole povere, restituirà alla GUI la risposta del gioco alle azioni dell'utente.
     */
    public void gameToGUI() {
        //Scrivere qui il codice per la gestione della comunicazione tra gioco e GUI
    }
}
