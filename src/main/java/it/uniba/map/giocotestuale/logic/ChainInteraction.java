package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class ChainInteraction extends TwoObjectInteraction {

    public ChainInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(firstObject, secondObject, interactionType, targetState, resultState, interaction);
    }

    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        if (gameObjects.size() != 1) {
            return false;
        } else {
            return super.getFirstObject().equals(gameObjects.getFirst());
        }
    }

    @Override
    public void executeInteraction(final GameEngine game) {
        super.getSecondObject().setStatus(super.getResultState());

        super.getInteraction().executeInteraction(super.getFirstObject(), super.getSecondObject(), super.getTargetState(), super.getResultState(), game);
    }
}
