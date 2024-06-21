package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class Interaction {
    private String targetState;
    private String resultState;
    private Interactable interaction;
    private Command interactionType;

    public Interaction(final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        this.interaction = interaction;
        this.targetState = targetState;
        this.resultState = resultState;
        this.interactionType = interactionType;
    }

    public Command getInteractionType() {
        return interactionType;
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

    public abstract boolean isCorrectInteraction(final GameObject gameObject, final Command interactionType);
    public abstract void executeInteraction();
}
