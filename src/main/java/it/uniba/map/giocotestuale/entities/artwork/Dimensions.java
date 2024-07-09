package it.uniba.map.giocotestuale.entities.artwork;

/**
 * Classe per la gestione delle dimensioni espresse in pollici in e centimetri cm di Artwork (opera d'arte).
 */
public class Dimensions {
    /**
     * esprime le dimensioni in centimetri.
     */
    private Dimension in;

    /**
     * esprime le dimensioni in pollici.
     */
    private Dimension cm;

    /**
     * Metodo getter per l'oggetto dimensioni espresse in pollici.
     *
     * @return l'oggetto dimensioni espresse in pollici.
     */
    public Dimension getIn() {
        return in;
    }

    /**
     * Metodo setter per l'oggetto dimensioni espresse in pollici.
     *
     * @param in l'oggetto dimensioni espresse in pollici.
     */
    public void setIn(Dimension in) {
        this.in = in;
    }

    /**
     * Metodo getter per l'oggetto dimensioni espresse in centimetri.
     *
     * @return l'oggetto dimensioni espresse in centimetri.
     */
    public Dimension getCm() {
        return cm;
    }

    /**
     * Metodo setter per l'oggetto dimensioni espresse in centimetri.
     *
     * @param cm l'oggetto dimensioni espresse in centimetri.
     */
    public void setCm(Dimension cm) {
        this.cm = cm;
    }
}
