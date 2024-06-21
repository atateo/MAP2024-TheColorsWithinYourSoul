package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public class TwoObjectInteraction extends Interaction {
    private GameObject firstObject;
    private GameObject secondObject;

    public TwoObjectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    @Override
    public boolean isCorrectInteraction(final GameObject gameObject, final Command interactionType) {
        if (this.firstObject != gameObject) return false;
        if (!gameObject.getStatus().equals(super.getTargetState())) return false;

        return super.getInteractionType() != interactionType;
    }

    @Override
    public void executeInteraction() {
        secondObject.setStatus(super.getResultState());

        super.getInteraction().executeInteraction(firstObject, secondObject, super.getTargetState(), super.getResultState());
    }
}