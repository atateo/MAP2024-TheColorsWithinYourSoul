package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

/**
 * Classe astratta che definisce le interazioni tra oggetti.
 */
public abstract class Interaction {
    /**
     * Stato che l'oggetto o gli oggetti devono avere per scatenare l'interazione.
     */
    private final String targetState;
    /**
     * Stato che l'oggetto o gli oggetti avranno a fine interazione.
     */
    private final String resultState;
    /**
     * Istanza dell'interfaccia funzionale Interactable che definisce il codice dell'interazione.
     */
    private final Interactable interaction;
    /**
     * Tipo del comando che scaturisce l'interazione.
     */
    private final Command interactionCommand;

    /**
     * Costruttore con parametri della classe Interaction. Inizializza tutti i parametri.
     * @param interactionCommand Stato che l'oggetto o gli oggetti devono avere per scatenare l'interazione.
     * @param targetState Stato che l'oggetto o gli oggetti avranno a fine interazione.
     * @param resultState Tipo del comando che scaturisce l'interazione.
     * @param interaction Istanza dell'interfaccia funzionale Interactable che definisce il codice dell'interazione.
     */
    public Interaction(final Command interactionCommand, final String targetState, final String resultState, final Interactable interaction) {
        this.interaction = interaction;
        this.targetState = targetState;
        this.resultState = resultState;
        this.interactionCommand = interactionCommand;
    }

    /**
     * Metodo getter per il tipo di comando.
     * @return Tipo del comando dell'interazione.
     */
    public Command getInteractionCommand() {
        return interactionCommand;
    }

    /**
     * Metodo getter per il target state.
     * @return Stato che l'oggetto o gli oggetti devono avere per scatenare l'interazione.
     */
    public String getTargetState() {
        return targetState;
    }

    /**
     * Metodo getter per il result state.
     * @return Stato che l'oggetto o gli oggetti avranno a fine interazione.
     */
    public String getResultState() {
        return resultState;
    }

    /**
     * Metodo getter per l'istanza di Interactable.
     * @return Istanza di Interactable che definisce il codice dell'interazione.
     */
    public Interactable getInteraction() {
        return interaction;
    }

    /**
     * Metodo astratto che verifica se, sulla base degli oggetti passati e il comando
     * passati come parametri, l'interazione va effettuata su di essi.
     * @param gameObjects Lista degli oggetti da analizzare per vedere se sono interessati dall'interazione.
     * @param interactionType Comando dell'interazione.
     * @return Booleano che indica se l'interazione interessa gli oggetti o meno.
     */
    public abstract boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType);

    /**
     * Metodo astratto che esegue l'interazione tra gli oggetti nell'istanza di gioco passata come parametro.
     * @param game Istanza di gioco su cui va effettuata l'interazione.
     */
    public abstract void executeInteraction(final GameEngine game);
}