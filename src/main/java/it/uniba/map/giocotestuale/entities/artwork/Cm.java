package it.uniba.map.giocotestuale.entities.artwork;

public class Cm {
    private String text;
    private double height;
    private double width;
    private Object depth;  // Can be null
    private Object diameter;  // Can be null

    // Getters and setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Object getDepth() {
        return depth;
    }

    public void setDepth(Object depth) {
        this.depth = depth;
    }

    public Object getDiameter() {
        return diameter;
    }

    public void setDiameter(Object diameter) {
        this.diameter = diameter;
    }
}
