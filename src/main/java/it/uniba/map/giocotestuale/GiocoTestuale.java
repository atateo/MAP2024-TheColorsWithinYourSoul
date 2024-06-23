package it.uniba.map.giocotestuale;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import it.uniba.map.giocotestuale.entities.*;
//import it.uniba.map.giocotestuale.logic.*;
//import it.uniba.map.giocotestuale.type.*;
import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.dao.ContenutoDaoImpl;
import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.Setup;
import it.uniba.map.giocotestuale.gui.MenuGUI;
import it.uniba.map.giocotestuale.model.Contenuto;

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
