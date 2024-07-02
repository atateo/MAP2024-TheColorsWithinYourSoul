package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.type.Command;

import java.util.List;

/**
 * Classe che gestisce le interazioni a catena tra due oggetti. Queste sono interazioni tra due oggetti
 * che non sono direttamente controllate da un comando. Un esempio di interazione a catena:
 * "Se la torcia è accesa, la stanza si illumina."
 */
public class ChainInteraction extends TwoObjectInteraction {

    /**
     * Costruttore con parametri della classe ChainInteraction. Inizializza tutti gli attributi della superclasse.
     * @param firstObject Primo oggetto. Causa dell'interazione.
     * @param secondObject Secondo oggetto. Subisce la conseguenza dell'interazione.
     * @param targetState Stato che il primo oggetto deve avere per scaturire l'interazione.
     * @param resultState Stato che il secondo oggetto assumerà dopo l'interazione.
     * @param interaction Istanza dell'interfaccia funzionale Interactable che definisce il codice dell'interazione.
     */
    public ChainInteraction(final GameObject firstObject, final GameObject secondObject, final String targetState, final String resultState, final Interactable interaction) {
        super(firstObject, secondObject, null, targetState, resultState, interaction);
    }

    /**
     * Metodo che verifica se, sulla base degli oggetti passati come parametro, l'interazione
     * va effettuata su di essi. Definizione del metodo nella superclasse.
     * @param gameObjects Lista degli oggetti da analizzare per vedere se sono interessati dall'interazione.
     * @param interactionType Comando dell'interazione. Essendo una interazione a catena, non viene analizzato.
     * @return Booleano che indica se l'interazione interessa gli oggetti o meno.
     */
    @Override
    public boolean isCorrectInteraction(final List<GameObject> gameObjects, final Command interactionType) {
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
}