package it.uniba.map.giocotestuale.type;

import it.uniba.map.giocotestuale.entities.game.GameObject;

/**
 * Classe che rappresenta l'output fornito dal Parser.
 */
public class ParserOutput {
    /**
     * Tipo di comando dell'output.
     */
    private CommandEnum commandType;
    /**
     * Primo parametro dell'output.
     */
    private GameObject firstObject;
    /**
     * Secondo parametro dell'output.
     */
    private GameObject secondObject;

    /**
     * Costruttore senza parametri di ParserOutput.
     */
    public ParserOutput() {}

    /**
     * Costruttore con parametri di ParserOutput. Inizializza tutti gli attributi.
     * @param commandType Tipo di comando dell'output.
     * @param firstObject Primo parametro dell'output.
     * @param secondObject Secondo parametro dell'output.
     */
    public ParserOutput(CommandEnum commandType, GameObject firstObject, GameObject secondObject) {
        this.commandType = commandType;
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    /**
     * Metodo getter per il tipo di comando.
     * @return Tipo di comando.
     */
    public CommandEnum getCommandType() {
        return this.commandType;
    }

    /**
     * Metodo getter per il primo parametro.
     * @return Primo parametro.
     */
    public GameObject getFirstObject() {
        return this.firstObject;
    }

    /**
     * Metodo getter per il secondo parametro.
     * @return Secondo parametro.
     */
    public GameObject getSecondObject() {
        return this.secondObject;
    }

    /**
     * Metodo setter per il tipo di comando.
     * @param commandType Tipo di comando da impostare.
     */
    public void setCommandType(CommandEnum commandType) {
        this.commandType = commandType;
    }

    /**
     * Metodo setter per il primo parametro.
     * @param firstObject Oggetto di gioco da impostare come primo parametro.
     */
    public void setFirstObject(GameObject firstObject) {
        this.firstObject = firstObject;
    }

    /**
     * Metodo setter per il primo parametro.
     * @param secondObject Oggetto di gioco da impostare come secondo parametro.
     */
    public void setSecondObject(GameObject secondObject) {
        this.secondObject = secondObject;
    }

    /**
     * Override del metodo equals. Verifica se l'istanza corrente sia uguale a quella passata come parametro,
     * verificando l'uguaglianza tra il tipo di comando e i due parametri.
     * @param o Oggetto da confrontare con l'istanza.
     * @return Booleano che indica se le due istanze sono uguali.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ParserOutput that)) return false;

        if (this.getCommandType() != that.getCommandType()) return false;

        if(this.getSecondObject() == null && that.getSecondObject() != null) return false;

        if(this.getSecondObject() != null && that.getSecondObject() == null) return false;

        if (!this.getFirstObject().equals(that.getFirstObject())) return false;

        return this.getSecondObject().equals(that.getSecondObject());
    }
}