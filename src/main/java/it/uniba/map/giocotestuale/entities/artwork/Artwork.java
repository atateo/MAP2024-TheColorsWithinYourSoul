package it.uniba.map.giocotestuale.entities.artwork;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
* Classe per la gestione di Artwork (opera d'arte).
* Questa classe utilizza annotazioni Gson per la serializzazione e deserializzazione JSON.
*/
public class Artwork {
	/**
     * identificativo dell'opera d'arte.
     */
    private String id;
    
    /**
     * Nome dell'opera d'arte nel sistema.
     */
    private String slug;
    
    /**
     * data registrazione nel sistema ArtSy.
     */
    @SerializedName("created_at")
    private String createdAt;
    
    /**
     * data ultimo aggiornamento nel sistema ArtSy.
     */
    @SerializedName("updated_at")
    private String updatedAt;
    
    /**
     * titolo dell'opera d'arte.
     */
    private String title;
    
    /**
     * categoria dell'opera d'arte.
     */
    private String category;
    
    /**
     * tipo opera d'arte.
     */
    private String medium;
    
    /**
     * anno realizzazione opera reale.
     */
    private String date;
    
    /**
     * oggetto che definisce le dimensioni dell'opera d'arte.
     */
    private Dimensions dimensions;
    
    /**
     * boolean che indica se l'opera d'arte è stata pubblicata.
     */
    private boolean published;
    
    /**
     * sito web di riferimento dell'opera d'arte.
     */
    private String website;
    
    /**
     * firma dell'opera d'arte.
     */
    private String signature;
    
    /**
     * serie dell'opera d'arte.
     */
    private String series;
    
    /**
     * provenienza dell'opera d'arte.
     */
    private String provenance;
    
    /**
     * genere dell'opera d'arte.
     */
    private String literature;
    
    /**
     * storia delle mostre inerenti all'opera d'arte.
     */
    @SerializedName("exhibition_history")
    private String exhibitionHistory;
    
    /**
     * struttura presso cui é esposta l'opera d'arte.
     */
    @SerializedName("collecting_institution")
    private String collectingInstitution;
    
    /**
     * informazioni addizionali sull'opera d'arte.
     */
    @SerializedName("additional_information")
    private String additionalInformation;
    
    /**
     * ente detentore dei diritti di immagine dell'opera d'arte.
     */
    @SerializedName("image_rights")
    private String imageRights;
    
    /**
     * recensione dell'opera d'arte.
     */
    private String blurb;
    
    /**
     * boolean unique.
     */
    private boolean unique;
    
    /**
     * attributo cultural maker.
     */
    @SerializedName("cultural_maker")
    private String culturalMaker;
    
    /**
     * attributo iconicity.
     */
    private double iconicity;
    
    /**
     * boolean can inquire.
     */
    @SerializedName("can_inquire")
    private boolean canInquire;
    
    /**
     * boolean can acquire.
     */
    @SerializedName("can_acquire")
    private boolean canAcquire;
    
    /**
     * boolean can share.
     */
    @SerializedName("can_share")
    private boolean canShare;
    
    /**
     * messaggio di vendita.
     */
    @SerializedName("sale_message")
    private String saleMessage;
    
    /**
     * boolean sold.
     */
    private boolean sold;
    
    /**
     * livello di visibilità.
     */
    @SerializedName("visibility_level")
    private String visibilityLevel;
    
    /**
     * Lista di stringhe rappresentanti i vari formati di immagine disponibili.
     */
    @SerializedName("image_versions")
    private List<String> imageVersions;
    
    /**
     * oggetto Links che definisce un insieme di link di riferimento dell'opera d'arte.
     */
    @SerializedName("_links")
    private Links links;
    
    /**
     * oggetto Embedded.
     */
    @SerializedName("_embedded")
    private Embedded embedded;

    /**
     * Restituisce l'ID dell'opera d'arte.
     * @return l'ID dell'opera d'arte.
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo setter per l'ID di Artwork.
     * @param id dell'opera d'arte.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Restituisce l'attributo slug.
     * @return lo slug dell'opera d'arte.
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Metodo setter per lo slug dell'opera d'arte.
     * @param slug id dello slug dell'opera d'arte.
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Metodo getter per la data creazione dell'opera d'arte nel sistema.
     * @return la data creazione dell'opera d'arte nel sistema.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Metodo setter per la data creazione dell'opera d'arte.
     * @param createdAt la data creazione dell'opera d'arte.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Metodo getter per la data di aggiornamento dell'opera d'arte nel sistema.
     * @return la data di aggiornamento dell'opera d'arte nel sistema.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Metodo setter per la data di aggiornamento dell'opera d'arte.
     * @param updatedAt data di aggiornamento dell'opera d'arte.
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Metodo getter per il titolo dell'opera d'arte nel sistema.
     * @return il titolo dell'opera d'arte nel sistema.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Metodo setter per il titolo dell'opera d'arte.
     * @param title il titolo dell'opera d'arte.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metodo getter per la categoria dell'opera d'arte nel sistema.
     * @return la categoria dell'opera d'arte nel sistema.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Metodo setter per la categoria dell'opera d'arte.
     * @param category la categoria dell'opera d'arte.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Metodo getter per il tipo dell'opera d'arte nel sistema.
     * @return il tipo dell'opera d'arte nel sistema.
     */
    public String getMedium() {
        return medium;
    }

    /**
     * Metodo setter per il tipo dell'opera d'arte.
     * @param medium il tipo dell'opera d'arte.
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    /**
     * Metodo getter per l'anno di realizzazione dell'opera d'arte nel sistema.
     * @return l'anno di realizzazione dell'opera d'arte nel sistema.
     */
    public String getDate() {
        return date;
    }

    /**
     * Metodo setter per l'anno di realizzazione dell'opera d'arte.
     * @param date l'anno di realizzazione dell'opera d'arte.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Metodo getter per l'oggetto Dimensione dell'opera d'arte nel sistema.
     * @return l'oggetto Dimensione dell'opera d'arte nel sistema.
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Metodo setter per l'oggetto Dimensione dell'opera d'arte.
     * @param dimensions l'oggetto Dimensione dell'opera d'arte.
     */
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Metodo getter per il booleano isPublished
     * @return l'attributo booleano isPublished,  true se l'opera è pubblicata, false altrimenti.
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Metodo setter per il booleano isPublished
     * @param published l'attributo booleano isPublished,  true se l'opera è pubblicata, false altrimenti.
     */
    public void setPublished(boolean published) {
        this.published = published;
    }

    /**
    * Metodo getter per il sito web di riferimento dell'opera d'arte.
    * @return il sito web di riferimento dell'opera d'arte.
    */
    public String getWebsite() {
        return website;
    }

    /**
     * Metodo setter per il sito web di riferimento dell'opera d'arte.
     * @param website il sito web di riferimento dell'opera d'arte.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Metodo getter per la firma dell'opera d'arte.
     * @return la firma dell'opera d'arte.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Metodo setter per la firma dell'opera d'arte.
     * @param signature la firma dell'opera d'arte.
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Metodo getter per la serie dell'opera d'arte.
     * @return la serie dell'opera d'arte.
     */
    public String getSeries() {
        return series;
    }

    /**
     * Metodo setter per la serie dell'opera d'arte.
     * @param series la serie dell'opera d'arte.
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * Metodo getter per la provenienza dell'opera d'arte.
     * @return la provenienza dell'opera d'arte.
     */
    public String getProvenance() {
        return provenance;
    }

    /**
     * Metodo setter per la provenienza dell'opera d'arte.
     * @param provenance la provenienza dell'opera d'arte.
     */
    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    /**
     * Metodo getter per il genere(literature) dell'opera d'arte.
     * @return la provenienza dell'opera d'arte.
     */
    public String getLiterature() {
        return literature;
    }

    /**
     * Metodo setter per il genere(literature) dell'opera d'arte.
     * @param literature la provenienza dell'opera d'arte.
     */
    public void setLiterature(String literature) {
        this.literature = literature;
    }

    /**
     * Metodo getter per la storia delle esposizioni dell'opera d'arte.
     * @return la storia delle esposizioni dell'opera d'arte.
     */
    public String getExhibitionHistory() {
        return exhibitionHistory;
    }

    /**
     * Metodo setter per la storia delle esposizioni dell'opera d'arte.
     * @param exhibitionHistory la storia delle esposizioni dell'opera d'arte.
     */
    public void setExhibitionHistory(String exhibitionHistory) {
        this.exhibitionHistory = exhibitionHistory;
    }

    /**
     * Metodo getter per la struttura presso cui è esposta l'opera d'arte.
     * @return la struttura presso cui è esposta l'opera d'arte.
     */
    public String getCollectingInstitution() {
        return collectingInstitution;
    }

    /**
     * Metodo setter per la struttura presso cui è esposta l'opera d'arte.
     * @param collectingInstitution la struttura presso cui è esposta l'opera d'arte.
     */
    public void setCollectingInstitution(String collectingInstitution) {
        this.collectingInstitution = collectingInstitution;
    }

    /**
     * Metodo getter per le informazioni aggiuntive dell'opera d'arte.
     * @return le informazioni aggiuntive dell'opera d'arte.
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Metodo setter per le informazioni aggiuntive dell'opera d'arte.
     * @param additionalInformation le informazioni aggiuntive dell'opera d'arte.
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * Metodo getter per i diritti di immagine dell'opera d'arte.
     * @return i diritti di immagine dell'opera d'arte.
     */
    public String getImageRights() {
        return imageRights;
    }

    /**
     * Metodo setter per i diritti di immagine dell'opera d'arte.
     * @param imageRights i diritti di immagine dell'opera d'arte.
     */
    public void setImageRights(String imageRights) {
        this.imageRights = imageRights;
    }

    /**
     * Metodo getter per la recensione dell'opera d'arte.
     * @return la recensione dell'opera d'arte.
     */
    public String getBlurb() {
        return blurb;
    }

    /**
     * Metodo setter per la recensione dell'opera d'arte.
     * @param blurb la recensione dell'opera d'arte.
     */
    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }
    
    /**
     * Metodo getter per il booleano isUnique
     * @return l'attributo booleano isUnique,  true se l'opera è unica, false altrimenti.
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * Metodo setter per il booleano isUnique
     * @param unique l'attributo booleano isUnique,  true se l'opera è unica, false altrimenti.
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * Metodo getter per l'attributo cultural maker.
     * @return l'attributo cultural maker.
     */
    public String getCulturalMaker() {
        return culturalMaker;
    }

    /**
     * Metodo setter per l'attributo cultural maker.
     * @param culturalMaker l'attributo cultural maker.
     */
    public void setCulturalMaker(String culturalMaker) {
        this.culturalMaker = culturalMaker;
    }

    /**
     * Metodo getter per l'iconicità dell'opera d'arte.
     * @return l'iconicità dell'opera d'arte.
     */
    public double getIconicity() {
        return iconicity;
    }

    /**
     * Metodo setter per l'iconicità dell'opera d'arte.
     * @param iconicity l'iconicità dell'opera d'arte.
     */
    public void setIconicity(double iconicity) {
        this.iconicity = iconicity;
    }

    /**
     * Metodo getter per il booleano isCanInquire
     * @return l'attributo booleano isCanInquire.
     */
    public boolean isCanInquire() {
        return canInquire;
    }

    /**
     * Metodo setter per il booleano isCanInquire
     * @param canInquire l'attributo booleano isCanInquire.
     */
    public void setCanInquire(boolean canInquire) {
        this.canInquire = canInquire;
    }

    /**
     * Metodo getter per il booleano isCanAcquire
     * @return l'attributo booleano isCanAcquire.
     */
    public boolean isCanAcquire() {
        return canAcquire;
    }

    /**
     * Metodo setter per il booleano isCanAcquire
     * @param canAcquire l'attributo booleano isCanAcquire.
     */
    public void setCanAcquire(boolean canAcquire) {
        this.canAcquire = canAcquire;
    }

    /**
     * Metodo getter per il booleano isCanShare
     * @return l'attributo booleano isCanShare.
     */
    public boolean isCanShare() {
        return canShare;
    }

    /**
     * Metodo setter per il booleano isCanShare
     * @param canShare l'attributo booleano isCanShare.
     */
    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    /**
     * Metodo getter per il messaggio promozionale.
     * @return il messaggio promozionale.
     */
    public String getSaleMessage() {
        return saleMessage;
    }

    /**
     * Metodo setter per il messaggio promozionale.
     * @param saleMessage il messaggio promozionale.
     */
    public void setSaleMessage(String saleMessage) {
        this.saleMessage = saleMessage;
    }

    /**
     * Metodo getter per il booleano isSold
     * @return l'attributo booleano isSold.
     */
    public boolean isSold() {
        return sold;
    }
    
    /**
     * Metodo setter per il booleano isSold
     * @param sold l'attributo booleano isSold.
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**
     * Metodo getter per il livello di visibilità.
     * @return il livello di visibilità.
     */
    public String getVisibilityLevel() {
        return visibilityLevel;
    }

    /**
     * Metodo setter per il livello di visibilità.
     * @param visibilityLevel il livello di visibilità.
     */
    public void setVisibilityLevel(String visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }

    /**
     * Metodo getter per la lista dei formati immagine.
     * @return la lista in formato stringa dei formati gestiti per l'immagine.
     */
    public List<String> getImageVersions() {
        return imageVersions;
    }

    /**
     * Metodo setter per la lista dei formati immagine.
     * @param imageVersions la lista in formato stringa dei formati gestiti per l'immagine.
     */
    public void setImageVersions(List<String> imageVersions) {
        this.imageVersions = imageVersions;
    }

    /**
     * Metodo getter per l'oggetto Links.
     * @return la lista dei link di riferimento dell'opera d'arte strutturata secondo l'oggetto Links.
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Metodo setter per l'oggetto Links.
     * @param links la lista dei link di riferimento dell'opera d'arte strutturata secondo l'oggetto Links.
     */
    public void setLinks(Links links) {
        this.links = links;
    }
    
    /**
     * Metodo getter per l'oggetto Embedded.
     * @return l'oggetto Embedded (eventuali informazioni circa l'edizione dell'opera d'arte). .
     */
    public Embedded getEmbedded() {
        return embedded;
    }

    /**
     * Metodo setter per l'oggetto Embedded.
     * @param embedded l'oggetto Embedded (eventuali informazioni circa l'edizione dell'opera d'arte). .
     */
    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }
}

/**
* Inner Class per la gestione del tipo Embedded di Artwork.
* Definisce la lista di oggetti che costituiscono le edizioni.
*/
class Embedded {
	/**
     * Lista di oggetti generici rappresentanti le edizioni dell'opera d'arte.
     */
    private List<Object> editions;

    /**
     * Metodo getter per la lista degli oggetti che definiscono le edizioni.
     * @return la lista in formato stringa dei formati gestiti per l'immagine.
     */
    public List<Object> getEditions() {
        return editions;
    }

    /**
     * Metodo setter per la lista degli oggetti che definiscono le edizioni.
     * @param editions la lista in formato stringa dei formati gestiti per l'immagine.
     */
    public void setEditions(List<Object> editions) {
        this.editions = editions;
    }
}
