package it.uniba.map.giocotestuale.logic;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class DirectInteraction extends TwoObjectInteraction {

    public DirectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(firstObject, secondObject, interactionType, targetState, resultState, interaction);
    }

    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        if (gameObjects.size() != 2) {
            return false;
        } else {
            return super.getFirstObject().equals(gameObjects.get(0)) && this.getSecondObject().equals(gameObjects.get(1));
        }
    }

    @Override
    public void executeInteraction(final GameEngine game) {
        super.getSecondObject().setStatus(super.getResultState());

        super.getInteraction().executeInteraction(super.getFirstObject(), super.getSecondObject(), super.getTargetState(), super.getResultState(), game);
    }
}