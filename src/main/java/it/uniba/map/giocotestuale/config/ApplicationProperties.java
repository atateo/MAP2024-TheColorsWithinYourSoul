package it.uniba.map.giocotestuale.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Singleton class per la gestione delle proprietà dell'applicazione.
 */
public class ApplicationProperties {
	/**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    
    /**
     * Application version.
     */
    private String version;
    
    /**
     * Database URL.
     */
    private String urlDatabase;
    
    /**
     * Database user.
     */
    private String user;
    
    /**
     * Database password.
     */
    private String password;
    
    /**
     * indica se é necessario costruire il  database (solo la prima volta deve 
     * valere true anche se è presente il controllo di esistenza sulla tabella).
     */
    private boolean setup;

    /**
     * Ritorna l'attributo versione dell'applicazione.
     * 
     * @return version la versione dell'applicazione.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta l'attributo versione dell'applicazione.
     * 
     * @param version l'attributo version da impostare.
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * Ritorna l'attributo database URL.
     * 
     * @return database URL.
     */
    public String getUrlDatabase() {
        return urlDatabase;
    }

    /**
     * Imposta l'attributo database URL.
     * 
     * @param urlDatabase l'attributo database URL da impostare.
     */
    public void setUrlDatabase(String urlDatabase) {
        this.urlDatabase = urlDatabase;
    }

    /**
     * Ritorna l'attributo user.
     * 
     * @return database user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Imposta l'attributo user.
     * 
     * @param user l'attributo da impostare.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Ritorna l'attributo password.
     * 
     * @return database password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta l'attributo password.
     * 
     * @param password l'attributo password da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Ritorna il booleano isSetup.
     * 
     * @return true se il setup è richiesto, false altrimenti.
     */
    public boolean isSetup() {
        return setup;
    }

    /**
     * Imposta l'attributo booleano isSetup.
     * 
     * @param setup true se il setup è richiesto, false altrimenti.
     */
    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    /**
     * Singleton instance di ApplicationProperties.
     */
    private static ApplicationProperties instance = null;
    
    /**
     * Costruttorre privato che carica le proprietà dell'applicazione da file.
     */
    private ApplicationProperties() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
            logger.info("Proprietà dell'applicazione caricate correttamente.");
            setVersion(appProps.getProperty("version"));
            setSetup(Boolean.parseBoolean(appProps.getProperty("setup")));
            setUrlDatabase(appProps.getProperty("urlDatabase"));
            setUser(appProps.getProperty("user"));
            setPassword(appProps.getProperty("password"));
        } catch (IOException e) {
            logger.error("Errore in fase di caricamento delle proprietà dell'applicazione: {}",e);
        }
    }
    
    /**
     * Ritorna l'istanza Singleton di ApplicationProperties.
     * Se l'istanza non esiste la crea
     * 
     * @return singleton instance di ApplicationProperties.
     */
    public static synchronized ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }
}
