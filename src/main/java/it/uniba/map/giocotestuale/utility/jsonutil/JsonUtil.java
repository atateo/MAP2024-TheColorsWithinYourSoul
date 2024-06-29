package it.uniba.map.giocotestuale.utility.jsonutil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import it.uniba.map.giocotestuale.config.ApplicationProperties;

/**
 * La classe JsonUtil fornisce metodi di utilità per la serializzazione e deserializzazione di oggetti in formato JSON.
 */
public class JsonUtil {
    static ApplicationProperties appProps = ApplicationProperties.getInstance();

    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();

    /**
     * Scrive un oggetto in formato JSON su un file specificato.
     *
     * @param filePath il percorso del file in cui scrivere il JSON
     * @param obj l'oggetto da serializzare
     */
    public static void writeJsonToFile(String filePath, Object obj) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);

        // Scrive la stringa JSON su file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
            logger.info("Serializzazione su file avvenuta con successo!");
        } catch (IOException e) {
            logger.info("Eccezione in fase di scrittura del file Json: {}", e);
        }
    }

    /**
     * Legge un oggetto in formato JSON da un file specificato.
     *
     * @param filePath il percorso del file da cui leggere il JSON
     * @param obj      l'oggetto in cui deserializzare i dati
     */
    public static void readJsonFromToFile(String filePath, Object obj) {
        Gson gson = new Gson();

        // Legge il file JSON e deserializza l'oggetto
        try (FileReader fileReader = new FileReader(filePath)) {
            obj = gson.fromJson(fileReader, Object.class);
            logger.info("Deserializzazione oggetto avvenuta con successo!");
        } catch (IOException e) {
            logger.info("Eccezione in fase di deserializzazione: {}", e);
        }
    }
}
