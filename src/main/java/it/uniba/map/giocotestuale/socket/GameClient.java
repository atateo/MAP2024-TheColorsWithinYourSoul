package it.uniba.map.giocotestuale.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.uniba.map.giocotestuale.database.Score;

public class GameClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void sendScore(Score score) {
        try {
        	out.writeObject("POST");
			out.writeObject(score);
			String resp = (String) in.readObject();
			System.out.println("Risposta del server: "+resp);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();        
		}
    }
    
    public List<Score> getScores() {
    	List<Score> punteggi = new ArrayList<Score>();
    	
        try {
        	out.writeObject("GET");
        	
        	List<?>response = (List<?>) in.readObject();
			for (Iterator<?> iterator = response.iterator(); iterator.hasNext();) {
				Score score = (Score) iterator.next();
				punteggi.add(score);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();        
		}
        punteggi.sort((a, b) -> fromatTimeFromStringToInt(a.getTime()) - fromatTimeFromStringToInt(b.getTime()));
        return punteggi;
    }

    public void end() throws IOException {
    	try {
        	out.writeObject("END");
        	String resp = (String) in.readObject();
			System.out.println("Risposta del server: "+resp);
    	}
    	catch (IOException | ClassNotFoundException e) {
			
		}
        
    }
    public void stopConnection() {
        try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private static int fromatTimeFromStringToInt(String time) {
    	String[] tokenized = time.split(":");
    	int hh = Integer.parseInt(tokenized[0]);
    	int mm = Integer.parseInt(tokenized[1]);
    	int ss = Integer.parseInt(tokenized[2]);
    	int timeInSecond = (hh * 3600) + (mm * 60) + ss;
    	return timeInSecond;
    }
}
