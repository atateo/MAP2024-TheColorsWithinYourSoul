package it.uniba.map.giocotestuale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.Setup;

public class GiocoTestuale {
	
	protected static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		
		ApplicationProperties properties = ApplicationProperties.getInstance();
		logger.info("Proprietà recuperate correttamente: versione dell'app = {}",properties.getVersion());
		
		if(properties.isSetup())
		{
			logger.info("Costruisco il database");
			Setup.costruisciDatabase();
		}

		logger.debug("Connessione rilasciata");
		DatabaseConnection.releaseConnection();
	}
}
