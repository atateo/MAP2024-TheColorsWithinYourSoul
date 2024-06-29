package it.uniba.map.giocotestuale.rest;

public class TestClientRest {

	public static void main(String[] args) {
		byte[] opera = ClientRest.getArtwork();
		System.out.println("opera lunghezza:"+opera.length);
	}

}
