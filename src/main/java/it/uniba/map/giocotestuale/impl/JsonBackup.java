package it.uniba.map.giocotestuale.impl;

import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;
import it.uniba.map.giocotestuale.utility.jsonutil.JsonUtil;

public class JsonBackup {

	public void createJsonBackup() {
		ColorsWithinYourSoulGame game = new ColorsWithinYourSoulGame();

		//Crea le stanze di gioco
		Room roomCentrale = new Room(0, "AtticoCentrale", "Neutro");
		Room roomColoriPrimari = new Room(1, "StanzaColoriPrimari", "Neutro");
		Room roomColoriSecondari = new Room(2, "StanzaColoriSecondari", "Neutro");
		Room roomFinale = new Room(3, "StanzaFinale", "Neutro");
		Room roomRosso = new Room(4, "StanzaRosso", "Neutro");
		Room roomBlu = new Room(5, "StanzaBlu", "Neutro");
		Room roomGiallo = new Room(6, "StanzaGiallo", "Neutro");
		Room roomVerde = new Room(7, "StanzaVerde", "Neutro");
		Room roomMarrone = new Room(8, "StanzaMarrone", "Neutro");
		Room roomViola = new Room(9, "StanzaViola", "Neutro");

		//Collegamenti stanza centrale
		roomCentrale.setNorthRoomConnection(roomFinale, true);
		roomFinale.setSouthRoomConnection(roomCentrale, true);

		roomCentrale.setWestRoomConnection(roomColoriPrimari, true);
		roomColoriPrimari.setEastRoomConnection(roomCentrale, false);

		roomCentrale.setEastRoomConnection(roomColoriSecondari, false);
		roomColoriSecondari.setWestRoomConnection(roomCentrale, false);

		//Collegamenti stanza colori primari
		roomColoriPrimari.setNorthRoomConnection(roomRosso, false);
		roomRosso.setSouthRoomConnection(roomColoriPrimari, false);

		roomColoriPrimari.setWestRoomConnection(roomBlu, true);
		roomBlu.setEastRoomConnection(roomColoriPrimari, true);

		roomColoriPrimari.setSouthRoomConnection(roomGiallo, true);
		roomGiallo.setNorthRoomConnection(roomColoriPrimari, true);

		//Collegamenti stanza colori secondari
		roomColoriSecondari.setNorthRoomConnection(roomVerde, true);
		roomVerde.setSouthRoomConnection(roomColoriSecondari, true);

		roomColoriSecondari.setEastRoomConnection(roomMarrone, true);
		roomMarrone.setWestRoomConnection(roomColoriSecondari, true);

		roomColoriSecondari.setSouthRoomConnection(roomViola, true);
		roomViola.setNorthRoomConnection(roomColoriSecondari, true);

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

		roomCentrale.addItem(torcia);
		roomCentrale.addItem(macerie);

		//Item StanzaRossoPenne
		Item caminoDestro = new Item(2, "CaminoDestro", List.of("CaminoDX"), "Spento");
		caminoDestro.initializeProperties(false, true, false);
		Item caminoSinistro = new Item(3, "CaminoSinistro", List.of("CaminoSX"), "SenzaLegna");
		caminoSinistro.initializeProperties(false, true, false);
		Item legnetti = new Item(4, "Legnetti", List.of("wood"), "Neutro");
		legnetti.initializeProperties(true, false, false);
		Item pennelloRosso = new Item(5, "PennelloRosso", List.of("RedBrush"), "Neutro");
		pennelloRosso.initializeProperties(true, false, false);

		roomRosso.addItem(caminoDestro);
		roomRosso.addItem(caminoSinistro);
		roomRosso.addItem(legnetti);
		roomRosso.addItem(pennelloRosso);

		//Item StanzaBlu
		Item ascia = new Item(6, "Ascia", List.of("Accetta", "Axe"), "Neutro");
		ascia.initializeProperties(true, false, false);
		Item albero = new Item(7, "Albero", List.of("Pianta", "Alberello"), "NonCresciuto");
		albero.initializeProperties(false, true, false);
		Item statuaDrago = new Item(8, "StatuaDrago", List.of("TestaDrago"), "SenzaAcqua");
		statuaDrago.initializeProperties(false, true, false);
		Item pennelloBlu = new Item(9, "PennelloBlu", List.of("BlueBrush"), "Neutro");
		pennelloBlu.initializeProperties(true, false, false);

		roomBlu.addItem(ascia);
		roomBlu.addItem(albero);
		roomBlu.addItem(statuaDrago);
		roomBlu.addItem(pennelloBlu);

		//Item StanzaGiallo
		Item interruttore = new Item(10, "Interruttore", List.of("Bottone"), "Acceso");
		interruttore.initializeProperties(false, true, false);
		Item bloccoDiFerro = new Item(11, "BloccoDiFerro", List.of("Lamina"), "NonSpostatoAcceso");
		bloccoDiFerro.initializeProperties(false, false, true);
		Item pennelloGiallo = new Item(12, "PennelloGiallo", List.of("YellowBrush"), "Neutro");
		pennelloGiallo.initializeProperties(true, false, false);

		roomGiallo.addItem(interruttore);
		roomGiallo.addItem(bloccoDiFerro);
		roomGiallo.addItem(pennelloGiallo);

		//Item StanzaVerde
		Item vaso = new Item(13, "Liana", List.of("Rampicante"), "NonCresciuto");
		vaso.initializeProperties(false, true, false);
		Item aiuola = new Item(14, "Aiuola", List.of("Aiola"), "NonCresciuto");
		aiuola.initializeProperties(false, true, false);
		Item pennelloVerde = new Item(15, "PennelloVerde", List.of("GreenBrush"), "Neutro");
		pennelloGiallo.initializeProperties(true, false, false);

		roomVerde.addItem(vaso);
		roomVerde.addItem(aiuola);
		roomVerde.addItem(pennelloVerde);

		//Item StanzaMarrone
		Item piedistallo = new Item(16, "Piedistallo", List.of("Sostegno"), "NonColorato");
		piedistallo.initializeProperties(false, true, true);
		Item scalpello = new Item(17, "Scalpello", List.of("Scalpellino", "Subbia"), "Neutro");
		scalpello.initializeProperties(true, false, false);
		Item pedana = new Item(18, "PedanaAPressione", List.of("Pedana", "PressurePlate"), "NonPremuta");
		pedana.initializeProperties(false, false, false);
		Item pennelloMarrone = new Item(19, "PennelloMarrone", List.of("BrownBrush"), "Neutro");
		pennelloMarrone.initializeProperties(true, false, false);

		roomMarrone.addItem(piedistallo);
		roomMarrone.addItem(scalpello);
		roomMarrone.addItem(pedana);
		roomMarrone.addItem(pennelloMarrone);

		//Item StanzaViola
		Item scala = new Item(20, "Scala", List.of("Ladder, Stepladder"), "Rotto");
		scala.initializeProperties(false, true, false);
		Item orologio = new Item(21, "Orologio", List.of("Dispositivo", "Clock"), "Rotto");
		orologio.initializeProperties(false, false, false);
		Item incavo = new Item(22, "Incavo", List.of("Incavatura"), "Vuoto");
		incavo.initializeProperties(false, false, false);
		Item pennelloViola = new Item(23, "PennelloViola", List.of("PurpleBrush"), "Neutro");
		pennelloViola.initializeProperties(true, false, false);

		roomViola.addItem(scala);
		roomViola.addItem(orologio);
		roomViola.addItem(incavo);
		roomViola.addItem(pennelloViola);

		//Item StanzaFinale
		Item cerchioDiMattonelle = new Item(24, "Vivaio", List.of("Mattonelle", "Cerchio"), "Ghiacciato");
		cerchioDiMattonelle.initializeProperties(false, true, false);

		roomFinale.addItem(cerchioDiMattonelle);

		//Aggiunge le stanze al gioco
		game.addRoom(roomCentrale);
		game.addRoom(roomColoriPrimari);
		game.addRoom(roomColoriSecondari);
		game.addRoom(roomFinale);
		game.addRoom(roomRosso);
		game.addRoom(roomBlu);
		game.addRoom(roomGiallo);
		game.addRoom(roomVerde);
		game.addRoom(roomMarrone);
		game.addRoom(roomViola);

		//Imposta la stanza iniziale
		game.setCurrentRoom(roomCentrale);

		JsonUtil.writeJsonToFile("src/main/resources/static/start.json", game);
	}
}