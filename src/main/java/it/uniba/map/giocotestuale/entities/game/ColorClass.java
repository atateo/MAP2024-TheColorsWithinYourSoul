package it.uniba.map.giocotestuale.entities.game;

import it.uniba.map.giocotestuale.database.impl.ColorDaoImpl;
import it.uniba.map.giocotestuale.type.ColorEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe che rappresenta i possibili colori del gioco.
 */
public class ColorClass extends GameObject implements Serializable {
    /**
     * Istanza del logger.
     */
    private static final Logger logger = LogManager.getLogger(Item.class);
    /**
     * Booleano che indica se il player ha sbloccato o meno questo colore.
     */
    private boolean isUnlocked;

    /**
     * Costruttore con parametri della classe ColorClass. Istanzia tutti i parametri della classe.
     * @param id ID del colore.
     * @param name Nome del colore.
     * @param aliases Alias del colore.
     * @param isUnlocked Booleano che indica se il colore è stato sbloccato o meno.
     */
    public ColorClass(final int id, final String name, final List<String> aliases, final boolean isUnlocked) {
        super(id, name, aliases, null);
        this.isUnlocked = isUnlocked;
    }

    /**
     * Metodo setter per l'attributo isUnlocked.
     * @param unlocked Booleano da impostare come valore di isUnlocked.
     */
    public void setUnlocked(boolean unlocked) {
        this.isUnlocked = unlocked;
    }

    /**
     * Metodo getter per l'attributo isUnlocked.
     * @return Booleano che indica se il colore è stato sbloccato o meno.
     */
    public boolean isUnlocked() {
        return this.isUnlocked;
    }

    /**
     * Restituisce la descrizione del colore presa facendo una query al DB.
     * @return Stringa contenente la descrizione del colore.
     */
    public String getDescriptionFromDB() {
        ColorDaoImpl colorDao = new ColorDaoImpl();

        try {
            return colorDao.getDescrizioneById(super.getId());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return "Strano, neanche io che sono il narratore riesco a descrivere questo colore...";
        }
    }
}