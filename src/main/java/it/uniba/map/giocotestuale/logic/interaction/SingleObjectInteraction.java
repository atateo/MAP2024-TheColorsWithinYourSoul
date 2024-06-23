package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class SingleObjectInteraction extends Interaction {
    private final GameObject gameObject;

    public SingleObjectInteraction(final GameObject gameObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        //Il comando non corrisponde
        if (super.getInteractionCommand() != interactionType) {
            return false;
        }

        //Non è un'interazione a singolo oggetto
        if (gameObjects.size() != 1) {
            return false;
        }

        //Non è l'oggetto giusto su cui effettuarlo
        if (!this.getGameObject().equals(gameObjects.getFirst())){
            return false;
        }

        //Se il targetState è nullo, allora il comando va eseguito indipendentemente dallo stato, quindi ritorna true.
        //Se il targetState corrisponde allo stato dell'oggetto attualmente, ritorna true.
        return (super.getTargetState() == null || super.getTargetState().equals(gameObjects.getFirst().getStatus()));
    }

    @Override
    public void executeInteraction(final GameEngine game) {
        this.gameObject.setStatus(super.getResultState());

        super.getInteraction().executeInteraction(this.gameObject, this.gameObject, super.getTargetState(), super.getResultState(), game);
    }
}