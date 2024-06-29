package it.uniba.map.giocotestuale.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe responsabile della creazione e configurazione del database.
 */
public class Setup {
	 /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    
    /**
     * Metodo per creare le tabelle necessarie nel database.
     * Viene utilizzata una lista di istruzioni SQL per creare le tabelle 
     * e caricare eventuali dati di configurazione
     */
    public static void costruisciDatabase() {
        List<String> istruzioni = new ArrayList<>();
        
        // Creazione della tabella contenuto
        String creaContenuto = "CREATE TABLE IF NOT EXISTS contenuto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "label VARCHAR(200), " +
                "messaggio VARCHAR(4000), " +
                "isRisposta BOOLEAN, " +
                "idItem INT)";
        
     // Creazione della tabella score
        String creaScore = "CREATE TABLE IF NOT EXISTS score (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "player VARCHAR(100), " +
                "score INT, " +
                "time VARCHAR(10))";
        
        istruzioni.add(creaContenuto);
        istruzioni.add(creaScore);
        
        for (String istruzione : istruzioni) {
            boolean eseguito = eseguiIstruzione(istruzione);
            logger.info(eseguito?"Istruzione :{} eseguita con successo.":"Errore di esecuzione dell'istruzione: {}", istruzione);
        }
    }

    /**
     * Esegue l'istruzione SQL passata come parametro, sul database.
     * 
     * @param istruzione la stringa dell'istruzione SQL da eseguire.
     * @return true se l'istruzione è stata eseguita con successo, false in caso di errore.
     */
    private static boolean eseguiIstruzione(String istruzione) {
        boolean successo = false;
        Connection connection = DatabaseConnection.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(istruzione);
            successo = true;
        } catch (SQLException e) {
            logger.error("Eccezione in fase di esecuzione dell'istruzione: {} - {}",istruzione, e);
            e.printStackTrace();
        }
        return successo;
    }
}
