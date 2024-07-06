package it.uniba.map.giocotestuale.impl;

import java.util.ArrayList;
import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.logic.interaction.InteractionFactory;
import it.uniba.map.giocotestuale.utility.jsonutil.JsonUtil;

/**
 * Classe contenente la logica di base del gioco The Colors Within Your Soul e vari metodi per gestirla.
 */
public class BaseGameLogic {

	/**
	 * Crea un file Json corrispondente allo stato iniziale del gioco.
	 */
	public void createJsonBackup() {
		ColorsWithinYourSoulGame game = new ColorsWithinYourSoulGame();

		//Stanze
		ArrayList<Room> rooms = getAllBaseGameRooms();

		//Collegamenti stanza centrale
		rooms.get(0).setNorthRoomConnection(rooms.get(3), false);
		rooms.get(3).setSouthRoomConnection(rooms.get(0), false);

		rooms.get(0).setWestRoomConnection(rooms.get(1), false);
		rooms.get(1).setEastRoomConnection(rooms.get(0), false);

		rooms.get(0).setEastRoomConnection(rooms.get(2), false);
		rooms.get(2).setWestRoomConnection(rooms.get(0), false);

		//Collegamenti stanza colori primari
		rooms.get(1).setNorthRoomConnection(rooms.get(4), false);
		rooms.get(4).setSouthRoomConnection(rooms.get(1), false);

		rooms.get(1).setWestRoomConnection(rooms.get(5), false);
		rooms.get(5).setEastRoomConnection(rooms.get(1), false);

		rooms.get(1).setSouthRoomConnection(rooms.get(6), false);
		rooms.get(6).setNorthRoomConnection(rooms.get(1), false);

		//Collegamenti stanza colori secondari
		rooms.get(2).setNorthRoomConnection(rooms.get(7), false);
		rooms.get(7).setSouthRoomConnection(rooms.get(2), false);

		rooms.get(2).setEastRoomConnection(rooms.get(8), false);
		rooms.get(8).setWestRoomConnection(rooms.get(2), false);

		rooms.get(2).setSouthRoomConnection(rooms.get(9), false);
		rooms.get(9).setNorthRoomConnection(rooms.get(2), false);

		//Colori
		game.getColors().addAll(getAllBaseGameColors());

		//Item
		ArrayList<Item> items = getAllBaseGameItems();

		//Item AtticoCentrale
		rooms.get(0).addItem(items.get(0));
		rooms.get(0).addItem(items.get(1));

		//Item StanzaRossoPenne
		rooms.get(4).addItem(items.get(2));
		rooms.get(4).addItem(items.get(3));
		rooms.get(4).addItem(items.get(4));
		rooms.get(4).addItem(items.get(5));

		//Item StanzaBlu
		rooms.get(5).addItem(items.get(6));
		rooms.get(5).addItem(items.get(7));
		rooms.get(5).addItem(items.get(8));
		rooms.get(5).addItem(items.get(9));

		//Item StanzaGiallo
		rooms.get(6).addItem(items.get(10));
		rooms.get(6).addItem(items.get(11));
		rooms.get(6).addItem(items.get(12));

		//Item StanzaVerde
		rooms.get(7).addItem(items.get(13));
		rooms.get(7).addItem(items.get(14));
		rooms.get(7).addItem(items.get(15));

		//Item StanzaMarrone
		rooms.get(8).addItem(items.get(16));
		rooms.get(8).addItem(items.get(17));
		rooms.get(8).addItem(items.get(18));
		rooms.get(8).addItem(items.get(19));

		//Item StanzaViola
		rooms.get(9).addItem(items.get(20));
		rooms.get(9).addItem(items.get(21));
		rooms.get(9).addItem(items.get(22));
		rooms.get(9).addItem(items.get(23));

		//Item StanzaFinale
		rooms.get(3).addItem(items.get(24));

		//Aggiunge le stanze al gioco
		game.addRoom(rooms.get(0));
		game.addRoom(rooms.get(1));
		game.addRoom(rooms.get(2));
		game.addRoom(rooms.get(3));
		game.addRoom(rooms.get(4));
		game.addRoom(rooms.get(5));
		game.addRoom(rooms.get(6));
		game.addRoom(rooms.get(7));
		game.addRoom(rooms.get(8));
		game.addRoom(rooms.get(9));

		//Imposta la stanza iniziale
		game.setCurrentRoom(rooms.get(0));

		//Inizializza le interazioni di gioco
		//game.defineGameInteractions();

		JsonUtil.writeJsonToFile("src/main/resources/static/start.json", game);
	}

