package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class TwoObjectInteraction extends Interaction {
    private final GameObject firstObject;
    private final GameObject secondObject;

    public TwoObjectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public GameObject getFirstObject() {
        return this.firstObject;
    }

    public GameObject getSecondObject() {
        return this.secondObject;
    }
}