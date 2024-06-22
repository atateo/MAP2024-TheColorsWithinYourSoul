package it.uniba.map.giocotestuale.entities;

import java.util.ArrayList;

public abstract class GameObject {
    private int id;
    private String name;
    private ArrayList<String> aliases;
    private String status;

    public GameObject(final int id, final String name, final ArrayList<String> aliases, final String status) {
        this.id = id;
        this.name = name;
        this.aliases = aliases;
        this.status = status;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public abstract String getDescriptionFromDB();
    public abstract void updateStatus(final String newStatus);
}
