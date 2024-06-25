package it.uniba.map.giocotestuale.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		String jsonToken = executePost(url+"?client_id="+clientID+"&client_secret="+secret, "");
		
		JsonObject jsonObject = JsonParser.parseString(jsonToken).getAsJsonObject();
		String token = jsonObject.get("token").getAsString();
		String url2="https://api.artsy.net/api/artists/vincent-van-gogh";
		String vangogh= executePost(url2,token);
		
		JsonObject jsonObject2 = JsonParser.parseString(vangogh)
			    .getAsJsonObject();
		JsonArray artworks = (JsonArray) jsonObject2.get("artworks");
		

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
			//connection.setRequestProperty("Authorization", "X-XAPP-Token eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IiIsInN1YmplY3RfYXBwbGljYXRpb24iOiI1YjdiNjY4OS03MjMwLTQwMzEtOWNmYi1kOTdkZTJiZDk5ZDkiLCJleHAiOjE3MTk3NzM1NjAsImlhdCI6MTcxOTE2ODc2MCwiYXVkIjoiNWI3YjY2ODktNzIzMC00MDMxLTljZmItZDk3ZGUyYmQ5OWQ5IiwiaXNzIjoiR3Jhdml0eSIsImp0aSI6IjY2Nzg2ZWY4ZTAyYjZkMDAwZjZmNDAzNyJ9.yKapRhASqce9Ddc12MmbvwdDJCdt1FQveizcgRFwNjY");
			if(urlParameters!=null && !urlParameters.equals(""))
				connection.setRequestProperty("X-XAPP-Token", urlParameters);
			
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
