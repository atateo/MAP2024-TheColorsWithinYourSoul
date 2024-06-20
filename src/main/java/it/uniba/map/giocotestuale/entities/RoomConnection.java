package it.uniba.map.giocotestuale.entities;

public class RoomConnection {
    private Room reachableRoom;
    private boolean isLocked;

    public RoomConnection(final Room reachableRoom, final boolean isLocked) {
        this.reachableRoom = reachableRoom;
        this.isLocked = isLocked;
    }

    public Room getReachableRoom() {
        return reachableRoom;
    }

    public void setReachableRoom(final Room reachableRoom) {
        this.reachableRoom = reachableRoom;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(final boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void unlock() {
        this.isLocked = false;
    }
}
