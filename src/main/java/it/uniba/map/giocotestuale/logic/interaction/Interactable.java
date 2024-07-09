package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;

import java.util.List;

/**
 * Interfaccia funzionale usata per definire le interazioni tra gli oggetti.
 */
@FunctionalInterface
public interface Interactable {

    /**
     * Metodo astratto che definirà il comportamento dell'interazione tra gli oggetti.
     *
     * @param targetObjects Oggetti interessati dall'interazione.
     * @param states        Stati degli oggetti da analizzare nell'interazione.
     * @param game          Istanza del gioco su cui va effettuata l'interazione.
     */
    void executeInteraction(final List<GameObject> targetObjects, final List<String> states, GameEngine game);

}