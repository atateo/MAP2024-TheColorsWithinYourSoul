package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.gui.GameGUI;
import it.uniba.map.giocotestuale.gui.HandlerGUI;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.logic.Parser;
import it.uniba.map.giocotestuale.type.ColorEnum;
import it.uniba.map.giocotestuale.utility.jsonutil.GameToJson;
import it.uniba.map.giocotestuale.utility.jsonutil.JsonUtil;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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
    private GameToGUICommunication() {
    }

    /**
     * Restituisce la singola istanza di questa classe.
     *
     * @return L'istanza della classe.
     */
    public static GameToGUICommunication getInstance() {
        if (instance == null) {
            instance = new GameToGUICommunication();
        }
        return instance;
    }

    /**
     * Metodo setter per l'HandlerGUI utilizzata dalla classe.
     *
     * @param handlerGUI La GUI con la quale comunicare.
     */
    public void setHandlerGUI(HandlerGUI handlerGUI) {
        this.gameGUI = handlerGUI;
    }

    /**
     * Metodo setter per l'istanza di gioco utilizzata dalla classe.
     *
     * @param gameEngine L'istanza di gioco.
     */
    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.parser = new Parser(gameEngine);
    }

    /**
     * Metodo che usa le classi apposite per caricare una partita dal percorso file specificato.
     *
     * @param filename Nome del file da cui caricare la partita.
     */
    public void setGameEngineFromFile(String filename) {
        GameToJson game;
        File file = new File(filename);

        if (file.exists()) {

            game = JsonUtil.readJsonFromFile(filename);
            if (this.gameEngine == null) {
                BaseGameDefinition backup = new BaseGameDefinition();
                backup.createJsonBackup();
                game = JsonUtil.readJsonFromFile(filename);
            }
        } else {
            BaseGameDefinition backup = new BaseGameDefinition();
            backup.createJsonBackup();
            game = JsonUtil.readJsonFromFile(filename);
        }
        this.gameEngine = game.convertFromJsonObject();
        this.gameEngine.defineGameInteractions();
        this.parser = new Parser(this.gameEngine);
    }

    /**
     * Metodo getter per l'istanza della GUI di gioco.
     *
     * @return Istanza della GUI di gioco.
     */
    public HandlerGUI getGameGUI() {
        return this.gameGUI;
    }

    /**
     * Metodo getter per l'istanza del gioco.
     *
     * @return Istanza del gioco.
     */
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * Metodo che salverà su file l'istanza di gioco corrente.
     *
     * @param nomeFile Nome del file in cui verrà salvato il gioco.
     * @return Percorso del file in cui è stato salvato il gioco.
     */
    public String saveGame(String nomeFile) {
        if (nomeFile == null || nomeFile.isEmpty()) {
            nomeFile = "saves" + File.separator;
        }

        gameEngine.getGameTimer().stop();
        JsonUtil.writeJsonToFile(nomeFile, new GameToJson((ColorsWithinYourSoulGame) this.gameEngine));
        gameEngine.getGameTimer().startAgain(gameEngine.getGameTimer().getElapsedTime());

        return nomeFile;
    }

    /**
     * Metodo che gestirà l'input ricevuto dall'utente tramite GUI.
     * In parole povere, chiamerà il Parser sull'input dell'utente preso dalla GUI
     * e ne comunicherà il risultato all'istanza di gioco affinché possa gestirlo.
     *
     * @param input Stringa di input dell'utente.
     */
    public void toGame(final String input) {
        GameGUI.writeOnPanel("➤ " + input);
        gameEngine.update(parser.parse(input));
    }

    /**
     * Metodo che gestirà l'output che l'utente vedrà sulla GUI:
     * In parole povere, restituirà alla GUI la risposta del gioco alle azioni dell'utente.
     *
     * @param output Stringa di output da stampare sulla GUI.
     */
    public void toGUI(final String output) {
        GameGUI.writeOnPanel(formatText("\uD83C\uDFA8 ➤ " + output));
    }

    /**
     * Metodo che farà partire l'intro di gioco.
     */
    public void start() {
        gameEngine.welcomePlayer();
    }

    /**
     * Metodo che restituisce il nome della stanza corrente del gioco.
     *
     * @return Stringa contenente il nome della stanza corrente.
     */
    public String getCurrentGameRoom() {
        return gameEngine.getCurrentRoom().getName();
    }

    /**
     * Metodo che notifica la GUI che il player si è spostato di stanza.
     */
    public void notifyRoomUpdateToGUI() {
        GameGUI.setRoomImage(getCurrentGameRoom());
    }

    /**
     * Metodo che notifica la GUI che va aggiornata la textbox con l'inventario del player.
     */
    public void notifyInventoryUpdateToGUI() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Inventario\n\n\n");

        for (Item item : gameEngine.getInventory()) {
            stringBuilder.append(item.getName());
            stringBuilder.append("   ");
        }

        HandlerGUI.getGameGUI().updateInventory(stringBuilder.toString());
    }

    /**
     * Metodo che restituisce tutti i colori che il player ha sbloccato. Serve alla GUI al caricamento della partita.
     *
     * @return Lista dei colori che il giocatore ha sbloccato.
     */
    public ArrayList<ColorEnum> getUnlockedColors() {
        ArrayList<ColorEnum> colors = new ArrayList<>();

        for (ColorClass color : gameEngine.getColors()) {
            if (color.isUnlocked()) {
                colors.add(getColorEnumFromColorClass(color));
            }
        }

        return colors;
    }

    /**
     * Comunica alla GUI che il player ha sbloccato il colore passato come parametro.
     *
     * @param toUnlock Colore da sbloccare.
     */
    public void unlockColor(final ColorClass toUnlock) {
        HandlerGUI.getGameGUI().unlockColor(getColorEnumFromColorClass(toUnlock));
    }

    /**
     * Metodo di utility che dato una ColorClass ne restituisce l'equivalente ColorEnum.
     *
     * @param color Oggetto ColorClass da convertire in ColorEnum.
     * @return ColorEnum relativo all'oggetto passato come parametro.
     */
    public ColorEnum getColorEnumFromColorClass(final ColorClass color) {
        return switch (color.getName().toLowerCase()) {
            case "rosso" -> ColorEnum.RED;
            case "blu" -> ColorEnum.BLUE;
            case "giallo" -> ColorEnum.YELLOW;
            case "verde" -> ColorEnum.GREEN;
            case "marrone" -> ColorEnum.BROWN;
            case "viola" -> ColorEnum.PURPLE;
            default -> ColorEnum.NEUTRAL;
        };
    }

    /**
     * Restituisce il tempo di gioco formattato.
     *
     * @return Stringa contenente il tempo di gioco formattato.
     */
    public String getTime() {
        return gameEngine.getGameTimer().getTimeFormatted();
    }

    /**
     * Fa ripartire il tempo di gioco, passando il tempo passato dal salvataggio.
     *
     * @param elapsedTime Tempo trascorso dal timer prima di fermarsi.
     */
    public void restartTimer(long elapsedTime) {
        gameEngine.getGameTimer().startAgain(elapsedTime);
    }

    /**
     * Restituisce il tempo trascorso dal timer.
     *
     * @return Tempo trascorso dal timer prima di fermarsi.
     */
    public long getElapsedTime() {
        return gameEngine.getGameTimer().getElapsedTime();
    }

    /**
     * Questo metodo serve per formattare la stringa passata come parametro in modo che,
     * se dovesse venire stampata sulla GUI, non darebbe problemi di formattazione al panel
     * (va a capo quando il testo è troppo lungo, ad esempio).
     *
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

    /**
     * Metodo che notifica la GUI che il gioco è terminato. Predispone le azioni di fine gioco.
     */
    public void notifyGameOverToGUI() {
        HandlerGUI.getGameGUI().setFinished(true);
    }
}
