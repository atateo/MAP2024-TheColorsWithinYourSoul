package it.uniba.map.giocotestuale.database.dao;

import java.sql.SQLException;
import java.util.List;

import it.uniba.map.giocotestuale.database.domain.ColorRecord;

/**
 * Interfaccia per le operazioni di accesso ai dati per l'entità Color(colore).
 */
public interface ColorDao {

    /**
     * Aggiunge un nuovo record Color al database.
     *
     * @param color l'oggetto Color da aggiungere.
     * @return l'ID dell'oggetto color appena aggiunto.
     * @throws SQLException se si verifica un errore durante l'operazione di inserimento.
     */
    public int add(ColorRecord color) throws SQLException;

    /**
     * Elimina un oggetto color dal database partendo dal suo ID.
     *
     * @param id l'ID del colore da eliminare.
     * @throws SQLException se si verifica un errore durante l'operazione di eliminazione.
     */
    public void delete(int id) throws SQLException;

    /**
     * Recupera un oggetto Color dal database in base al suo ID.
     *
     * @param id l'ID del colore da recuperare.
     * @return l'oggetto Color corrispondente all'ID specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public ColorRecord getColor(int id) throws SQLException;

    /**
     * Recupera tutti i record di color dal database.
     *
     * @return una lista di tutti gli oggetti Color presenti nel database.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public List<ColorRecord> getColors() throws SQLException;

    /**
     * Aggiorna un oggetto color esistente nel database.
     *
     * @param color l'oggetto Color con i dati aggiornati.
     * @throws SQLException se si verifica un errore durante l'operazione di aggiornamento.
     */
    public void update(ColorRecord color) throws SQLException;
    
    /**
     * Recupera la descrizione dell'oggetto Color dal database in base al suo id.
     *
     * @param id id del colore da recuperare.
     * @return la descrizione del record Color corrispondente all'id specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public String getDescrizioneById(int id) throws SQLException;
}
