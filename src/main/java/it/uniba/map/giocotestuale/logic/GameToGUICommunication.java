package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.gui.HandlerGUI;

/**
 * Classe che gestisce la comunicazione tra la GUI di gioco e il gioco stesso. È una classe singleton.
 */
public class GameToGUICommunication {
    /**
     * Singola istanza della classe GameToGUICommunication.
     */
    private static GameToGUICommunication instance;
    /**
     * Istanza della GUI di gioco.
     */
    private HandlerGUI gameGUI;
    /**
     * Istanza del gioco vero e proprio.
     */
    private GameEngine gameEngine;

    /**
     * Costruttore base della classe. Non ha parametri ed è privato.
     */
    private GameToGUICommunication() {}

    /**
     * Restituisce la singola istanza di questa classe.
     * @return L'istanza della classe.
     */
    public static GameToGUICommunication getInstance() {
        if (instance == null) {
            instance = new GameToGUICommunication();
        }
        return instance;
    }

    /**
     * Serve per inizializzare gli attributi dell'istanza della classe.
     * @param gameGUI GUI di gioco su cui bisogna operare.
     * @param gameEngine Istanza del gioco in esecuzione.
     */
    public void setAttributes(HandlerGUI gameGUI, GameEngine gameEngine) {
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
