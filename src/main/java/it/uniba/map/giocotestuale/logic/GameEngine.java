package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.CommandClass;
import it.uniba.map.giocotestuale.entities.Item;
import it.uniba.map.giocotestuale.entities.Room;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.Command;
import it.uniba.map.giocotestuale.utility.Timer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public abstract class GameEngine {
    private ArrayList<Room> rooms;
    private ArrayList<Item> inventory;
    private ArrayList<Interaction> gameInteractions;
    private Timer gameTimer;

    private String playerName;
    private Room currentRoom;

    public GameEngine() {
        rooms = new ArrayList<>();
        inventory = new ArrayList<>();
        gameInteractions = new ArrayList<>();
        gameTimer = new Timer();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean setCurrentRoom(Room room) {
        if (room != null && rooms.contains(room)) {
            this.currentRoom = room;
            return true;
        } else {
            return false;
        }
    }

    public void setCurrentRoomToNull() {
        this.currentRoom = null;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public ArrayList<Interaction> getGameInteractions() {
        return this.gameInteractions;
    }

    public Timer getGameTimer() {
        return this.gameTimer;
    }

    public Set<CommandClass> getAllCommands() {
        Set<CommandClass> commands = new HashSet<>();

        commands.add(new CommandClass("Aiuto", Command.AIUTO, List.of("h", "help", "comandi", "comando", "guida")));
        commands.add(new CommandClass("Nord", Command.NORD, List.of("n", "north", "avanti", "vaiAvanti")));
        commands.add(new CommandClass("Sud", Command.SUD, List.of("s", "south", "indietro", "vaiIndietro")));
        commands.add(new CommandClass("Ovest", Command.OVEST, List.of("o", "west", "sinistra", "vaiSinistra", "vaiASinistra")));
        commands.add(new CommandClass("Est", Command.EST, List.of("e", "east", "destra", "vaiDestra", "vaiADestra")));
        commands.add(new CommandClass("Osserva", Command.OSSERVA, List.of("g", "l", "look", "vedi", "esamina", "osserva", "ammira", "ispeziona")));
        commands.add(new CommandClass("Inventario", Command.INVENTARIO, List.of("i", "inventory", "borsa", "zaino", "inv")));
        commands.add(new CommandClass("Prendi", Command.PRENDI, List.of("p", "t", "take", "raccogli", "recupera", "intasca")));
        commands.add(new CommandClass("Lascia", Command.LASCIA, List.of("drop", "abbandona", "lancia", "butta", "scarta", "rimuovi")));
        commands.add(new CommandClass("Usa", Command.USA, List.of("u", "use", "utilizza", "poggia", "appoggia", "poni")));
        commands.add(new CommandClass("Colora", Command.COLORA, List.of("pittura", "paint", "tinteggia")));

        return commands;
    }

    public abstract void welcomePlayer();
    public abstract void defineGameInteractions();
    public abstract void update();
    public abstract boolean checkIfGameIsOver();
    public abstract void goodbyePlayer();
}