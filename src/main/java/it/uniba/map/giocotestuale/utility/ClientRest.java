package it.uniba.map.giocotestuale.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.uniba.map.giocotestuale.config.ApplicationProperties;

public class ClientRest {
	static ApplicationProperties appProps = ApplicationProperties.getInstance();
	
	/**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    
    private static final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) {
		//String url = "https://api.artsy.net/api/tokens/xapp_token";
		String url = appProps.getUrlToken();
		//String clientID="9f19902f49b14daead3e";
		String clientID=appProps.getClientId();
		//String secret="020b12d3f0ee4c4e00653ea153635b93";
		String secret=appProps.getSecret();
		// TODO Auto-generated method stub
		executePost(url+"?client_id="+clientID+"&client_secret="+secret, "");

	}
	public static String executePost(String targetURL, String urlParameters) {
		HttpURLConnection connection = null;
		String response=null;

		try {
			//Create connection
			URI uri = new URI(targetURL);
			URL url = uri.toURL();
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(urlParameters.getBytes());
			os.flush();
			os.close();
			// For POST only - END

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
}
