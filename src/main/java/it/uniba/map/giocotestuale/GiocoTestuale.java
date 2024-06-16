package it.uniba.map.giocotestuale;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.database.Setup;

public class GiocoTestuale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationProperties properties = ApplicationProperties.getInstance();
		System.out.println(properties.getVersion());
		//Connection conn = DatabaseConnection.getConnection();
		if(properties.isSetup())
		{
			Setup.costruisciDatabase();
		}
	}

}
