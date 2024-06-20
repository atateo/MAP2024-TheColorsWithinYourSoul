package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public abstract class GameObjectToGameObjectInteraction extends Interaction {
    private GameObject firstObject;
    private GameObject secondObject;

    public GameObjectToGameObjectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType) {
        super(interactionType);
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public boolean isCorrectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType) {
        if (this.firstObject != firstObject) return false;
        if (this.secondObject != secondObject) return false;

        return super.getInteractionType() != interactionType;
    }
}