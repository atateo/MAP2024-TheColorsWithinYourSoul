package it.uniba.map.giocotestuale.entities.artwork;

/**
 * Classe che rappresenta il ritorno del servizio di recupero delle opere d'arte.
 */
public class ArtworkResponse {
    /**
     * Array di bytes corrispondente all'immagine.
     */
    private byte[] artwork;
    /**
     * Nome dell'artwork.
     */
    private String nameArtwork;
    /**
     * Nome dell'artista dell'opera.
     */
    private String nameArtist;

    /**
     * Costruttore di default di ArtworkResponse.
     */
    public ArtworkResponse() {}

    /**
     * Restituisce l'opera d'arte come array di byte.
     *
     * @return l'array di byte dell'opera d'arte
     */
    public byte[] getArtwork() {
        return artwork;
    }

    /**
     * Imposta l'opera d'arte come array di byte.
     *
     * @param artwork l'array di byte dell'opera d'arte da impostare
     */
    public void setArtwork(byte[] artwork) {
        this.artwork = artwork;
    }

    /**
     * Restituisce il nome dell'opera d'arte.
     *
     * @return il nome dell'opera d'arte
     */
    public String getNameArtwork() {
        return nameArtwork;
    }

    /**
     * Imposta il nome dell'opera d'arte.
     *
     * @param nameArtwork il nome dell'opera d'arte da impostare
     */
    public void setNameArtwork(String nameArtwork) {
        this.nameArtwork = nameArtwork;
    }

    /**
     * Restituisce il nome dell'artista.
     *
     * @return il nome dell'artista
     */
    public String getNameArtist() {
        return nameArtist;
    }

    /**
     * Imposta il nome dell'artista.
     *
     * @param nameArtist il nome dell'artista da impostare
     */
    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }
}
