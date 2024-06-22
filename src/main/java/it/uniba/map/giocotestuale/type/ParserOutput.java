package it.uniba.map.giocotestuale.type;

import it.uniba.map.giocotestuale.entities.GameObject;

public class ParserOutput {
    private Command commandType;
    private GameObject firstObject;
    private GameObject secondObject;

    public ParserOutput(Command commandType, GameObject firstObject, GameObject secondObject) {
        this.commandType = commandType;
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public Command getCommandType() {
        return this.commandType;
    }

    public GameObject getFirstObject() {
        return this.firstObject;
    }

    public GameObject getSecondObject() {
        return this.secondObject;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ParserOutput that)) return false;

        if (this.getCommandType() != that.getCommandType()) return false;

        if (this.getFirstObject().equals(that.getFirstObject())) return false;

        if(this.getSecondObject() == null && that.getSecondObject() != null) return false;

        if(this.getSecondObject() != null && that.getSecondObject() == null) return false;

        return this.getSecondObject().equals(that.getSecondObject());
    }
}
