package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

public class SingleObjectInteraction extends Interaction {
    private GameObject gameObject;

    public SingleObjectInteraction(final GameObject gameObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.gameObject = gameObject;
    }

    @Override
    public boolean isCorrectInteraction(final GameObject gameObject, final Command interactionType) {
        if (!this.gameObject.equals(gameObject)) return false;
        if (!gameObject.getStatus().equals(super.getTargetState())) return false;

        return super.getInteractionType() != interactionType;
    }

    @Override
    public void executeInteraction() {
        gameObject.setStatus(super.getResultState());

        super.getInteraction().executeInteraction(gameObject, gameObject, super.getTargetState(), super.getResultState());
    }
}