	/**
	 * Metodo che restituisce tutte le stanze del gioco.
	 * @return ArrayList contenente le stanze del gioco.
	 */
	public ArrayList<Room> getAllBaseGameRooms() {
		ArrayList<Room> baseGameRooms = new ArrayList<>();

		//Crea le stanze di gioco
		baseGameRooms.add(new Room(0, "AtticoCentrale", "Neutro"));
		baseGameRooms.add(new Room(1, "StanzaColoriPrimari", "Neutro"));
		baseGameRooms.add(new Room(2, "StanzaColoriSecondari", "Neutro"));
		baseGameRooms.add(new Room(3, "StanzaFinale", "Neutro"));
		baseGameRooms.add(new Room(4, "StanzaRosso", "Neutro"));
		baseGameRooms.add(new Room(5, "StanzaBlu", "Neutro"));
		baseGameRooms.add(new Room(6, "StanzaGiallo", "Neutro"));
		baseGameRooms.add(new Room(7, "StanzaVerde", "Neutro"));
		baseGameRooms.add(new Room(8, "StanzaMarrone", "Neutro"));
		baseGameRooms.add(new Room(9, "StanzaViola", "Neutro"));

		return baseGameRooms;
	}

	/**
	 * Metodo che restituisce tutti gli item presenti nel salvataggio base del gioco.
	 * @return ArrayList contenente tutti gli item del gioco.
	 */
	public ArrayList<Item> getAllBaseGameItems() {
		ArrayList<Item> baseGameItems = new ArrayList<>();

		Item torcia = new Item(0, "Torcia", List.of("torch"), "Spento");
		torcia.initializeProperties(true, true, false);
		Item macerie = new Item(1, "Macerie", List.of("massi"), "NonSpostato");
		macerie.initializeProperties(false, false, true);
		Item caminoDestro = new Item(2, "CaminoDestro", List.of("CaminoDX"), "Spento");
		caminoDestro.initializeProperties(false, true, false);
		Item caminoSinistro = new Item(3, "CaminoSinistro", List.of("CaminoSX"), "SenzaLegna");
		caminoSinistro.initializeProperties(false, true, false);
		Item legnetti = new Item(4, "Legnetti", List.of("wood"), "Neutro");
		legnetti.initializeProperties(true, false, false);
		Item pennelloRosso = new Item(5, "PennelloRosso", List.of("RedBrush"), "Neutro");
		pennelloRosso.initializeProperties(true, false, false);
		Item ascia = new Item(6, "Ascia", List.of("Accetta", "Axe"), "Neutro");
		ascia.initializeProperties(true, false, false);
		Item albero = new Item(7, "Albero", List.of("Pianta", "Alberello"), "NonCresciuto");
		albero.initializeProperties(false, true, false);
		Item statuaDrago = new Item(8, "StatuaDrago", List.of("TestaDrago"), "SenzaAcqua");
		statuaDrago.initializeProperties(false, true, false);
		Item pennelloBlu = new Item(9, "PennelloBlu", List.of("BlueBrush"), "Neutro");
		pennelloBlu.initializeProperties(true, false, false);
		Item interruttore = new Item(10, "Interruttore", List.of("Bottone"), "Acceso");
		interruttore.initializeProperties(false, true, false);
		Item bloccoDiFerro = new Item(11, "BloccoDiFerro", List.of("Lamina"), "NonSpostatoAcceso");
		bloccoDiFerro.initializeProperties(false, false, true);
		Item pennelloGiallo = new Item(12, "PennelloGiallo", List.of("YellowBrush"), "Neutro");
		pennelloGiallo.initializeProperties(true, false, false);
		Item liana = new Item(13, "Liana", List.of("Rampicante"), "NonCresciuto");
		liana.initializeProperties(false, true, false);
		Item aiuola = new Item(14, "Aiuola", List.of("Aiola"), "NonCresciuto");
		aiuola.initializeProperties(false, true, false);
		Item pennelloVerde = new Item(15, "PennelloVerde", List.of("GreenBrush"), "Neutro");
		pennelloVerde.initializeProperties(true, false, false);
		Item piedistallo = new Item(16, "Piedistallo", List.of("Sostegno"), "NonColorato");
		piedistallo.initializeProperties(false, true, true);
		Item scalpello = new Item(17, "Scalpello", List.of("Scalpellino", "Subbia"), "Neutro");
		scalpello.initializeProperties(true, false, false);
		Item pedana = new Item(18, "PedanaAPressione", List.of("Pedana", "PressurePlate"), "NonPremuta");
		pedana.initializeProperties(false, false, false);
		Item pennelloMarrone = new Item(19, "PennelloMarrone", List.of("BrownBrush"), "Neutro");
		pennelloMarrone.initializeProperties(true, false, false);
		Item scala = new Item(20, "Scala", List.of("Ladder, Stepladder"), "Rotto");
		scala.initializeProperties(false, true, false);
		Item orologio = new Item(21, "Orologio", List.of("Dispositivo", "Clock"), "Rotto");
		orologio.initializeProperties(false, false, false);
		Item incavo = new Item(22, "Incavo", List.of("Incavatura"), "Vuoto");
		incavo.initializeProperties(false, false, false);
		Item pennelloViola = new Item(23, "PennelloViola", List.of("PurpleBrush"), "Neutro");
		pennelloViola.initializeProperties(true, false, false);
		Item vivaio = new Item(24, "Vivaio", List.of("Mattonelle", "Cerchio"), "Ghiacciato");
		vivaio.initializeProperties(false, true, false);

		baseGameItems.add(torcia);
		baseGameItems.add(macerie);
		baseGameItems.add(caminoDestro);
		baseGameItems.add(caminoSinistro);
		baseGameItems.add(legnetti);
		baseGameItems.add(pennelloRosso);
		baseGameItems.add(ascia);
		baseGameItems.add(albero);
		baseGameItems.add(statuaDrago);
		baseGameItems.add(pennelloBlu);
		baseGameItems.add(interruttore);
		baseGameItems.add(bloccoDiFerro);
		baseGameItems.add(pennelloGiallo);
		baseGameItems.add(liana);
		baseGameItems.add(aiuola);
		baseGameItems.add(pennelloVerde);
		baseGameItems.add(piedistallo);
		baseGameItems.add(scalpello);
		baseGameItems.add(pedana);
		baseGameItems.add(pennelloMarrone);
		baseGameItems.add(scala);
		baseGameItems.add(orologio);
		baseGameItems.add(incavo);
		baseGameItems.add(pennelloViola);
		baseGameItems.add(vivaio);

		return baseGameItems;
	}

