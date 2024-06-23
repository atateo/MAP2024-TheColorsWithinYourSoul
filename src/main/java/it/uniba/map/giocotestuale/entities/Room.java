package it.uniba.map.giocotestuale.entities;

import java.util.ArrayList;
import java.util.List;

public class Room extends GameObject {

    private RoomConnection north;
    private RoomConnection south;
    private RoomConnection west;
    private RoomConnection east;

    private List<Item> itemsInRoom;

    public Room(final int id, final String name, final String state) {
        super(id, name, null, state);
        itemsInRoom = new ArrayList<>();
    }

    public void setNorthRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.north = new RoomConnection(reachableRoom, isLocked);
    }

    public void setSouthRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.south = new RoomConnection(reachableRoom, isLocked);
    }

    public void setWestRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.west = new RoomConnection(reachableRoom, isLocked);
    }

    public void setEastRoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.east = new RoomConnection(reachableRoom, isLocked);
    }

    public void setItemsInRoom(final List<Item> itemsInRoom) {
        this.itemsInRoom = itemsInRoom;
    }

    public RoomConnection getNorthRoomConnection() {
        return this.north;
    }

    public RoomConnection getSouthRoomConnection() {
        return this.south;
    }

    public RoomConnection getWestRoomConnection() {
        return this.west;
    }

    public RoomConnection getEastRoomConnection() {
        return this.east;
    }

    public List<Item> getItemsInRoom() {
        return this.itemsInRoom;
    }

    public void addItem(final Item item) {
        this.itemsInRoom.add(item);
    }

    @Override
    public String getDescriptionFromDB() {
        //Placeholder, sarà implementato insieme al DB
        return null;
    }

    @Override
    public void updateStatus(final String newStatus) {
        super.setStatus(newStatus);

        //Scrivere qui l'implementazione dei vari status
    }
}
