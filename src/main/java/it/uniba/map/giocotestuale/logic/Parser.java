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
        CommandClass command;

        tokens = Arrays.stream(input.split(" "))
                .map(String::toLowerCase)
                .filter(w -> !stopwords.contains(w))
                .toArray(String[]::new);

        if (tokens.length == 0) {
            return null;
        } else {
            Iterator<CommandClass> commandIterator = availableCommands.iterator();

            while (output.getCommandType() == null && commandIterator.hasNext()) {
                command = commandIterator.next();
                if (command.getCommandName().equalsIgnoreCase(tokens[0])) {
                    output.setCommandType(command.getCommandType());
                } else {

                    String foundAlias = command.getCommandAliases().stream()
                            .filter(alias -> alias.equalsIgnoreCase(tokens[0]))
                            .findFirst()
                            .orElse(null);

                    if (foundAlias != null) {
                        output.setCommandType(command.getCommandType());
                    }
                }
            }

            if (output.getCommandType() == null) {
                return null;
            }
        }
        return new ParserOutput(Command.COLORA, null, null);
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