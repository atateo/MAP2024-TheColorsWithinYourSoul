package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;

@FunctionalInterface
public interface Interactable {

    void executeInteraction(GameObject sourceObject, GameObject targetObject, final String targetState, final String resultState, final GameEngine game);

}