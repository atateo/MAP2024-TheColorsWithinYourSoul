package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class DirectInteraction extends TwoObjectInteraction {

    public DirectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(firstObject, secondObject, interactionType, targetState, resultState, interaction);
    }

    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        //Non è un'interazione a due oggetti
        if (gameObjects.size() != 2) {
            return false;
        }

        //Il primo o il secondo oggetto non corrispondono
        if (!super.getFirstObject().equals(gameObjects.get(0)) || !this.getSecondObject().equals(gameObjects.get(1))){
            return false;
        }

        //Se il targetState è nullo, allora il comando va eseguito indipendentemente dallo stato, quindi ritorna true.
        //Se il targetState corrisponde allo stato del primo oggetto attualmente, ritorna true.
        return super.getTargetState() == null || super.getTargetState().equals(gameObjects.getFirst().getStatus());
    }

    @Override
    public void executeInteraction(final GameEngine game) {
        super.getSecondObject().setStatus(super.getResultState());

        super.getInteraction().executeInteraction(super.getFirstObject(), super.getSecondObject(), super.getTargetState(), super.getResultState(), game);
    }
}