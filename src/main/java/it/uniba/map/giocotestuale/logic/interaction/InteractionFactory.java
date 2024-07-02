package it.uniba.map.giocotestuale.logic.interaction;

import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.type.CommandEnum;

/**
 * Classe Factory che permette la creazione facilitata delle Interaction.
 */
public class InteractionFactory {
    /**
     * Costruttore della classe. Essendo una classe di utility, è privato.
     */
    private InteractionFactory() {}

    /**
     * Costruisce un oggetto SingleObjectInteraction sulla base dei parametri passati in input.
     * @param gameObject Oggetto interessato dall'interazione.
     * @param interactionType Comando dell'interazione.
     * @param targetState Stato iniziale dell'oggetto di gioco.
     * @param resultState Stato finale dell'oggetto di gioco.
     * @param interaction Comportamento dell'interazione.
     * @return Oggetto SingleObjectInteraction creato con gli attributi passati sopra.
     */
    public static Interaction buildInteraction(final GameObject gameObject, final CommandEnum interactionType, final String targetState, final String resultState, final Interactable interaction) {
        return new SingleObjectInteraction(gameObject, interactionType, targetState, resultState, interaction);
    }

    /**
     * Costruisce un oggetto TwoObjectInteraction (Direct o Chain) sulla base dei parametri passati in input.
     * @param firstObject Primo oggetto interessato dall'interazione.
     * @param secondObject Secondo oggetto interessato dall'interazione.
     * @param interactionType Comando dell'interazione.
     * @param targetState Stato iniziale del primo oggetto di gioco.
     * @param resultState Stato finale del secondo oggetto di gioco.
     * @param interaction Comportamento dell'interazione.
     * @param type Determina se l'interazione è diretta o a catena (true per diretta, false per a catena).
     * @return Oggetto TwoObjectInteraction (Direct o Chain) creato con gli attributi passati sopra.
     */
    public static Interaction buildInteraction(final GameObject firstObject, final GameObject secondObject, final CommandEnum interactionType, final String targetState, final String resultState, final Interactable interaction, final boolean type) {
        if (type) {
            return new DirectInteraction(firstObject, secondObject, interactionType, targetState, resultState, interaction);
        } else {
            return new ChainInteraction(firstObject, secondObject, targetState, resultState, interaction);
        }
    }
}
