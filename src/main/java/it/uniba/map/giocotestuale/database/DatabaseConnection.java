package it.uniba.map.giocotestuale.database;

import java.sql.*;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
public class DatabaseConnection {
	private static Connection conn = null;
	
	static
    {
		ApplicationProperties appProps = ApplicationProperties.getInstance();
        
        try {
			conn = DriverManager.getConnection(appProps.getUrlDatabase(),appProps.getUser(),appProps.getPassword());
			System.out.println("Connessione aperta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void releaseConnection()
    {
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
