package it.uniba.map.giocotestuale.entities;

import java.util.ArrayList;

public class Item extends GameObject {
    private boolean isPickable;
    private boolean isPaintable;
    private boolean isMovable;

    private Color currentColor;

    public Item(final int id, final String name, final ArrayList<String> aliases, final String status) {
        super(id, name, aliases, status);
        this.currentColor = Color.NEUTRAL;
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

    public Color getCurrentColor() {
        return this.currentColor;
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

    public boolean setCurrentColor(final Color currentColor) {
        if (getPaintable()) {
            this.currentColor = currentColor;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescriptionFromDB() {
        //Placeholder, verrà implementata insieme al DB
        return null;
    }
}
