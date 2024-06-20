package it.uniba.map.giocotestuale;

import java.sql.SQLException;
import java.util.List;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.dao.ContenutoDaoImpl;
import it.uniba.map.giocotestuale.database.Setup;
import it.uniba.map.giocotestuale.model.Contenuto;

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
		//test CRUD
		Contenuto contenuto = new Contenuto();
		contenuto.setLabel("PROVA");
		contenuto.setMessaggio("messaggio di prova");
		contenuto.setRisposta(false);
		
		ContenutoDaoImpl contImpl = new ContenutoDaoImpl();
		try {
			contImpl.add(contenuto);
			Contenuto contenuto1 = contImpl.getContenuto(1);
			System.out.println("contenuto1: "+contenuto1.toString());
			
			List<Contenuto> lista = contImpl.getContenuti();
			for (Contenuto contenuto2 : lista) {
				System.out.println("contenuto2: "+contenuto2.toString());
			}
			Contenuto contUp = contImpl.getContenuto(1);
			contUp.setMessaggio("prova di aggiornamento tramite DAO");
			contImpl.update(contUp);
			
			contImpl.delete(1);
			
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
