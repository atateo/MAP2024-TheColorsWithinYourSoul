package it.uniba.map.giocotestuale.entities;

import java.util.List;
import java.util.Map;

public class ArtworksResponse {
    private Integer total_count;
    private Links _links;
    private Embedded _embedded;

    // Getters and setters
    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public static class Links {
        private Link self;
        private Link next;

        // Getters and setters
        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }

        public Link getNext() {
            return next;
        }

        public void setNext(Link next) {
            this.next = next;
        }
    }

    public static class Embedded {
        private List<Artwork> artworks;

        // Getters and setters
        public List<Artwork> getArtworks() {
            return artworks;
        }

        public void setArtworks(List<Artwork> artworks) {
            this.artworks = artworks;
        }
    }
}

