package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class SingularGameObjectInteraction extends Interaction {
    private GameObject gameObject;

    public SingularGameObjectInteraction(final GameObject gameObject, final Command interactionType) {
        super(interactionType);
        this.gameObject = gameObject;
    }

    public boolean isCorrectInteraction(final GameObject gameObject, final Command interactionType) {
        if (this.gameObject != gameObject) return false;

        return super.getInteractionType() != interactionType;
    }
}
