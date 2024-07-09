package it.uniba.map.giocotestuale.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import it.uniba.map.giocotestuale.database.domain.Score;
import it.uniba.map.giocotestuale.database.impl.ScoreDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * La classe GameServer gestisce le connessioni dei client per un gioco testuale,
 * permettendo di inserire e recuperare punteggi dal database.
 */
public class GameServer {
    private static ServerSocket serverSocket;
    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();

    /**
     * Costruttore per la creazione del server sulla porta specificata.
     *
     * @param port la porta su cui il server si mette in ascolto.
     */
    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("Errore durante la creazione del server: ", e);
        }
        logger.info("Server creato");
    }

    /**
     * Avvia il server e attende le connessioni dei client.
     *
     * @throws IOException eccezione sollevata se si dovesse verificare un errore di I/O durante la comunicazione client/server.
     */
    public void start() throws IOException, ClassNotFoundException {
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
                            int keyGenerated = scoreDaoImpl.add(score);
                            out.writeObject("Operazione di inserimento eseguita correttamente. KEY=" + keyGenerated);
                        } catch (SQLException e) {
                            logger.error("Operazione di inserimento fallita", e);
                            out.writeObject("Operazione di inserimento fallita");
                        }
                    }
                    case "GET" -> {
                        ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl();
                        try {
                            out.writeObject(scoreDaoImpl.getScores(10));
                        } catch (SQLException e) {
                            logger.error("Eccezione in fase di recupero della classifica: ", e);
                            out.writeObject("Eccezione in fase di recupero della classifica: " + e);
                        }
                    }
                    case "END" -> {
                        out.writeObject("Disconnessione in corso...");
                    }
                    default -> {
                        out.writeObject("Operazione non valida");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Eccezione in fase di comunicazione con il server dei punteggi", e);
                throw e;
            }
        }
    }

    /**
     * Arresta il server chiudendo il ServerSocket.
     *
     * @throws IOException eccezione sollevata se si dovesse verificare un errore di I/O durante la chiusura del ServerSocket.
     */
    public static void stop() throws IOException {
        logger.info("Server arrestato");
        serverSocket.close();
    }
}
