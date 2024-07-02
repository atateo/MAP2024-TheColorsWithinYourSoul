package it.uniba.map.giocotestuale.utility;

import java.util.ArrayList;
import java.util.List;

import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.entities.game.Room;
import it.uniba.map.giocotestuale.utility.jsonutil.GameToJson;
import it.uniba.map.giocotestuale.utility.jsonutil.JsonUtil;

public class TestJson {

public static void main(String[] args) {
		JsonUtil jUtil = new JsonUtil();
		GameToJson gioco = new GameToJson();

		List<String> aliasC2 = new ArrayList<>();
		aliasC2.add("bianco1");
		aliasC2.add("bianco2");
		ColorClass color2 = new ColorClass(2, "bianco", aliasC2, false);

		List<String> aliasC1 = new ArrayList<>();
		aliasC1.add("rosso1");
		aliasC1.add("rosso2");
		ColorClass color = new ColorClass(1, "rosso", aliasC1, true);

		List<ColorClass> colors = new ArrayList<>();
		colors.add(color);
		colors.add(color2);
		gioco.setColors(colors);
		Room room = new Room(1,"ingresso","done");
		Room room2 = new Room(2,"salone","done");
		Room room3 = new Room(3, "cucina", "ready");
		List<Room> stanze = new ArrayList<Room>();
		stanze.add(room);
		stanze.add(room2);
		stanze.add(room3);
		gioco.setRooms(stanze);
		//gioco.setPlayer("Antimo");
		gioco.setRoom(new Room(3,"studio","actual"));
		List<Item> inventario = new ArrayList<Item>();
		
		List<String> aliasis = new ArrayList<String>();
		String alias = "test";
		String alias2 = "prova";
		aliasis.add(alias);
		aliasis.add(alias2);
		Item item = new Item(1,"torcia",aliasis,"took");
		
		List<String> aliasis2 = new ArrayList<String>();
		String alias3 = "test2";
		String alias4 = "prova2";
		aliasis.add(alias3);
		aliasis.add(alias4);
		Item item2 = new Item(1,"quadro",aliasis2,"moved");
		inventario.add(item2);
		inventario.add(item);
		gioco.setInventario(inventario);
		
		jUtil.writeJsonToFile("c:\\\\test.json", gioco);
		
		
		
		jUtil.readJsonFromFile("c:\\\\test.json", GameToJson.class);
		System.out.println("test");
	}

}
