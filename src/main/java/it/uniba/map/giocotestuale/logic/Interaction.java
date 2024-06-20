package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class Interaction {
    private Command interactionType;

    public Interaction(Command interactionType) {
        this.interactionType = interactionType;
    }

    public Command getInteractionType() {
        return interactionType;
    }

    public abstract boolean isCorrectInteraction(final GameObject gameObject, final Command interactionType);
    public abstract void executeInteraction();
}
