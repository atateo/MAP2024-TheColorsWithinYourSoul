package it.uniba.map.giocotestuale.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import it.uniba.map.giocotestuale.database.Score;
import it.uniba.map.giocotestuale.database.ScoreDaoImpl;

public class GameServer {
	private ServerSocket serverSocket;

	public GameServer (int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Errore durante la creazione del server: " + e.getMessage());
		}
		System.out.println("Server creato");
	}

	public void start() throws IOException {
		while (true) {
			try (Socket clientSocket = serverSocket.accept();
					ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

				String input = (String) in.readObject();
				switch (input) {
					case "POST" -> {
						ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl();
						Score score = (Score) in.readObject();
						try {
							scoreDaoImpl.add(score);
							out.writeObject("Operazione di inserimento eseguita correttamente.");
						}
						catch (SQLException e) {
							out.writeObject("Operazione di inserimento fallita");
							e.printStackTrace();
						}
					}
					case "GET" -> {
						ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl();
						try {
							out.writeObject(scoreDaoImpl.getScores());
						}
						catch (SQLException e) {
							out.writeObject("Eccezione in fase di recupero della classifica: "+e);
						}
					}
					case "END" -> {
						out.writeObject("Disconnessione in corso...");
						stop();
						break;
					}
					default -> {
						out.writeObject("Operazione non valida");
					}
				}
			}
			catch (IOException | ClassNotFoundException e) {
			}
		}
	}

	public void stop() throws IOException {
		serverSocket.close();
	}
	
	public static void main(String[] args) {
		GameServer server=new GameServer(3999);
		try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
