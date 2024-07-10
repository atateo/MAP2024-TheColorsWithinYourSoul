package it.uniba.map.giocotestuale.entities.artwork;

import com.google.gson.annotations.SerializedName;

/**
 * Classe per la gestione dei link inerenti ad Artwork (opera d'arte).
 */
public class Links {

    /**
     * Links di tipo thumbnail.
     */
    private Link thumbnail;

    /**
     * Links dell'immagine.
     */
    private Link image;

    /**
     * Links partner.
     */
    private Link partner;

    /**
     * Links dell'api utilizzata.
     */
    private Link self;

    /**
     * Links di tipo permalink.
     */
    private Link permalink;

    /**
     * Links del genere dell'immagine.
     */
    private Link genes;

    /**
     * Links API all'artista.
     */
    private Link artists;

    /**
     * Links opere simili.
     */
    @SerializedName("similar_artworks")
    private Link similarArtworks;

    /**
     * Links delle collezioni utente.
     */
    @SerializedName("collection_users")
    private Link collectionUsers;

    /**
     * Links vendita.
     */
    @SerializedName("sale_artworks")
    private Link saleArtworks;

    /**
     * Costruttore di default di Links.
     */
    public Links() {}

    /**
     * Metodo getter per Link di tipo thumbnail.
     *
     * @return Link di tipo thumbnail.
     */
    public Link getThumbnail() {
        return thumbnail;
    }

    /**
     * Metodo setter per Link di tipo thumbnail.
     *
     * @param thumbnail Link di tipo thumbnail.
     */
    public void setThumbnail(Link thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Metodo getter per Link dell'immagine.
     *
     * @return Link dell'immagine.
     */
    public Link getImage() {
        return image;
    }

    /**
     * Metodo setter per Link dell'immagine.
     *
     * @param image Link dell'immagine.
     */
    public void setImage(Link image) {
        String immagine = image.getHref().replace("{image_version}", "large");
        image.setHref(immagine);
        this.image = image;
    }

    /**
     * Metodo getter per Link dei partner.
     *
     * @return Link dei partner.
     */
    public Link getPartner() {
        return partner;
    }

    /**
     * Metodo setter per Link dei partner.
     *
     * @param partner Link dei partner.
     */
    public void setPartner(Link partner) {
        this.partner = partner;
    }

    /**
     * Metodo getter per Link all'api di origine.
     *
     * @return Link all'api di origine.
     */
    public Link getSelf() {
        return self;
    }

    /**
     * Metodo setter per Link all'api di origine.
     *
     * @param self Link all'api di origine.
     */
    public void setSelf(Link self) {
        this.self = self;
    }

    /**
     * Metodo getter per Link di tipo Permalink.
     *
     * @return Link di tipo Permalink.
     */
    public Link getPermalink() {
        return permalink;
    }

    /**
     * Metodo setter per Link di tipo Permalink.
     *
     * @param permalink Link di tipo Permalink.
     */
    public void setPermalink(Link permalink) {
        this.permalink = permalink;
    }

    /**
     * Metodo getter per Link al genere.
     *
     * @return Link al genere.
     */
    public Link getGenes() {
        return genes;
    }

    /**
     * Metodo setter per Link al genere.
     *
     * @param genes Link al genere.
     */
    public void setGenes(Link genes) {
        this.genes = genes;
    }

    /**
     * Metodo getter per Link degli agli artisti.
     *
     * @return Link degli agli artisti.
     */
    public Link getArtists() {
        return artists;
    }

    /**
     * Metodo setter per Link degli agli artisti.
     *
     * @param artists Link degli agli artisti.
     */
    public void setArtists(Link artists) {
        this.artists = artists;
    }

    /**
     * Metodo getter per Link opere simili.
     *
     * @return Link opere simili.
     */
    public Link getSimilarArtworks() {
        return similarArtworks;
    }

    /**
     * Metodo setter per Link opere simili.
     *
     * @param similarArtworks Link opere simili.
     */
    public void setSimilarArtworks(Link similarArtworks) {
        this.similarArtworks = similarArtworks;
    }

    /**
     * Metodo getter per Link delle collezioni.
     *
     * @return Link delle collezioni.
     */
    public Link getCollectionUsers() {
        return collectionUsers;
    }

    /**
     * Metodo setter per Link delle collezioni.
     *
     * @param collectionUsers Link delle collezioni.
     */
    public void setCollectionUsers(Link collectionUsers) {
        this.collectionUsers = collectionUsers;
    }

    /**
     * Metodo getter per Link della vendita.
     *
     * @return Link della vendita.
     */
    public Link getSaleArtworks() {
        return saleArtworks;
    }

    /**
     * Metodo setter per Link della vendita.
     *
     * @param saleArtworks Link della vendita.
     */
    public void setSaleArtworks(Link saleArtworks) {
        this.saleArtworks = saleArtworks;
    }
}
