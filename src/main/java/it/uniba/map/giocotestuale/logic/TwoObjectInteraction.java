package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class TwoObjectInteraction extends Interaction {
    private GameObject firstObject;
    private GameObject secondObject;

    public TwoObjectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType) {
        super(interactionType);
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    @Override
    public boolean isCorrectInteraction(final GameObject gameObject, final Command interactionType) {
        if (this.firstObject != gameObject) return false;

        return super.getInteractionType() != interactionType;
    }

    public abstract void executeInteraction();
}