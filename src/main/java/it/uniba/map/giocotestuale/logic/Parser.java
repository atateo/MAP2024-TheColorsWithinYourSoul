package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.game.CommandClass;
import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.type.ParserOutput;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

/**
 * Classe che gestisce il parsing delle stringhe di input dell'utente per convertile in comandi su oggetti.
 */
public class Parser {
    /**
     * Oggetto Logger che gestisce i log dell'applicazione.
     */
    protected static final Logger logger = LogManager.getLogger();
    /**
     * Set di tutti i comandi disponibili nel gioco.
     */
    private final Set<CommandClass> availableCommands;
    /**
     * Lista di tutti gli oggetti disponibili nel gioco.
     */
    private final ArrayList<GameObject> availableObjects;
    /**
     * Set di tutte le parole non ritenute utili ai fini del parser.
     */
    private final Set<String> stopwords = new HashSet<>();

    /**
     * Costruttore con parametro della classe Parser. Istanzia il Parser sulla base dell'istanza
     * di GameEngine passata come parametro e tenta di leggere il file delle stopwords.
     *
     * @param game Istanza di gioco da cui saranno ricavate le informazioni su item e comandi.
     */
    public Parser(final GameEngine game) {
        availableCommands = game.getAllCommands();
        availableObjects = game.getAllObjects();

        try {
            setupUselessWords();
        } catch (Exception e) {
            logger.info("C'è stato un problema nell'apertura del file delle stopwords.\n");
        }
    }

    /**
     * Costruisce un oggetto di tipo ParseOutput sulla base della stringa fornita in input dall'utente.
     * L'oggetto ParseOutput costruito sarà usato per eseguire il comando.
     *
     * @param input Stringa di input dell'utente.
     * @return Oggetto ParseOutput corrispondente alla stringa. Il Parser non ha riconosciuto il comando.
     */
    public ParserOutput parse(String input) {
        ParserOutput output = new ParserOutput();
        String[] tokens;

        //Lambda function che divide la stringa di input in token filtrando le stopwords
        //e convertendo tutto in minuscolo, salvando poi i token nell'array di stringhe tokens
        tokens = Arrays.stream(input.split("\\s+"))
                .map(String::toLowerCase)
                .filter(w -> !stopwords.contains(w))
                .toArray(String[]::new);

        //Ha ricevuto una stringa vuota
        if (tokens.length == 0) {
            return null;
        } else {
            output.setCommandType(checkForCommands(tokens[0]));

            //Non ha riconosciuto il comando
            if (output.getCommandType() == null) {
                return null;
            }

            if (tokens.length > 1) {
                //In questo ramo ci vanno i comandi che prendono uno o due parametri
                output.setFirstObject(checkForObjects(tokens[1]));

                //Non ha riconosciuto il primo parametro del comando
                if (output.getFirstObject() == null) {
                    return null;
                }

                if (tokens.length == 2) {
                    output.setSecondObject(null);
                    return output;
                }

                if (tokens.length == 3) {
                    //In questo ramo ci vanno i comandi che prendono due parametri
                    output.setSecondObject(checkForObjects(tokens[2]));

                    //Non ha riconosciuto il secondo parametro del comando
                    if (output.getFirstObject() == null) {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                //In questo ramo ci vanno i comandi che non prendono parametri
                output.setFirstObject(null);
                output.setSecondObject(null);
                return output;
            }
        }

        return output;
    }

    /**
     * Verifica se il token passato è un comando tra quelli disponibili nel parser.
     *
     * @param token Token da analizzare.
     * @return Tipo di comando corrispondente. Se non riconosce il comando, restituisce null.
     */
    private Command checkForCommands(final String token) {
        for (CommandClass availableCommand : availableCommands) {
            if (availableCommand.getCommandName().equalsIgnoreCase(token) || availableCommand.getCommandAliases().contains(token.toLowerCase())) {
                return availableCommand.getCommandType();
            }
        }

        return null;
    }

    /**
     * Verifica se il token passato è un item tra quelli disponibili nel parser.
     *
     * @param token Token da analizzare.
     * @return Item corrispondente. Se non riconosce il comando, restituisce null.
     */
    private GameObject checkForObjects(String token) {
        for (GameObject availableObject : availableObjects) {
            if (availableObject.getName().equalsIgnoreCase(token) || availableObject.getAliases().contains(token.toLowerCase())) {
                return availableObject;
            }
        }

        return null;
    }

    /**
     * Tenta di leggere il file stopwords.txt per ricavare le parole da inserire nel set stopwords.
     *
     * @throws Exception Eccezioni dovute alla lettura del file.
     */
    private void setupUselessWords() throws Exception {
        Files.readAllBytes(Paths.get("src/main/resources/static/stopwords.txt"));
        File stopWordFile = new File("src/main/resources/static/stopwords.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(stopWordFile));

        while (fileReader.ready()) {
            stopwords.add(fileReader.readLine().trim().toLowerCase());
        }

        fileReader.close();
    }
}