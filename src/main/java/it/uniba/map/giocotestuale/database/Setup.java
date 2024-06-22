package it.uniba.map.giocotestuale.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Setup {

	public static void costruisciDatabase() {
		List<String> istruzioni = new ArrayList<String>();
		//creazione tabella contenuto
		String creaContenuto = "CREATE TABLE IF NOT EXISTS contenuto (" +
				"id INT AUTO_INCREMENT PRIMARY KEY, " +
				"label VARCHAR(200), " +
				"messaggio VARCHAR(4000), " +
				"isRisposta BOOLEAN, "+
				"idItem INT)";
		
		istruzioni.add(creaContenuto);
		
		for (String istruzione : istruzioni) {
			boolean eseguito = eseguiIstruzione(istruzione);
			System.out.println(eseguito?"Operazione eseguita con successo.":"Errore di esecuzione");
		}
	}
	private static boolean eseguiIstruzione(String istruzione) {
		boolean successo = false;
		Connection connection = DatabaseConnection.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.execute(istruzione);
			System.out.println("Istruzione eseguita con successo!");
			successo = true;
		} catch (SQLException e) {
			System.out.println("Eccezione in fase di esecuzione dell'istruzione: "+istruzione);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successo;
	}
}
