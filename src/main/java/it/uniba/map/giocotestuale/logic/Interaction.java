package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.type.Command;

public abstract class Interaction {
    private Command interactionType;

    public Interaction(Command interactionType) {
        this.interactionType = interactionType;
    }

    public Command getInteractionType() {
        return interactionType;
    }

    public abstract void executeInteraction();
    public abstract boolean isCorrectInteraction();
}
