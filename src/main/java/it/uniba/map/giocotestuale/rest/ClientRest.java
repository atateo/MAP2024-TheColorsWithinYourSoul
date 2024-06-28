package it.uniba.map.giocotestuale.rest;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.entities.artwork.Artwork;
import it.uniba.map.giocotestuale.entities.artwork.Links;

public class ClientRest {
	static ApplicationProperties appProps = ApplicationProperties.getInstance();

	/**
	 * Logger per la registrazione degli eventi.
	 */
	protected static final Logger logger = LogManager.getLogger();

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String URL_TOKEN = "tokens/xapp_token";
	private static final String URL_ARTWORK = "artworks/";
	private static final String TOKEN = "token";

	public static byte[] getArtwork() {
		byte[] operaDArte = null;
		
		//effettuo la chiamata in post verso il servizio di autenticazione
		String url = appProps.getUrlEndpoint()+URL_TOKEN;
		String clientID=appProps.getClientId();
		String secret=appProps.getSecret();

		String jsonResponse = executePost(url+"?client_id="+clientID+"&client_secret="+secret);

		//estraggo il token che mi servirà per poter utilizzare il servizio get
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		String token = jsonObject.get(TOKEN).getAsString();

		//estraggo randomicamente l'id dell'opera d'arte da un elenco recuperato in precedenza
		Random random = new Random();
		
		int n = appProps.getIdArtwork().length-1;
		int nRandom = random.nextInt(n + 1);
		
		String idArtwork = appProps.getIdArtwork()[nRandom];
		logger.info("opera d'arte restituita randomicamente n. {} :: {}",nRandom, idArtwork);
		
		//String urlOpere="https://api.artsy.net/api/artworks/"+idArtwork;
		String urlOpere=appProps.getUrlEndpoint()+URL_ARTWORK+idArtwork;
		String jsonOpera= executeGet(urlOpere,token);

		if(jsonOpera!=null && !"".equals(jsonOpera)) {
			Gson gson = new Gson(); 
			Artwork artwork = gson.fromJson(jsonOpera, Artwork.class);
			if(artwork!=null) {
				
				Links links = artwork.getLinks();
				if(links!=null && links.getImage()!=null && links.getImage().getHref()!=null) {
					String urlImmagine = links.getImage().getHref().replace("{image_version}", "large");
					//urlImmagine = urlImmagine.replace("{image_version}", "large");
					operaDArte = getImage(urlImmagine);
				}
			}
		}
		return operaDArte;
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
			logger.info("Response Code metodo POST:: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer output = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					output.append(inputLine);
				}
				in.close();

				response= output.toString();
				logger.info("Response ottenuta con successo: {}",response.substring(0,40)+"************}");
			} else {
				logger.info("Chiamata POST errata.");
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
			logger.info("Response Code metodo GET:: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer output = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					output.append(inputLine);
				}
				in.close();

				response= output.toString();
				logger.info("Response ottenuta con successo: {}",response.substring(0,40)+"************}");
			} else {
				logger.info("Chiamata GET errata.");
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
		} catch (URISyntaxException | IOException e) {
			logger.error("Eccezione in fase di recupero del file dell'opera d'arte: {}",e);
			e.printStackTrace();
		}
		return image;
	}
}
