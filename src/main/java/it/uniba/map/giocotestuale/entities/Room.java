package it.uniba.map.giocotestuale.entities;

public class Room {
    private int id;
    private String name;

    private RoomConnection north;
    private RoomConnection south;
    private RoomConnection west;
    private RoomConnection east;

    public Room(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
