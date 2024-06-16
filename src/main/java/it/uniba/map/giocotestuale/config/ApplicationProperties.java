package it.uniba.map.giocotestuale.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Classe delle proprietà dell'applicazione
 */
public class ApplicationProperties {
	private String version;
	private String urlDatabase;
	private String user;
	private String password;
	private boolean setup;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUrlDatabase() {
		return urlDatabase;
	}

	public void setUrlDatabase(String urlDatabase) {
		this.urlDatabase = urlDatabase;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSetup() {
		return setup;
	}

	public void setSetup(boolean setup) {
		this.setup = setup;
	}



	private static ApplicationProperties instance = null;
    
	
	/**
	 * metodo getGiocoTestuale(), carica le proprietà dell'applicazione in avvio
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ApplicationProperties()  {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "application.properties";

		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			setVersion(appProps.getProperty("version"));
			setSetup(Boolean.valueOf(appProps.getProperty("setup")));
			setUrlDatabase(appProps.getProperty("urlDatabase"));
			setUser(appProps.getProperty("user"));
			setPassword(appProps.getProperty("password"));
		}
		catch (IOException  e) {
			// TODO: handle exception
		}
	}
	
	public static synchronized ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }
}