	/**
	 * Metodo che restituisce tutti i colori implementati nel gioco.
	 * @return ArrayList contenente tutti i colori del gioco.
	 */
	public ArrayList<ColorClass> getAllBaseGameColors() {
		ArrayList<ColorClass> baseGameColors = new ArrayList<>();

		baseGameColors.add(new ColorClass(0, "Rosso", List.of("red"), false));
		baseGameColors.add(new ColorClass(1, "Blu", List.of("blue"), false));
		baseGameColors.add(new ColorClass(2, "Giallo", List.of("yellow"), false));
		baseGameColors.add(new ColorClass(3, "Verde", List.of("green"), false));
		baseGameColors.add(new ColorClass(4, "Marrone", List.of("brown"), false));
		baseGameColors.add(new ColorClass(5, "Viola", List.of("purple"), false));

		return baseGameColors;
	}

	/**
	 * Metodo che restituisce tutte le interactions che compongono la logica di gioco sulla base di ciò che è presente nel salvataggio.
	 * @param objects La lista di items, colori e stanze disponibili.
	 * @return ArrayList contenente tutte le interactions di gioco.
	 */
	public ArrayList<Interaction> getGameLogic(ArrayList<GameObject> objects) {
		ArrayList<Interaction> gameLogic = new ArrayList<>();

		if (getObjectByName("PennelloRosso", objects) != null) {
			gameLogic.add(InteractionFactory.buildInteraction(
					getObjectByName("PennelloRosso", objects), getObjectByName("Rosso", objects), "Neutro", "Neutro",
					(gameObjects, targetStates, gameEngine) -> {
						((ColorClass) gameObjects.get(1)).setUnlocked(true);
						GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
								"però senti qualcosa di diverso in te... Hai sbloccato il colore rosso!");

						GameToGUICommunication.getInstance().unlockColor((ColorClass) gameObjects.get(1));
					}
			));
		}

