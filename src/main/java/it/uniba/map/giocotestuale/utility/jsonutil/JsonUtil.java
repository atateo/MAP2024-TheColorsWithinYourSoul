package it.uniba.map.giocotestuale.utility.jsonutil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import it.uniba.map.giocotestuale.impl.ColorsWithinYourSoulGame;

/**
 * La classe JsonUtil fornisce metodi di utilità per la serializzazione e deserializzazione di oggetti in formato JSON.
 */
public class JsonUtil {

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
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write(jsonString);
            logger.info("Serializzazione su file avvenuta con successo!");
        } catch (IOException e) {
            logger.info("Eccezione in fase di scrittura del file Json: ", e);
        }
    }

    /**
     * Legge un oggetto in formato JSON da un file specificato.
     *
     * @param filePath il percorso del file da cui leggere il JSON
  	 * @return restituisce l'oggetto deserializzato
     */
    public static ColorsWithinYourSoulGame readJsonFromFile(String filePath) {
        Gson gson = new Gson();
        ColorsWithinYourSoulGame game = null;
        // Legge il file JSON e deserializza l'oggetto
        try (FileReader fileReader = new FileReader(filePath)) {
            game = gson.fromJson(fileReader, ColorsWithinYourSoulGame.class);
            logger.info("Deserializzazione oggetto avvenuta con successo!");
        } catch (IOException e) {
            logger.info("Eccezione in fase di deserializzazione: ", e);
        }
        return game;
    }
    
    /**
     * Legge un oggetto in formato JSON da un file specificato.
     *
     * @param jsonString il percorso del file da cui leggere il JSON
     * @return obj l'oggetto in cui deserializzare i dati
     */
    public static Object getObjectFromJsonString(String jsonString, Object obj) {
        Gson gson = new Gson();

        obj = gson.fromJson(jsonString, Object.class);
        logger.info("Deserializzazione oggetto avvenuta con successo!");
        return obj;
    }
    
    /**
     * Restituisce una stringa in formato JSON a partire da un oggetto.
     *
     * @param obj l'oggetto da cui ricavare la stringa JSON
     * @return jsonString la stringa JSON derivata dall'oggetto
     */
    public static String getJsonStringFromObject(Object obj) {
        Gson gson = new Gson();

        String jsonString = gson.toJson(obj);
        logger.info("Deserializzazione oggetto avvenuta con successo!");
        return jsonString;
    }
}
