package it.uniba.map.giocotestuale.utility;

import java.util.List;

import it.uniba.map.giocotestuale.entities.Item;
import it.uniba.map.giocotestuale.entities.Room;

public class GameToJson {
	private String player;
	private List<Room> rooms;
	private Room room;
	private List<Item> inventario;
	
	public GameToJson(String player, List<Room> rooms, Room room, List<Item> inventario) {
		super();
		this.player = player;
		this.rooms = rooms;
		this.room = room;
		this.inventario = inventario;
	}
	public GameToJson() {
	}
	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Item> getInventario() {
		return inventario;
	}
	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}

}
