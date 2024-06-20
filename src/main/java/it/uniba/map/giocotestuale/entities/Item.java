package it.uniba.map.giocotestuale.entities;

import java.util.ArrayList;

public class Item extends Interactable {
    private boolean isPickable;
    private boolean isPaintable;

    private Color currentColor;

    public Item(final int id, final String name, final ArrayList<String> aliases, final boolean isPickable, final boolean isPaintable) {
        super(id, name, aliases);
        this.isPickable = isPickable;
        this.isPaintable = isPaintable;
        this.currentColor = Color.NEUTRAL;
    }

    public boolean getPickable() {
        return this.isPickable;
    }

    public boolean getPaintable() {
        return this.isPaintable;
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
