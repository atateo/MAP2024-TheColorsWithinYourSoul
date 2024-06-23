package it.uniba.map.giocotestuale.entities;

import java.util.List;

public abstract class GameObject {
    private int id;
    private String name;
    private List<String> aliases;
    private String status;

    public GameObject(final int id, final String name, final List<String> aliases, final String status) {
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

    public List<String> getAliases() {
        return this.aliases;
    }

    public void setAliases(final List<String> aliases) {
        this.aliases = aliases;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameObject that)) return false;
        if (this == o) return true;

        return this.getId() == that.getId();
    }

    public abstract String getDescriptionFromDB();
    public abstract void updateStatus(final String newStatus);
}
