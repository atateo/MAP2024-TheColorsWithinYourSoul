package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.CommandClass;
import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.entities.Item;
import it.uniba.map.giocotestuale.entities.Room;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.type.ParserOutput;
import it.uniba.map.giocotestuale.utility.Timer;

import java.util.ArrayList;
import java.util.Set;

public abstract class GameEngine {
    private ArrayList<Room> rooms;
    private ArrayList<Item> inventory;
    private ArrayList<Interaction> gameInteractions;
    private final Timer gameTimer;

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

    public void addItemToInventory(Item item) {
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

    public ArrayList<GameObject> getAllGameItems() {
        ArrayList<GameObject> gameItems = new ArrayList<>();
        for (Room room : rooms) {
            gameItems.addAll(room.getItemsInRoom());
        }
        return gameItems;
    }

    public abstract void welcomePlayer();
    public abstract void help();
    public abstract Set<CommandClass> getAllCommands();
    public abstract void defineGameInteractions();
    public abstract void update(ParserOutput output);
    public abstract void invalidCommandOutput();
    public abstract boolean checkIfGameIsOver();
    public abstract void goodbyePlayer();
}