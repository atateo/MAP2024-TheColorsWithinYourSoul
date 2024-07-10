package it.uniba.map.giocotestuale;

import it.uniba.map.giocotestuale.gui.*;
import it.uniba.map.giocotestuale.impl.GameToGUICommunication;
import it.uniba.map.giocotestuale.socket.GameServer;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.uniba.map.giocotestuale.database.Setup;
import it.uniba.map.giocotestuale.config.ApplicationProperties;

/**
 * Classe contente il main della nostra applicazione. Fa partire il gioco.
 *
 * @author Yuri Tateo
 * @author Antimo Tateo
 * @author Angelo Vincenti
 */
public class GiocoTestuale {

    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();

    /**
     * Costruttore di default di GiocoTestuale.
     */
    public GiocoTestuale() {}

    /**
     * Main dell'applicazione. Il metodo che verrà eseguito e darà inizio al gioco.
     * @param args Argomenti.
     */
    public static void main(String[] args) {

        ApplicationProperties properties = ApplicationProperties.getInstance();
        logger.info("Proprietà recuperate correttamente: versione dell'app = {}", properties.getVersion());

        if (properties.isSetup()) {
            logger.info("Costruisco il database");
            Setup.setup();
        }

        HandlerGUI handlerGUI = new HandlerGUI();
        handlerGUI.setVisible(true);
        GameToGUICommunication.getInstance().setHandlerGUI(handlerGUI);

        GameServer server = new GameServer(3999);
        try {
            server.start();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Errore durante la creazione del server: ", e);
        }
    }
}
