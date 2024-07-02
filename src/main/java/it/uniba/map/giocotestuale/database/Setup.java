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
        
        // Creazione della tabella room
        String creaRoom = "CREATE TABLE IF NOT EXISTS room (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "stato VARCHAR(20), " +
                "descrizione VARCHAR(150), " +
                "id_room INT)";
     // Creazione della tabella item
        String creaItem = "CREATE TABLE IF NOT EXISTS item (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "stato VARCHAR(20), " +
                "descrizione VARCHAR(75), " +
                "id_item INT)";
        
     // Creazione della tabella color
        String creaColor = "CREATE TABLE IF NOT EXISTS color (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "descrizione VARCHAR(75))";
        
     // Creazione della tabella dialog
        String creaDialog = "CREATE TABLE IF NOT EXISTS dialog (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "testo VARCHAR(500))";
                
     // Creazione della tabella score
        String creaScore = "CREATE TABLE IF NOT EXISTS score (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "player VARCHAR(100), " +
                "score INT, " +
                "time VARCHAR(10))";
        
        istruzioni.add(creaRoom);
        istruzioni.add(creaItem);
        istruzioni.add(creaColor);
        istruzioni.add(creaDialog);
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
            logger.error("Eccezione in fase di esecuzione dell'istruzione: {} - ",istruzione, e);
        }
        return successo;
    }
}
