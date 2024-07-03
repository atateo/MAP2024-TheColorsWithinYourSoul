package it.uniba.map.giocotestuale.impl;

import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;

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

		room0.setWestRoomConnection(room1, true);
		room1.setEastRoomConnection(room0, true);

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
		Item torcia = new Item(0, "Torcia", List.of("torch"), "Spento");
		torcia.initializeProperties(true, true, false);
		Item macerie = new Item(1, "Macerie", List.of("massi"), "NonSpostato");
		macerie.initializeProperties(false, false, true);

		room0.addItem(torcia);
		room0.addItem(macerie);

		//Item StanzaRosso
		Item caminoDestro = new Item(2, "CaminoDestro", List.of("CaminoDX"), "Spento");
		caminoDestro.initializeProperties(false, true, false);
		Item caminoSinistro = new Item(3, "CaminoSinistro", List.of("CaminoSX"), "SenzaLegna");
		caminoSinistro.initializeProperties(false, true, false);
		Item legnetti = new Item(4, "Legnetti", List.of("wood"), "Neutro");
		legnetti.initializeProperties(true, false, false);
		Item pennelloRosso = new Item(5, "PennelloRosso", List.of("RedBrush"), "Neutro");
		pennelloRosso.initializeProperties(true, false, false);

		room4.addItem(caminoDestro);
		room4.addItem(caminoSinistro);
		room4.addItem(legnetti);
		room4.addItem(pennelloRosso);

		//Item StanzaBlu
		Item ascia = new Item(6, "Ascia", List.of("Accetta", "Axe"), "Neutro");
		ascia.initializeProperties(true, false, false);
		Item albero = new Item(7, "Albero", List.of("Pianta", "Alberello"), "NonCresciuto");
		albero.initializeProperties(false, true, false);
		Item statuaDrago = new Item(8, "StatuaDrago", List.of("TestaDrago"), "SenzaAcqua");
		statuaDrago.initializeProperties(false, true, false);
		Item pennelloBlu = new Item(9, "PennelloBlu", List.of("BlueBrush"), "Neutro");
		pennelloBlu.initializeProperties(true, false, false);

		room5.addItem(ascia);
		room5.addItem(albero);
		room5.addItem(statuaDrago);
		room5.addItem(pennelloBlu);

		//Item StanzaGiallo
		Item interruttore = new Item(10, "Interruttore", List.of("Bottone"), "Acceso");
		interruttore.initializeProperties(false, true, false);
		Item bloccoDiFerro = new Item(11, "BloccoDiFerro", List.of("Lamina"), "NonSpostatoAcceso");
		bloccoDiFerro.initializeProperties(false, false, true);
		Item pennelloGiallo = new Item(9, "PennelloGiallo", List.of("YellowBrush"), "Neutro");
		pennelloGiallo.initializeProperties(true, false, false);

		room5.addItem(interruttore);
		room5.addItem(bloccoDiFerro);
		room5.addItem(pennelloGiallo);

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