package it.uniba.map.giocotestuale.rest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestClientRest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientRest client = new ClientRest();
		byte[] opera = client.getArtwork();
		System.out.println("opera lunghezza:"+opera.length);
	}

}
