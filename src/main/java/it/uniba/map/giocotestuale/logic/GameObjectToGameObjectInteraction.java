package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class GameObjectToGameObjectInteraction implements Interactable {
    private GameObject firstObject;
    private GameObject secondObject;
    private Command interactionType;
}