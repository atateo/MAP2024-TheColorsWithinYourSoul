package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.CommandClass;
import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.type.ParserOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

public class Parser {
    private final Set<CommandClass> availableCommands;
    private final ArrayList<GameObject> availableObjects;
    private final Set<String> stopwords = new HashSet<>();

    public Parser(final GameEngine game) {
        availableCommands = game.getAllCommands();
        availableObjects = game.getAllGameItems();

        try {
            setupUselessWords();
        } catch (Exception e) {
            System.out.println("C'è stato un problema nell'apertura del file delle stopwords.\n");
        }
    }

    public ParserOutput parse(String input) {
        ParserOutput output = new ParserOutput();
        String[] tokens;

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
                if(output.getFirstObject() == null) {
                    return null;
                }

                if (tokens.length > 2) {
                    //In questo ramo ci vanno i comandi che prendono due parametri
                    output.setSecondObject(checkForObjects(tokens[2]));

                    //Non ha riconosciuto il secondo parametro del comando
                    if(output.getFirstObject() == null) {
                        return null;
                    }
                } else {
                    //Il comando ha troppi parametri, restituisci null
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

    private Command checkForCommands(final String token) {
        for (CommandClass availableCommand : availableCommands) {
            if (availableCommand.getCommandName().equalsIgnoreCase(token) || availableCommand.getCommandAliases().contains(token.toLowerCase())) {
                return availableCommand.getCommandType();
            }
        }

        return null;
    }

    private GameObject checkForObjects(String token) {
        for (GameObject availableObject : availableObjects) {
            if (availableObject.getName().equalsIgnoreCase(token) || availableObject.getAliases().contains(token.toLowerCase())) {
                return availableObject;
            }
        }

        return null;
    }

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