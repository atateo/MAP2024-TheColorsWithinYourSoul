package it.uniba.map.giocotestuale.entities.artwork;

import com.google.gson.annotations.SerializedName;

public class Links {

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
}
