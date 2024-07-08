package it.uniba.map.giocotestuale.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.uniba.map.giocotestuale.config.ApplicationProperties;

/**
 * Classe responsabile della creazione e configurazione del database.
 */
public class Setup {
	/**
	 * Logger per la registrazione degli eventi.
	 */
	protected static final Logger logger = LogManager.getLogger();

	/**
	 * getIstance per il recupero delle properties.
	 */
	static ApplicationProperties appProps = ApplicationProperties.getInstance();

	/**
	 * Metodo per creare le tabelle necessarie nel database. Viene utilizzata una
	 * lista di istruzioni SQL per creare le tabelle e caricare eventuali dati di
	 * configurazione
	 */
	public static void setup() {

		String setup = "RUNSCRIPT FROM '" + appProps.getPathSetupDb() + "'";

		boolean eseguito = eseguiIstruzione(setup);
		logger.info(eseguito ? "Istruzione :{} eseguita con successo." : "Errore di esecuzione dell'istruzione: {}",
				setup);

		if (!eseguito) {
			// provo a creare il database dalla classe mock
			List<String> istruzioni = MockDatabase.getIstruzioni();

			for (String istruzione : istruzioni) {
				boolean eseguitoMock = eseguiIstruzione(istruzione);
				logger.info(
						eseguitoMock ? "Istruzione :{} eseguita con successo." : "Errore di esecuzione dell'istruzione: {}",
						istruzione);
			}
		}
		try {
			// provo a creare la directory in cui salvare i file di salvataggio
			getDir();
		} catch (IOException e) {
			logger.error("Cartella saves non creata, i file saranno salvati nella dir principale: ", e);
		}
	}

	/**
	 * Esegue l'istruzione SQL passata come parametro, sul database.
	 * 
	 * @param setup la stringa dell'istruzione SQL da eseguire.
	 * @return true se l'istruzione è stata eseguita con successo, false in caso di
	 *         errore.
	 */
	private static boolean eseguiIstruzione(String setup) {
		boolean successo = false;
		Connection connection = DatabaseConnection.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.execute(setup);
			successo = true;
		} catch (SQLException e) {
			logger.error("Eccezione in fase di esecuzione dell'istruzione: {} - ", setup, e);
			successo = false;
		}
		return successo;
	}

	/**
	 * metodo che crea la directory "saves" dei salvataggi.
	 * 
	 */
	private static void getDir() throws IOException {
		File saves = new File("saves");
		if (!saves.exists()) {
			boolean md = saves.mkdir();
			if (!md)
				throw new IOException();
		}
	}
}
