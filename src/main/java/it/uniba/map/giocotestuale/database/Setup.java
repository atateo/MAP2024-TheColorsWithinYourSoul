package it.uniba.map.giocotestuale.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Setup {

	public static void costruisciDatabase() {
		List<String> istruzioni = new LinkedList();
		//creazione tabella contenuto
		String creaContenuto = "CREATE TABLE IF NOT EXISTS contenuto (" +
				"id INT AUTO_INCREMENT PRIMARY KEY, " +
				"label VARCHAR(200), " +
				"messaggio VARCHAR(4000), " +
				"isRisposta BOOLEAN, "+
				"idContenuto INT)";
		
		istruzioni.add(creaContenuto);
		
		String creaFKContenutoVsContenuto="ALTER TABLE contenuto"
				+ "    ADD FOREIGN KEY (idContenuto) REFERENCES contenuto(id)";
		
		istruzioni.add(creaFKContenutoVsContenuto);
		for (String istruzione : istruzioni) {
			boolean eseguito = eseguiIstruzione(istruzione);
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