		//Sblocca il colore blu
		if (getObjectByName("PennelloBlu", objects) != null) {
			gameLogic.add(InteractionFactory.buildInteraction(
					getObjectByName("PennelloBlu", objects), getObjectByName("Blu", objects), "Neutro", "Neutro",
					(gameObjects, targetStates, gameEngine) -> {
						((ColorClass) gameObjects.get(1)).setUnlocked(true);
						GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
								"però senti qualcosa di diverso in te... Hai sbloccato il colore blu!");

						GameToGUICommunication.getInstance().unlockColor((ColorClass) gameObjects.get(1));
					}
			));
		}

		//Sblocca il colore giallo
		if (getObjectByName("PennelloGiallo", objects) != null) {
			gameLogic.add(InteractionFactory.buildInteraction(
					getObjectByName("PennelloGiallo", objects), getObjectByName("Giallo", objects), "Neutro", "Neutro",
					(gameObjects, targetStates, gameEngine) -> {
						((ColorClass) gameObjects.get(1)).setUnlocked(true);
						GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
								"però senti qualcosa di diverso in te... Hai sbloccato il colore giallo!");

						GameToGUICommunication.getInstance().unlockColor((ColorClass) gameObjects.get(1));
					}
			));
		}

		//Sblocca il colore verde
		if ((getObjectByName("PennelloVerde", objects) != null)) {
			gameLogic.add(InteractionFactory.buildInteraction(
					getObjectByName("PennelloVerde", objects), getObjectByName("Verde", objects), "Neutro", "Neutro",
					(gameObjects, targetStates, gameEngine) -> {
						((ColorClass) gameObjects.get(1)).setUnlocked(true);
						GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
								"però senti qualcosa di diverso in te... Hai sbloccato il colore verde!");

						GameToGUICommunication.getInstance().unlockColor((ColorClass) gameObjects.get(1));
					}
			));
		}

		//Sblocca il colore marrone
		if (getObjectByName("PennelloMarrone", objects) != null) {
			gameLogic.add(InteractionFactory.buildInteraction(
					getObjectByName("PennelloMarrone", objects), getObjectByName("Marrone", objects), "Neutro", "Neutro",
					(gameObjects, targetStates, gameEngine) -> {
						((ColorClass) gameObjects.get(1)).setUnlocked(true);
						GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
								"però senti qualcosa di diverso in te... Hai sbloccato il colore marrone!");

						GameToGUICommunication.getInstance().unlockColor((ColorClass) gameObjects.get(1));
					}
			));
		}

		//Sblocca il colore viola
		if (getObjectByName("PennelloViola", objects) != null) {
			gameLogic.add(InteractionFactory.buildInteraction(
					getObjectByName("PennelloViola", objects), getObjectByName("Viola", objects), "Neutro", "Neutro",
					(gameObjects, targetStates, gameEngine) -> {
						((ColorClass) gameObjects.get(1)).setUnlocked(true);
						GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
								"però senti qualcosa di diverso in te... Hai sbloccato il colore viola!");

						GameToGUICommunication.getInstance().unlockColor((ColorClass) gameObjects.get(1));
					}
			));
		}
		
		return gameLogic;
	}

	/**
	 * Data una stringa come parametro, questo metodo restituisce l'item di gioco che ha quella stringa come nome.
	 * @param name Nome da cercare.
	 * @return Item con il nome cercato. Se non lo trova, restituisce null.
	 */
	public Item getItemByName(final String name) {
		for (Item item : getAllBaseGameItems()) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Data una stringa come parametro, questo metodo restituisce il colore di gioco che ha quella stringa come nome.
	 * @param name Nome da cercare.
	 * @return Colore con il nome cercato. Se non lo trova, restituisce null.
	 */
	public ColorClass getColorByName(final String name) {
		for (ColorClass color : getAllBaseGameColors()) {
			if (color.getName().equalsIgnoreCase(name)) {
				return color;
			}
		}
		return null;
	}

	/**
	 * Data una stringa come parametro, questo metodo restituisce l'oggetto di gioco che ha quella stringa come nome.
	 * @param name Nome da cercare.
	 * @param availableObjects Lista degli oggetti di gioco in cui cercare.
	 */
	public GameObject getObjectByName(final String name, ArrayList<GameObject> availableObjects) {
		for (GameObject gameObject : availableObjects) {
			if (gameObject.getName().equalsIgnoreCase(name)) {
				return gameObject;
			}
		}
		return null;
	}
}