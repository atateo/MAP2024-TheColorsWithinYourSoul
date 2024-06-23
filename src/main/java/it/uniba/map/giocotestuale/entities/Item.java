package it.uniba.map.giocotestuale.entities;

import java.util.List;

public class Item extends GameObject {
    private boolean isPickable;
    private boolean isPaintable;
    private boolean isMovable;

    public Item(final int id, final String name, final List<String> aliases, final String status) {
        super(id, name, aliases, status);
    }

    public void initializeProperties (final boolean isPickable, final boolean isPaintable, final boolean isMovable) {
        setPickable(isPickable);
        setPaintable(isPaintable);
        setMovable(isMovable);
    }

    public boolean getPickable() {
        return this.isPickable;
    }

    public boolean getPaintable() {
        return this.isPaintable;
    }

    public boolean getMovable() {
        return this.isMovable;
    }

    public void setPickable(final boolean isPickable) {
        this.isPickable = isPickable;
    }

    public void setPaintable(final boolean isPaintable) {
        this.isPaintable = isPaintable;
    }

    public void setMovable(final boolean isMovable) {
        this.isMovable = isMovable;
    }

    @Override
    public String getDescriptionFromDB() {
        //Placeholder, verrà implementata insieme al DB
        return null;
    }

    @Override
    public void updateStatus(final String newStatus) {
        super.setStatus(newStatus);

        //Scrivere qui l'implementazione dei vari status
    }
}
