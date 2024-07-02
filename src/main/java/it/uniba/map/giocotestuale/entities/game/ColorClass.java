package it.uniba.map.giocotestuale.entities.game;

import java.util.List;

/**
 * Classe che rappresenta i possibili colori del gioco.
 */
public class ColorClass extends GameObject {
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
        //Placeholder
        return null;
    }
}
