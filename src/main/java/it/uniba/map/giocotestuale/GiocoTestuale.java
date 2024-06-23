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
		
		MenuGUI menu = new MenuGUI();
		
//		//test CRUD
//		Contenuto contenuto = new Contenuto();
//		contenuto.setLabel("PROVA");
//		contenuto.setMessaggio("messaggio di prova");
//		contenuto.setRisposta(false);
//		contenuto.setIdItem(12);
//		
//		ContenutoDaoImpl contImpl = new ContenutoDaoImpl();
//		try {
//			contImpl.add(contenuto);
//			Contenuto contenuto1 = contImpl.getContenuto(1);
//			System.out.println(contenuto1!=null?"contenuto1: "+contenuto1.toString():"Contenuto è nullo");
//			
//			List<Contenuto> lista = contImpl.getContenuti();
//			for (Contenuto contenuto2 : lista) {
//				System.out.println("contenuto2: "+contenuto2.toString());
//			}
//			Contenuto contUp = contImpl.getContenuto(1);
//			if(contUp!=null) {
//				contUp.setMessaggio("prova di aggiornamento tramite DAO");
//				contImpl.update(contUp);
//				
//				contImpl.delete(1);
//			}
//			
//			
//			
//			
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}

//		Item itemTest = new Item(0, "Test1", null, "0");
//		Room room = new Room(0, "Test1", "0");
//
//		System.out.println(itemTest.getStatus());
//		System.out.println(room.getStatus());
//
//		SingleObjectInteraction testSingleInteraction = new SingleObjectInteraction(
//				itemTest, Command.USA, "0", "1",
//				(targetObject, resultObject, targetState, resultState) -> {
//					if(targetObject.getStatus().equals(targetState))
//						targetObject.setStatus(resultState);
//		});
//
//		TwoObjectInteraction testTwoInteraction = new TwoObjectInteraction(
//			itemTest, room, Command.USA, "1", "1",
//			(targetObject, resultObject, targetState, resultState) -> {
//				if(targetObject.getStatus().equals(targetState))
//					targetObject.setStatus(resultState);
//			}
//		);
//
//		testSingleInteraction.executeInteraction();
//		testTwoInteraction.executeInteraction();
//
//		System.out.println(itemTest.getStatus());
//		System.out.println(room.getStatus());
		
		logger.debug("Connessione rilasciata");
		DatabaseConnection.releaseConnection();
	}
}
