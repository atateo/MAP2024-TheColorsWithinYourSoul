package it.uniba.map.giocotestuale.database.dao;

import java.sql.SQLException;
import java.util.List;

import it.uniba.map.giocotestuale.database.domain.ItemRecord;

/**
 * Interfaccia per le operazioni di accesso ai dati per l'entità Item(oggetto).
 */
public interface ItemDao {

    /**
     * Aggiunge un nuovo record Item al database.
     *
     * @param item l'oggetto Item da aggiungere.
     * @return l'ID dell'oggetto item appena aggiunto.
     * @throws SQLException se si verifica un errore durante l'operazione di inserimento.
     */
    public int add(ItemRecord item) throws SQLException;

    /**
     * Elimina un oggetto item dal database partendo dal suo ID.
     *
     * @param id l'ID dell'item da eliminare.
     * @throws SQLException se si verifica un errore durante l'operazione di eliminazione.
     */
    public void delete(int id) throws SQLException;

    /**
     * Recupera un oggetto Item dal database in base al suo ID.
     *
     * @param id l'ID dell'item da recuperare.
     * @return l'oggetto Item corrispondente all'ID specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public ItemRecord getItem(int id) throws SQLException;

    /**
     * Recupera tutti i record di item dal database.
     *
     * @return una lista di tutti gli oggetti Item presenti nel database.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public List<ItemRecord> getItems() throws SQLException;

    /**
     * Aggiorna un oggetto item esistente nel database.
     *
     * @param room l'oggetto Item con i dati aggiornati.
     * @throws SQLException se si verifica un errore durante l'operazione di aggiornamento.
     */
    public void update(ItemRecord room) throws SQLException;
    
    /**
     * Recupera la descrizione dell'oggetto Item dal database in base al suo idItem (id nel game).
     *
     * @param idItem l'idItem dell'item da recuperare.
     * @param stato lo stato dell'item da recuperare.
     * @return la descrizione del record Item corrispondente all'idItem e allo stato specificati.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public String getDescrizioneByIdItemAndStato(int idItem, String stato) throws SQLException;
}
