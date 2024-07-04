package it.uniba.map.giocotestuale;

import it.uniba.map.giocotestuale.gui.*;
import it.uniba.map.giocotestuale.impl.ColorsWithinYourSoulGame;
import it.uniba.map.giocotestuale.impl.GameToGUICommunication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.Setup;
import it.uniba.map.giocotestuale.config.ApplicationProperties;

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

		HandlerGUI handlerGUI = new HandlerGUI();
		handlerGUI.setVisible(true);
		GameToGUICommunication.getInstance().setHandlerGUI(handlerGUI);
	}
}
