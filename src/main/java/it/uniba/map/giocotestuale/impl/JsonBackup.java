package it.uniba.map.giocotestuale.impl;

import java.util.ArrayList;
import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;
import it.uniba.map.giocotestuale.utility.jsonutil.GameToJson;
import it.uniba.map.giocotestuale.utility.jsonutil.JsonUtil;

public class JsonBackup {

	public void createJsonBackup() {
		ColorsWithinYourSoulGame game = new ColorsWithinYourSoulGame();

		//Crea le stanze di gioco
		Room room0 = new Room(0, "AtticoCentrale", "Neutro");
		Room room1 = new Room(1, "StanzaColoriPrimari", "Neutro");
		Room room2 = new Room(2, "StanzaColoriSecondari", "Neutro");
		Room room3 = new Room(3, "StanzaFinale", "Neutro");
		Room room4 = new Room(4, "StanzaRosso", "Neutro");
		Room room5 = new Room(5, "StanzaBlu", "Neutro");
		Room room6 = new Room(6, "StanzaGiallo", "Neutro");
		Room room7 = new Room(7, "StanzaVerde", "Neutro");
		Room room8 = new Room(8, "StanzaMarrone", "Neutro");
		Room room9 = new Room(9, "StanzaViola", "Neutro");

		//Collegamenti stanza centrale
		room0.setNorthRoomConnection(room3, true);
		room3.setSouthRoomConnection(room0, true);

		room0.setWestRoomConnection(room1, false);
		room1.setEastRoomConnection(room0, false);

		room0.setEastRoomConnection(room2, false);
		room2.setWestRoomConnection(room0, false);

		//Collegamenti stanza colori primari
		room1.setNorthRoomConnection(room4, false);
		room4.setSouthRoomConnection(room1, false);

		room1.setWestRoomConnection(room5, true);
		room5.setEastRoomConnection(room1, true);

		room1.setSouthRoomConnection(room6, true);
		room6.setNorthRoomConnection(room1, true);

		//Collegamenti stanza colori secondari
		room2.setNorthRoomConnection(room7, true);
		room7.setSouthRoomConnection(room2, true);

		room2.setEastRoomConnection(room8, true);
		room8.setWestRoomConnection(room2, true);

		room2.setSouthRoomConnection(room9, true);
		room9.setNorthRoomConnection(room2, true);

		//Colori
		game.getColors().add(new ColorClass(0, "Rosso", List.of("red"), false));
		game.getColors().add(new ColorClass(0, "Blu", List.of("blue"), false));
		game.getColors().add(new ColorClass(0, "Giallo", List.of("yellow"), false));
		game.getColors().add(new ColorClass(0, "Verde", List.of("green"), false));
		game.getColors().add(new ColorClass(0, "Marrone", List.of("brown"), false));
		game.getColors().add(new ColorClass(0, "Viola", List.of("purple"), false));

		//Item AtticoCentrale

		//Item StanzaColoriPrimari

		//Item StanzaRosso

		//Item StanzaBlu

		//Item StanzaGiallo

		//Item StanzaColoriSecondari

		//Item StanzaVerde

		//Item StanzaMarrone

		//Item StanzaViola

		//Aggiunge le stanze al gioco
		game.addRoom(room0);
		game.addRoom(room1);
		game.addRoom(room2);
		game.addRoom(room3);
		game.addRoom(room4);
		game.addRoom(room5);
		game.addRoom(room6);
		game.addRoom(room7);
		game.addRoom(room8);
		game.addRoom(room9);

		//Imposta la stanza iniziale
		game.setCurrentRoom(room0);
	}
}