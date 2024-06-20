package it.uniba.map.giocotestuale.entities;

import java.util.ArrayList;

public abstract class Interactable {
    private int id;
    private String name;
    private ArrayList<String> aliases;

    public Interactable(final int id, final String name, final ArrayList<String> aliases) {
        this.id = id;
        this.name = name;
        this.aliases = aliases;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getAliases() {
        return this.aliases;
    }

    public void setAliases(final ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public abstract String getDescriptionFromDB();
}
