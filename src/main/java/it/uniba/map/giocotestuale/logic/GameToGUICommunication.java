package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.gui.GameGUI;
import it.uniba.map.giocotestuale.gui.HandlerGUI;

import java.awt.*;

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
     * Istanza del Parser che sarà usata per processare gli input.
     */
    private Parser parser;

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
        this.parser = new Parser(gameEngine);
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
     * @param input Stringa di input dell'utente.
     */
    public void toGame(final String input) {
        GameGUI.writeOnPanel("□ " + input);
        gameEngine.update(parser.parse(input));
    }

    /**
     * Metodo che gestirà l'output che l'utente vedrà sulla GUI:
     * In parole povere, restituirà alla GUI la risposta del gioco alle azioni dell'utente.
     * @param output Stringa di output da stampare sulla GUI.
     */
    public void toGUI(final String output) {
        GameGUI.writeOnPanel(formatText("> " + output));
    }

    /**
     * Metodo che farà partire l'intro di gioco.
     */
    public void start() {
        gameEngine.welcomePlayer();
    }

    /**
     * Questo metodo serve per formattare la stringa passata come parametro in modo che,
     * se dovesse venire stampata sulla GUI, non darebbe problemi di formattazione al panel
     * (va a capo quando il testo è troppo lungo, ad esempio).
     * @param text Stringa di testo da formattare.
     * @return Stringa formattata.
     */
    private String formatText(String text) {
        FontMetrics fontMetrics = GameGUI.getTextPaneFontMetrics();
        int maxWidth = GameGUI.getTextPaneWidth();

        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");

        //Se una parola supera la lunghezza del panel, la spezza e va a capo
        for (String word : words) {
            StringBuilder line = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (fontMetrics.stringWidth(line.toString() + c + fontMetrics.charWidth('-')) > maxWidth) {
                    result.append(line).append("-\n");
                    line.setLength(0);
                }
                line.append(c);
            }
            result.append(line).append(" ");
        }

        return result.toString();
    }
}
