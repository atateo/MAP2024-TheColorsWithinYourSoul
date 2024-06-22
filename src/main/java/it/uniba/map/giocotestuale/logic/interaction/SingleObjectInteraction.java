package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class SingleObjectInteraction extends Interaction {
    private GameObject gameObject;

    public SingleObjectInteraction(final GameObject gameObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        if (gameObjects.size() != 1) {
            return false;
        }

        if (!this.getGameObject().equals(gameObjects.getFirst())){
            return false;
        }

        return (super.getTargetState() == null || super.getTargetState().equals(gameObjects.getFirst().getStatus()));
    }

    @Override
    public void executeInteraction(final GameEngine game) {
        this.gameObject.setStatus(super.getResultState());

        super.getInteraction().executeInteraction(this.gameObject, this.gameObject, super.getTargetState(), super.getResultState(), game);
    }
}