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
import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.uniba.map.giocotestuale.config.ApplicationProperties;
import it.uniba.map.giocotestuale.entities.artwork.Artwork;
import it.uniba.map.giocotestuale.entities.artwork.ArtworkResponse;
import it.uniba.map.giocotestuale.entities.artwork.Links;

/**
 * La classe ClientRest fornisce metodi per interagire con servizi RESTful.
 */
public class ClientRest {
    static ApplicationProperties appProps = ApplicationProperties.getInstance();

    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    /**
     *
     */
    private static final String USER_AGENT = "Mozilla/5.0";
    /**
     *
     */
    private static final String URL_TOKEN = "tokens/xapp_token";
    /**
     *
     */
    private static final String URL_ARTWORK = "artworks/";
    /**
     *
     */
    private static final String TOKEN = "token";

    /**
     * Costruttore di default di ClientRest.
     */
    public ClientRest() {}

    /**
     * Recupera un'opera d'arte come array di byte.
     *
     * @return l'opera d'arte come array di byte
     */
    public static ArtworkResponse getArtwork() {
        ArtworkResponse artworkResponse = new ArtworkResponse();
        byte[] operaDArte;
        String nameArtwork;

        // Predispone l'endPoint per l'api di autenticazione
        String url = appProps.getUrlEndpoint() + URL_TOKEN;
        String clientID = appProps.getClientId();
        String secret = appProps.getSecret();

        //esegue la chiamata in POST verso il servizio di autenticazione
        String jsonResponse = executePost(url + "?client_id=" + clientID + "&client_secret=" + secret);

        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            // Estrae il token che sarà utilizzato per il servizio GET
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            String token = jsonObject.get(TOKEN).getAsString();

            // Estrae randomicamente l'id dell'opera d'arte da un elenco predefinito
            Random random = new Random();
            int n = appProps.getIdArtwork().length - 1;
            int nRandom = random.nextInt(n + 1);

            String idArtwork = appProps.getIdArtwork()[nRandom];
            logger.info("Opera d'arte restituita randomicamente n. {} :: {}", nRandom, idArtwork);

            String urlOpere = appProps.getUrlEndpoint() + URL_ARTWORK + idArtwork;
            String jsonOpera = executeGet(urlOpere, token);

            if (jsonOpera != null && !jsonOpera.isEmpty()) {
                Gson gson = new Gson();
                Artwork artwork = gson.fromJson(jsonOpera, Artwork.class);
                if (artwork != null) {

                    Links links = artwork.getLinks();
                    if (links != null) {
                        if (links.getImage() != null && links.getImage().getHref() != null) {
                            String urlImmagine = links.getImage().getHref().replace("{image_version}", "large");
                            operaDArte = getImage(urlImmagine);
                            nameArtwork = artwork.getTitle();
                            artworkResponse.setArtwork(operaDArte);
                            artworkResponse.setNameArtwork(nameArtwork);
                        }
                        if (links.getArtists() != null && links.getArtists().getHref() != null) {
                            String urlArtista = links.getArtists().getHref();
                            String jsonArtista = executeGet(urlArtista, token);
                            if (jsonArtista != null && !jsonArtista.isEmpty()) {
                                artworkResponse.setNameArtist(getNameArtist(jsonArtista));
                            }
                        }
                    } else {
                        artworkResponse.setNameArtwork("Opera d'arte non più disponibile");
                    }
                }
            }
        }

        //ritorna l'opera d'arte
        return artworkResponse;
    }

    /**
     * Esegue una richiesta POST verso l'URL specificato.
     *
     * @param targetURL l'URL di destinazione
     * @return la risposta come stringa
     */
    public static String executePost(String targetURL) {
        HttpURLConnection connection = null;
        String response = null;

        try {
            // Crea la connessione
            URI uri = new URI(targetURL);
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("POST");

            int responseCode = connection.getResponseCode();
            logger.info("Response Code metodo POST: {}", responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                //StringBuffer output = new StringBuffer();
                StringBuilder output = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    output.append(inputLine);
                }
                in.close();

                response = output.toString();
                logger.info("Response ottenuta con successo: {}", response.substring(0, 40) + "************}");
            } else {
                logger.info("Chiamata POST errata.");
            }
        } catch (Exception e) {
            logger.error("Eccezione in fase di invocazione del servizio: ", e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    /**
     * Esegue una richiesta GET verso l'URL specificato con il token fornito.
     *
     * @param targetURL l'URL di destinazione
     * @param token     il token di autenticazione
     * @return la risposta come stringa
     */
    public static String executeGet(String targetURL, String token) {
        HttpURLConnection connection = null;
        String response = null;

        try {
            // Crea la connessione
            URI uri = new URI(targetURL);
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "application/vnd.artsy-v2+json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-XAPP-Token", token);

            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            logger.info("Response Code metodo GET: {}", responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder output = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    output.append(inputLine);
                }
                in.close();

                response = output.toString();
                logger.info("Response ottenuta con successo: {}", response.substring(0, 40) + "************}");
            } else {
                logger.info("Chiamata GET errata.");
            }
        } catch (Exception e) {
            logger.error("Eccezione in fase di invocazione del servizio: ", e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    /**
     * Recupera un'immagine da un URL specificato.
     *
     * @param imageLink il link dell'immagine
     * @return l'immagine come array di byte
     */
    private static byte[] getImage(String imageLink) {
        byte[] image = null;
        try {
            URI uri = new URI(imageLink);
            URL url = uri.toURL();

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
            logger.error("Eccezione in fase di recupero del file dell'immagine: ", e);
        }
        return image;
    }

    /**
     * Recupera il nome dell'artista dal json ottenuto dalla chiamata.
     *
     * @param jsonString la stringa contenente i dati restituiti dall'api
     * @return il nome dell'artista
     */
    private static String getNameArtist(String jsonString) {
        /*questo metodo usa volutamente la logica di recupero dell'attributo mediante espressione lambda,
         * avendo già utilizzato negli altri metodi la logica di binding attuata tramite Gson
         */

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        // Uso una lambda per recuperare il nome dell'artista dalla struttura del json fornito in input
        Optional<String> name = Optional.ofNullable(jsonObject)
                .map(obj -> obj.getAsJsonObject("_embedded"))
                .map(embedded -> embedded.getAsJsonArray("artists"))
                .flatMap(artists -> !artists.isEmpty() ? Optional.of(artists.get(0).getAsJsonObject()) : Optional.empty())
                .map(artist -> artist.get("name").getAsString());

        // Ritorna il nome dell'artista o null se non riesce a recuperarlo
        return name.orElse("Artista sconosciuto");
    }
}
