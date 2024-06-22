package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public abstract class Interaction {
    private final String targetState;
    private final String resultState;
    private final Interactable interaction;
    private final Command interactionCommand;

    public Interaction(final Command interactionCommand, final String targetState, final String resultState, final Interactable interaction) {
        this.interaction = interaction;
        this.targetState = targetState;
        this.resultState = resultState;
        this.interactionCommand = interactionCommand;
    }

    public Command getInteractionCommand() {
        return interactionCommand;
    }

    public String getTargetState() {
        return targetState;
    }

    public String getResultState() {
        return resultState;
    }

    public Interactable getInteraction() {
        return interaction;
    }

    public abstract boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType);
    public abstract void executeInteraction(final GameEngine game);
}
