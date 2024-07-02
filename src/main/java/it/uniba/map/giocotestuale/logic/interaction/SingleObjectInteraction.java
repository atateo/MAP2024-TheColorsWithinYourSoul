package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.logic.GameEngine;
import it.uniba.map.giocotestuale.type.CommandEnum;

import java.util.List;

/**
 * Classe che definisce le interazioni che interessano un singolo oggetto.
 * Generalmente sono le interazioni dirette tra il player e un item.
 */
public class SingleObjectInteraction extends Interaction {
    /**
     * Oggetto interessato dall'interazione.
     */
    private final GameObject gameObject;

    /**
     * Costruttore con parametri della classe SingleObjectInteraction. Inizializza tutti i parametri.
     * @param gameObject Oggetto interessato dall'interazione.
     * @param interactionType Tipo di comando che scaturisce l'interazione.
     * @param targetState Stato che l'oggetto deve avere per scaturire l'interazione.
     * @param resultState Stato che l'oggetto avrà alla fine dell'interazione.
     * @param interaction Istanza dell'interfaccia funzionale Interactable che definisce il codice dell'interazione.
     */
    public SingleObjectInteraction(final GameObject gameObject, final CommandEnum interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(interactionType, targetState, resultState, interaction);
        this.gameObject = gameObject;
    }

    /**
     * Metodo getter per l'oggetto dell'interazione singola.
     * @return Oggetto interessato dall'interazione singola.
     */
    public GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * Metodo che verifica se, sulla base degli oggetti passati e il comando passati come parametri,
     * l'interazione va effettuata su di essi. Definizione del metodo nella superclasse.
     * @param gameObjects Lista degli oggetti da analizzare per vedere se sono interessati dall'interazione.
     * @param interactionType Comando dell'interazione.
     * @return Booleano che indica se l'interazione interessa gli oggetti o meno.
     */
    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final CommandEnum interactionType) {
        //Il comando non corrisponde
        if (super.getInteractionCommand() != interactionType) {
            return false;
        }

        //Non è un'interazione a singolo oggetto
        if (gameObjects.size() != 1) {
            return false;
        }

        //Non è l'oggetto giusto su cui effettuarlo
        if (!this.getGameObject().equals(gameObjects.getFirst())){
            return false;
        }

        //Se il targetState è nullo, allora il comando va eseguito indipendentemente dallo stato, quindi ritorna true.
        //Se il targetState corrisponde allo stato dell'oggetto attualmente, ritorna true.
        return (super.getTargetState() == null || super.getTargetState().equals(gameObjects.getFirst().getStatus()));
    }

    /**
     * Metodo che esegue l'interazione tra gli oggetti nell'istanza di gioco passata come parametro.
     * @param game Istanza di gioco su cui va effettuata l'interazione.
     */
    @Override
    public void executeInteraction(final GameEngine game) {
        List<GameObject> gameObjects = List.of(this.getGameObject());
        List<String> states = List.of(this.getTargetState(), this.getResultState());

        super.getInteraction().executeInteraction(gameObjects, states, game);
    }
}