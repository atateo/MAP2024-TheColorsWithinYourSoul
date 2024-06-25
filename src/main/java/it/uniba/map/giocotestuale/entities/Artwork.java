package it.uniba.map.giocotestuale.entities;

/**
 * @author tateo.antimo
 *
 */
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Artwork {
    private String id;
    private String slug;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private String title;
    private String category;
    private String medium;
    private String date;
    private Dimensions dimensions;
    private boolean published;
    private String website;
    private String signature;
    private String series;
    private String provenance;
    private String literature;
    @SerializedName("exhibition_history")
    private String exhibitionHistory;
    @SerializedName("collecting_institution")
    private String collectingInstitution;
    @SerializedName("additional_information")
    private String additionalInformation;
    @SerializedName("image_rights")
    private String imageRights;
    private String blurb;
    private boolean unique;
    @SerializedName("cultural_maker")
    private String culturalMaker;
    private double iconicity;
    @SerializedName("can_inquire")
    private boolean canInquire;
    @SerializedName("can_acquire")
    private boolean canAcquire;
    @SerializedName("can_share")
    private boolean canShare;
    @SerializedName("sale_message")
    private String saleMessage;
    private boolean sold;
    @SerializedName("visibility_level")
    private String visibilityLevel;
    @SerializedName("image_versions")
    private List<String> imageVersions;
    @SerializedName("_links")
    private Links links;
    @SerializedName("_embedded")
    private Embedded embedded;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getLiterature() {
        return literature;
    }

    public void setLiterature(String literature) {
        this.literature = literature;
    }

    public String getExhibitionHistory() {
        return exhibitionHistory;
    }

    public void setExhibitionHistory(String exhibitionHistory) {
        this.exhibitionHistory = exhibitionHistory;
    }

    public String getCollectingInstitution() {
        return collectingInstitution;
    }

    public void setCollectingInstitution(String collectingInstitution) {
        this.collectingInstitution = collectingInstitution;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getImageRights() {
        return imageRights;
    }

    public void setImageRights(String imageRights) {
        this.imageRights = imageRights;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getCulturalMaker() {
        return culturalMaker;
    }

    public void setCulturalMaker(String culturalMaker) {
        this.culturalMaker = culturalMaker;
    }

    public double getIconicity() {
        return iconicity;
    }

    public void setIconicity(double iconicity) {
        this.iconicity = iconicity;
    }

    public boolean isCanInquire() {
        return canInquire;
    }

    public void setCanInquire(boolean canInquire) {
        this.canInquire = canInquire;
    }

    public boolean isCanAcquire() {
        return canAcquire;
    }

    public void setCanAcquire(boolean canAcquire) {
        this.canAcquire = canAcquire;
    }

    public boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    public String getSaleMessage() {
        return saleMessage;
    }

    public void setSaleMessage(String saleMessage) {
        this.saleMessage = saleMessage;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getVisibilityLevel() {
        return visibilityLevel;
    }

    public void setVisibilityLevel(String visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }

    public List<String> getImageVersions() {
        return imageVersions;
    }

    public void setImageVersions(List<String> imageVersions) {
        this.imageVersions = imageVersions;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }
}

/*
 * class Dimensions { private In in; private Cm cm;
 * 
 * // Getters and setters
 * 
 * public In getIn() { return in; }
 * 
 * public void setIn(In in) { this.in = in; }
 * 
 * public Cm getCm() { return cm; }
 * 
 * public void setCm(Cm cm) { this.cm = cm; } }
 */

/*
 * class In { private String text; private double height; private double width;
 * private Object depth; // Can be null private Object diameter; // Can be null
 * 
 * // Getters and setters
 * 
 * public String getText() { return text; }
 * 
 * public void setText(String text) { this.text = text; }
 * 
 * public double getHeight() { return height; }
 * 
 * public void setHeight(double height) { this.height = height; }
 * 
 * public double getWidth() { return width; }
 * 
 * public void setWidth(double width) { this.width = width; }
 * 
 * public Object getDepth() { return depth; }
 * 
 * public void setDepth(Object depth) { this.depth = depth; }
 * 
 * public Object getDiameter() { return diameter; }
 * 
 * public void setDiameter(Object diameter) { this.diameter = diameter; } }
 */

/*
 * class Cm { private String text; private double height; private double width;
 * private Object depth; // Can be null private Object diameter; // Can be null
 * 
 * // Getters and setters
 * 
 * public String getText() { return text; }
 * 
 * public void setText(String text) { this.text = text; }
 * 
 * public double getHeight() { return height; }
 * 
 * public void setHeight(double height) { this.height = height; }
 * 
 * public double getWidth() { return width; }
 * 
 * public void setWidth(double width) { this.width = width; }
 * 
 * public Object getDepth() { return depth; }
 * 
 * public void setDepth(Object depth) { this.depth = depth; }
 * 
 * public Object getDiameter() { return diameter; }
 * 
 * public void setDiameter(Object diameter) { this.diameter = diameter; } }
 */

/*class Links {
    private Link thumbnail;
    private Link image;
    private Link partner;
    private Link self;
    private Link permalink;
    private Link genes;
    private Link artists;
    @SerializedName("similar_artworks")
    private Link similarArtworks;
    @SerializedName("collection_users")
    private Link collectionUsers;
    @SerializedName("sale_artworks")
    private Link saleArtworks;

    // Getters and setters

    public Link getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Link thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Link getImage() {
        return image;
    }

    public void setImage(Link image) {
    	String immagine = image.getHref().replace("{image_version}", "large");
    	image.setHref(immagine);
        this.image = image;
    }

    public Link getPartner() {
        return partner;
    }

    public void setPartner(Link partner) {
        this.partner = partner;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getPermalink() {
        return permalink;
    }

    public void setPermalink(Link permalink) {
        this.permalink = permalink;
    }

    public Link getGenes() {
        return genes;
    }

    public void setGenes(Link genes) {
        this.genes = genes;
    }

    public Link getArtists() {
        return artists;
    }

    public void setArtists(Link artists) {
        this.artists = artists;
    }

    public Link getSimilarArtworks() {
        return similarArtworks;
    }

    public void setSimilarArtworks(Link similarArtworks) {
        this.similarArtworks = similarArtworks;
    }

    public Link getCollectionUsers() {
        return collectionUsers;
    }

    public void setCollectionUsers(Link collectionUsers) {
        this.collectionUsers = collectionUsers;
    }

    public Link getSaleArtworks() {
        return saleArtworks;
    }

    public void setSaleArtworks(Link saleArtworks) {
        this.saleArtworks = saleArtworks;
    }
}*/

//class Link {
//    private String href;
//    private boolean templated;  // This field is only present in some cases
//
//    // Getters and setters
//
//    public String getHref() {
//        return href.replace("", "large");
//    }
//
//    public void setHref(String href) {
//        this.href = href;
//    }
//
//    public boolean isTemplated() {
//        return templated;
//    }
//
//    public void setTemplated(boolean templated) {
//        this.templated = templated;
//    }
//}

class Embedded {
    private List<Object> editions;

    // Getters and setters

    public List<Object> getEditions() {
        return editions;
    }

    public void setEditions(List<Object> editions) {
        this.editions = editions;
    }
}
