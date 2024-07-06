package it.uniba.map.giocotestuale.rest;

import it.uniba.map.giocotestuale.entities.artwork.ArtworkResponse;

public class TestClientRest {

	public static void main(String[] args) {
		ArtworkResponse artworkResponse = ClientRest.getArtwork();

		byte[] opera = artworkResponse.getArtwork();
		System.out.println("Opera d'arte "+artworkResponse.getNameArtwork()+" :: lunghezza:"+opera.length);
	}

}
