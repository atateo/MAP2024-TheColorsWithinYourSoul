package it.uniba.map.giocotestuale.entities.game;

import it.uniba.map.giocotestuale.database.impl.ItemDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe che rappresenta i vari item di gioco. Questa classe include gli oggetti che si trovano nelle stanze
 * e, nel caso specifico del gioco The Colors Within Your Soul, anche i colori.
 */
public class Item extends GameObject implements Serializable {
    /**
     * Istanza del logger.
     */
    private static final Logger logger = LogManager.getLogger(Item.class);
    /**
     * Booleano che indica se l'item può essere raccolto e messo nell'inventario.
     */
    private boolean isPickable;

    /**
     * Booleano che indica se l'oggetto può essere dipinto o meno.
     */
    private boolean isPaintable;

    /**
     * Booleano che indica se l'oggetto può essere spostato o meno.
     */
    private boolean isMovable;

    /**
     * Costruttore con parametri della classe Item. Inizializza gli attributi della superclasse GameObject.
     *
     * @param id      ID dell'item.
     * @param name    Nome dell'item.
     * @param aliases Lista degli alias dell'item.
     * @param status  Stato corrente dell'item.
     */
    public Item(final int id, final String name, final List<String> aliases, final String status) {
        super(id, name, aliases, status);
    }

    /**
     * Metodo che inizializza le proprietà della classe Item.
     *
     * @param isPickable  Booleano che indica se l'item può essere raccolto e messo nell'inventario.
     * @param isPaintable Booleano che indica se l'oggetto può essere dipinto o meno.
     * @param isMovable   Booleano che indica se l'oggetto può essere spostato o meno.
     */
    public void initializeProperties(final boolean isPickable, final boolean isPaintable, final boolean isMovable) {
        setPickable(isPickable);
        setPaintable(isPaintable);
        setMovable(isMovable);
    }

    /**
     * Metodo getter per la proprietà isPickable.
     *
     * @return Proprietà isPickable.
     */
    public boolean getPickable() {
        return this.isPickable;
    }

    /**
     * Metodo getter per la proprietà isPaintable.
     *
     * @return Proprietà isPaintable.
     */
    public boolean getPaintable() {
        return this.isPaintable;
    }

    /**
     * Metodo getter per la proprietà isMovable.
     *
     * @return Proprietà isMovable.
     */
    public boolean getMovable() {
        return this.isMovable;
    }

    /**
     * Metodo setter per la proprietà isPickable.
     *
     * @param isPickable Booleano che indica se l'item può essere raccolto e messo nell'inventario.
     */
    public void setPickable(final boolean isPickable) {
        this.isPickable = isPickable;
    }

    /**
     * Metodo setter per la proprietà isPaintable.
     *
     * @param isPaintable Booleano che indica se l'oggetto può essere dipinto o meno.
     */
    public void setPaintable(final boolean isPaintable) {
        this.isPaintable = isPaintable;
    }

    /**
     * Metodo setter per la proprietà isMovable.
     *
     * @param isMovable Booleano che indica se l'oggetto può essere spostato o meno.
     */
    public void setMovable(final boolean isMovable) {
        this.isMovable = isMovable;
    }

    /**
     * Metodo che interroga il DB sulla base degli attributi dell'item per ottenerne la descrizione.
     * Definisce il metodo getDescriptionFromDB ereditato dalla superclasse.
     *
     * @return Descrizione dell'item in questione.
     */
    @Override
    public String getDescriptionFromDB() {
        ItemDaoImpl itemDao = new ItemDaoImpl();

        try {
            return itemDao.getDescrizioneByIdItemAndStato(super.getId(), super.getStatus());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return "Strano, neanche io che sono il narratore riesco a descrivere questo oggetto...";
        }
    }
}
