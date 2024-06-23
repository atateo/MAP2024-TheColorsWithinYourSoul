package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

public class ChainInteraction extends TwoObjectInteraction {

    public ChainInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(firstObject, secondObject, interactionType, targetState, resultState, interaction);
    }

    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        //Non è un'interazione a due oggetti
        if (gameObjects.size() != 2) {
            return false;
        }

        //Il primo oggetto non corrisponde
        if (super.getFirstObject().equals(gameObjects.getFirst())){
            return false;
        }

        //Se il targetState corrisponde allo stato del primo oggetto attualmente, ritorna true.
        return super.getTargetState().equals(gameObjects.getFirst().getStatus());

        //Una reazione a catena dipende esclusivamente dallo stato del primo oggetto e non dal comando.
        //Di conseguenza, non ci sono interazioni a catena da eseguire indipendentemente dal comando.
        //Inoltre, non ha bisogno di fare controlli sul comando e sul secondo oggetto.
    }

    @Override
    public void executeInteraction(final GameEngine game) {
        super.getSecondObject().setStatus(super.getResultState());

        super.getInteraction().executeInteraction(super.getFirstObject(), super.getSecondObject(), super.getTargetState(), super.getResultState(), game);
    }
}