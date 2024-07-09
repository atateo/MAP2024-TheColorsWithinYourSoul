package it.uniba.map.giocotestuale.entities.game;

import java.io.Serializable;
import java.util.List;

/**
 * Classe astratta che rappresenta gli oggetti di gioco e i loro attributi.
 */
public abstract class GameObject implements Serializable {
    /**
     * ID dell'oggetto.
     */
    private int id;

    /**
     * Nome dell'oggetto.
     */
    private String name;

    /**
     * Lista degli alias dell'oggetto.
     */
    private List<String> aliases;

    /**
     * Stringa che rappresenta lo stato corrente dell'oggetto nel gioco.
     */
    private String status;

    /**
     * Costruttore con parametri della classe GameObject. Inizializza tutti gli attributi.
     *
     * @param id      ID dell'istanza dell'oggetto di gioco.
     * @param name    Nome dell'istanza dell'oggetto di gioco.
     * @param aliases Lista degli alias dell'istanza dell'oggetto di gioco.
     * @param status  Stringa che rappresenta lo stato iniziale dell'oggetto nel gioco.
     */
    public GameObject(final int id, final String name, final List<String> aliases, final String status) {
        this.id = id;
        this.name = name;
        this.aliases = aliases;
        this.status = status;
    }

    /**
     * Metodo getter per l'ID di questo oggetto di gioco.
     *
     * @return ID dell'istanza dell'oggetto di gioco.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Metodo setter per l'ID di questo oggetto di gioco.
     *
     * @param id Nuovo ID dell'istanza di questo oggetto di gioco.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Metodo getter per il nome di questo oggetto di gioco.
     *
     * @return Nome dell'istanza dell'oggetto di gioco.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metodo setter per il nome di questo oggetto di gioco.
     *
     * @param name Nuovo nome dell'istanza di questo oggetto di gioco.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Metodo getter per la lista di alias di questo oggetto di gioco.
     *
     * @return Lista degli alias dell'istanza di questo oggetto di gioco.
     */
    public List<String> getAliases() {
        return this.aliases;
    }

    /**
     * Metodo setter per la lista di alias questo oggetto di gioco.
     *
     * @param aliases Nuova lista di alias dell'istanza di questo oggetto di gioco.
     */
    public void setAliases(final List<String> aliases) {
        this.aliases = aliases;
    }

    /**
     * Metodo getter per lo stato attuale di questo oggetto di gioco.
     *
     * @return Stato corrente dell'istanza di questo oggetto di gioco.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Metodo setter per lo stato attuale di questo oggetto di gioco.
     * Si può usare durante il gioco per aggiornare lo stato dell'istanza dell'oggetto.
     *
     * @param status Nuovo stato corrente dell'istanza di questo oggetto di gioco.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Override del metodo equals. Verifica che due l'oggetto passato come parametro sia uguale all'istanza
     * di questo oggetto di classe, verificando sia gli oggetti stessi che i loro ID.
     *
     * @param o Oggetto da confrontare con l'istanza dell'oggetto di gioco.
     * @return Booleano che indica se i due oggetti sono uguali o meno.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameObject that)) return false;
        if (this.getClass() != that.getClass()) return false;
        if (this == o) return true;

        return this.getId() == that.getId();
    }

    /**
     * Metodo astratto che fa una query al DB per ottenere la descrizione dell'oggetto di gioco.
     *
     * @return Descrizione di questo oggetto di gioco, presa dal DB.
     */
    public abstract String getDescriptionFromDB();
}
