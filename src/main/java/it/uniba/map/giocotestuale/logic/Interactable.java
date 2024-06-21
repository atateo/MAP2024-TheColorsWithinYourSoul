package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;

@FunctionalInterface
public interface Interactable {

    void executeInteraction(GameObject sourceObject, GameObject targetObject, final String targetState, final String resultState);

}
