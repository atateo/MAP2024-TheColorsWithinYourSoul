package it.uniba.map.giocotestuale.database;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.uniba.map.giocotestuale.config.ApplicationProperties;

/**
 * Gestisce la connessione al database utilizzando le proprietà dell'applicazione.
 * La connessione viene aperta una volta e riutilizzata fino a quando non viene rilasciata.
 */
public class DatabaseConnection {
    
    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();

    /**
     * Oggetto Connection utilizzato per connettersi al database.
     */
    private static Connection conn = null;

    // Blocco statico per inizializzare la connessione al database all'avvio della classe.
    static {
        ApplicationProperties appProps = ApplicationProperties.getInstance();

        try {
            conn = DriverManager.getConnection(appProps.getUrlDatabase(), appProps.getUser(), appProps.getPassword());
            logger.info("Connessione aperta");
        } catch (SQLException e) {
            logger.error("Eccezione in fase di apertura della connessione al database: ", e);
        }
    }

    /**
     * Restituisce la connessione al database.
     * 
     * @return l'oggetto Connection corrente.
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * Rilascia la connessione al database chiudendola.
     */
    public static void releaseConnection() {
        try {
            conn.close();
            logger.info("Connessione al database rilasciata");
        } catch (SQLException e) {
            logger.error("Eccezione in fase di chiusura della connessione al database: ", e);
        }
    }
}
