package it.uniba.map.giocotestuale.rest;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.entities.Artwork;
import it.uniba.map.giocotestuale.entities.ArtworksResponse;
import it.uniba.map.giocotestuale.entities.Links;

public class ClientRest {
	static ApplicationProperties appProps = ApplicationProperties.getInstance();

	/**
	 * Logger per la registrazione degli eventi.
	 */
	protected static final Logger logger = LogManager.getLogger();

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String URL_TOKEN = "tokens/xapp_token";

	public static void main(String[] args) {
		//String url = "https://api.artsy.net/api/tokens/xapp_token";

		//effettuo la chiamata in post verso il servizio di autenticazione
		String url = appProps.getUrlEndpoint()+URL_TOKEN;
		String clientID=appProps.getClientId();
		String secret=appProps.getSecret();

		String jsonResponse = executePost(url+"?client_id="+clientID+"&client_secret="+secret);

		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		String token = jsonObject.get("token").getAsString();


		//recupero le opere dell'artista
		//picasso 4d8b928b4eb68a1b2c0001f2
		//van gogh 4d8b92944eb68a1b2c000264
		//sandro botticelli 4eaeec71bddaf7000100681b
		//michelangelo buonarroti 4d8b92834eb68a1b2c00019e
		//neruda 0 opere
		//salvador dali 4dadcce67129f059240009df 0 opere

		String urlGetArtworksByArtistId= appProps.getUrlEndpoint()+"artworks?artist_id=4d8b92944eb68a1b2c000264";
		String opereVanGogh= executeGet(urlGetArtworksByArtistId,token);
		Gson gsonOpere = new Gson();
		ArtworksResponse artworksResponse = gsonOpere.fromJson(opereVanGogh, ArtworksResponse.class);
		List<Artwork> opere = artworksResponse.get_embedded().getArtworks();

		Desktop desk=Desktop.getDesktop();
		for (Artwork artwork : opere) {

			String idImmagine = artwork.getId();
			logger.info("Identificativo dell'opera: {}",idImmagine);

			Links links = artwork.getLinks();

			String urlImmagine = links.getImage().getHref();
			String nome = artwork.getTitle();
			urlImmagine = urlImmagine.replace("{image_version}", "large");
			
			byte[] operaDArte = getImage(urlImmagine);
			
			logger.info("Opera d'arte recuperata: {} - url {}",nome,urlImmagine);
			//byte[] operaDArte = recuperaImmagine(urlImmagine); 
			/*try {
				desk.browse(new URI(urlImmagine));
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		/*
		 * String
		 * urlOpere="https://api.artsy.net/api/artworks/4eb077c25fb415000100dd91";
		 * String opereVG= executeGet(urlOpere,token);
		 * 
		 * String json = opereVG; Gson gson = new Gson(); Artwork artwork =
		 * gson.fromJson(json, Artwork.class); System.out.println(artwork.toString());
		 */


	}
	public static String executePost(String targetURL) {
		HttpURLConnection connection = null;
		String response=null;

		try {
			//Create connection
			URI uri = new URI(targetURL);
			URL url = uri.toURL();
			connection = (HttpURLConnection) url.openConnection();


			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestMethod("POST");

			int responseCode = connection.getResponseCode();
			logger.info("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer output = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					output.append(inputLine);
				}
				in.close();

				response= output.toString();
				logger.info("Response: {}",response);
			} else {
				logger.info("POST request did not work.");
			}
		} catch (Exception e) {
			logger.error("Eccezione in fase di invocazione del servizio Artsy:: {}",e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return response;
	}

	public static String executeGet(String targetURL, String token) {
		HttpURLConnection connection = null;
		String response=null;

		try {
			//Create connection
			URI uri = new URI(targetURL);
			URL url = uri.toURL();
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Charset", "application/vnd.artsy-v2+json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("X-XAPP-Token", token);


			connection.setDoOutput(true);

			int responseCode = connection.getResponseCode();
			logger.info("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer output = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					output.append(inputLine);
				}
				in.close();

				response= output.toString();
				logger.info("Response: {}",response);
			} else {
				logger.info("POST request did not work.");
			}
		} catch (Exception e) {
			logger.error("Eccezione in fase di invocazione del servizio Artsy:: {}",e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return response;
	}

	private static byte[] getImage(String imageLink) {
		byte[] image = null;
		try {
			URI uri = new URI(imageLink);
			URL url;

			url = uri.toURL();

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Firefox");

			try (InputStream inputStream = conn.getInputStream()) {
				int n = 0;
				byte[] buffer = new byte[1024];
				while (-1 != (n = inputStream.read(buffer))) {
					output.write(buffer, 0, n);
				}
			}
			image = output.toByteArray();
			//ByteBuffer imageBytes = ByteBuffer.wrap(img);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}


	/*public static byte[] recuperaImmagine(String url) {
		byte [] data = null;
		BufferedImage bImage;
		try {
			bImage = ImageIO.read(new File(url));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", bos );
			data = bos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}*/

}
