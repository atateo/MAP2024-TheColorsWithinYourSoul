package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

/**
 * Classe astratta che rappresenta le interazioni tra due oggetti.
 */
public abstract class TwoObjectInteraction extends Interaction {
    /**
     * Primo oggetto, scatena l'interazione.
     */
    private final GameObject firstObject;
    /**
     * Secondo oggetto, subisce l'interazione.
     */
    private final GameObject secondObject;

    /**
     * Costruttore con parametri della classe TwoObjectInteraction. Inizializza tutti i parametri.
     * @param firstObject Primo oggetto, scatena l'interazione.
     * @param secondObject Secondo oggetto, subisce l'interazione.
     * @param interactionType Tipo del comando dell'interazione.
     * @param targetState Stato che il primo oggetto deve avere per scatenare l'interazione.
     * @param resultState Stato che il secondo oggetto avrò dopo l'interazione.
     * @param interaction Istanza dell'interfaccia funzionale Interactable che definisce il codice dell'interazione.
     */
    public TwoObjectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    /**
     * Metodo getter per il primo oggetto dell'interazione.
     * @return Primo oggetto dell'interazione.
     */
    public GameObject getFirstObject() {
        return this.firstObject;
    }

    /**
     * Metodo getter per il secondo oggetto dell'interazione.
     * @return Secondo oggetto dell'interazione.
     */
    public GameObject getSecondObject() {
        return this.secondObject;
    }

    /**
     * Metodo che esegue l'interazione tra gli oggetti nell'istanza di gioco passata come parametro.
     * @param game Istanza di gioco su cui va effettuata l'interazione.
     */
    @Override
    public void executeInteraction(final GameEngine game) {
        List<GameObject> targetObjects = List.of(this.getFirstObject(), this.getSecondObject());
        List<String> states = List.of(this.getTargetState(), this.getResultState());

        this.getInteraction().executeInteraction(targetObjects, states, game);
    }
}