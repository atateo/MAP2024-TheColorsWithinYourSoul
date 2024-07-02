package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

/**
 * Classe che gestisce l'interazione diretta tra due oggetti mediante un comando. Ad esempio:
 * "Usa chiave su porta".
 */
public class DirectInteraction extends TwoObjectInteraction {

    /**
     * Costruttore con parametri della classe ChainInteraction. Inizializza tutti gli attributi della superclasse.
     * @param firstObject Primo oggetto.
     * @param secondObject Secondo oggetto.
     * @param interactionType Comando dell'interazione.
     * @param targetState Stato che il primo oggetto deve avere per iniziare l'interazione.
     * @param resultState Stato che il secondo oggetto avrà dopo l'interazione.
     * @param interaction Istanza dell'interfaccia funzionale Interactable che definisce il codice dell'interazione.
     */
    public DirectInteraction(final GameObject firstObject, final GameObject secondObject, final Command interactionType, final String targetState, final String resultState, final Interactable interaction) {
        super(firstObject, secondObject, interactionType, targetState, resultState, interaction);
    }

    /**
     * Metodo che verifica se, sulla base degli oggetti passati e il comando passati come parametri,
     * l'interazione va effettuata su di essi. Definizione del metodo nella superclasse.
     * @param gameObjects Lista degli oggetti da analizzare per vedere se sono interessati dall'interazione.
     * @param interactionType Comando dell'interazione.
     * @return Booleano che indica se l'interazione interessa gli oggetti o meno.
     */
    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
        //Non è un'interazione a due oggetti
        if (gameObjects.size() != 2) {
            return false;
        }

        //Il comando non è corretto
        if (super.getInteractionCommand() != interactionType) {
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
}