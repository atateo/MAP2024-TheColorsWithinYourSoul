package it.uniba.map.giocotestuale.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.uniba.map.giocotestuale.database.domain.Score;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GameClient, classe client che gestisce la connessione con il server del gioco,
 * permettendo di inviare e ricevere punteggi.
 */
public class GameClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();

    /**
     * Avvia la connessione al server specificato.
     *
     * @param ip   l'indirizzo IP del server
     * @param port la porta del server
     * @throws UnknownHostException se l'indirizzo IP del server non è valido
     * @throws IOException          se si verifica un errore di I/O durante la connessione
     */
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    /**
     * Invia un oggetto Score (punteggio) al server.
     *
     * @param score il punteggio da inviare
     */
    public String sendScore(Score score) {
        String resp = null;
        try {
            out.writeObject("POST");
            out.writeObject(score);
            resp = (String) in.readObject();
            logger.info("Risposta del server: {}", resp);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Eccezione nel metodo sendScore (invio punteggio): ", e);
        }
        return resp;
    }

    /**
     * Richiede al server la lista dei punteggi, restituisce la lista ordinata per tempo di gioco crescente.
     *
     * @return una lista di punteggi
     */
    public List<Score> getScores() {
        List<Score> punteggi = new ArrayList<>();

        try {
            out.writeObject("GET");

            List<?> response = (List<?>) in.readObject();
            for (Object o : response) {
                Score score = (Score) o;
                punteggi.add(score);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Eccezione nel metodo getScores (recupero dei punteggi): ", e);
        }
        punteggi.sort(Comparator.comparingInt(a -> fromatTimeFromStringToInt(a.getTime())));
        return punteggi;
    }

    /**
     * Invia un comando al server per terminare la connessione.
     *
     * @throws IOException se si verifica un errore di I/O durante la disconnessione
     */
    public void end() throws IOException {
        try {
            out.writeObject("END");
            String resp = (String) in.readObject();
            System.out.println("Risposta del server: " + resp);
            stopConnection();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Eccezione nel metodo end (chiusura della comunicazione con il server): ", e);
        }
    }

    /**
     * Chiude la connessione con il server.
     */
    public void stopConnection() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            logger.error("Eccezione nel metodo stopConnection(): ", e);
        }
    }

    /**
     * Converte il tempo in formato stringa (hh:mm:ss) in secondi.
     *
     * @param time il tempo in formato stringa
     * @return il tempo in secondi
     */
    private static int fromatTimeFromStringToInt(String time) {
        String[] tokenized = time.split(":");
        int hh = Integer.parseInt(tokenized[0]);
        int mm = Integer.parseInt(tokenized[1]);
        int ss = Integer.parseInt(tokenized[2]);
        return (hh * 3600) + (mm * 60) + ss;
    }
}
