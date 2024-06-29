package it.uniba.map.giocotestuale.entities.artwork;

/**
* Classe per la gestione delle dimensioni di Artwork (opera d'arte).
*/
public class Dimension {
	/**
     * testo.
     */
    private String text;
    
    /**
     * altezza.
     */
    private double height;
    
    /**
     * larghezza.
     */
    private double width;
    
    /**
     * profondità.
     */
    private String depth;
    
    /**
     * diametro.
     */
    private String diameter;

    /**
     * Metodo getter per il testo.
     * @return testo.
     */
    public String getText() {
        return text;
    }

    /**
     * Metodo setter per il testo.
     * @param testo.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Metodo getter per l'altezza dell'opera d'arte.
     * @return l'altezza dell'opera d'arte.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Metodo setter per l'altezza dell'opera d'arte.
     * @param l'altezza dell'opera d'arte.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Metodo getter per la larghezza dell'opera d'arte.
     * @return la larghezza dell'opera d'arte.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Metodo setter per la larghezza dell'opera d'arte.
     * @param la larghezza dell'opera d'arte.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Metodo getter per la profondità dell'opera d'arte.
     * @return la profondità dell'opera d'arte.
     */
    public String getDepth() {
        return depth;
    }

    /**
     * Metodo setter per la profondità dell'opera d'arte.
     * @param la profondità dell'opera d'arte.
     */
    public void setDepth(String depth) {
        this.depth = depth;
    }

    /**
     * Metodo getter per il diametro dell'opera d'arte.
     * @return il diametro dell'opera d'arte.
     */
    public String getDiameter() {
        return diameter;
    }

    /**
     * Metodo setter per il diametro dell'opera d'arte.
     * @param il diametro dell'opera d'arte.
     */
    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }
}
