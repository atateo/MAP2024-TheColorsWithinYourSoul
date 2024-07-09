package it.uniba.map.giocotestuale.entities.artwork;

/**
 * Classe per la gestione dei link inerenti ad Artwork (opera d'arte).
 */
public class Link {
    /**
     * link dell'immagine .
     */
    private String href;

    /**
     * boolean che indica se l'immagine è provvista di template.
     */
    private boolean templated;

    /**
     * Metodo getter per il link dell'immagine.
     *
     * @return il link dell'immagine.
     */
    public String getHref() {
        return href;
    }

    /**
     * Metodo setter per il link dell'immagine.
     *
     * @param href il link dell'immagine.
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Metodo getter per il boolean isTemplated.
     *
     * @return il link dell'immagine (true se l'immagine è provvista di template, false altrimenti).
     */
    public boolean isTemplated() {
        return templated;
    }

    /**
     * Metodo setter per il boolean isTemplated.
     *
     * @param templated true se l'immagine è provvista di template, false altrimenti.
     */
    public void setTemplated(boolean templated) {
        this.templated = templated;
    }
}
