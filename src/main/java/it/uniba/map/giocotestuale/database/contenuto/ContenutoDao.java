package it.uniba.map.giocotestuale.database.contenuto;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per le operazioni di accesso ai dati per l'entità Contenuto.
 */
public interface ContenutoDao {

    /**
     * Aggiunge un nuovo contenuto al database.
     *
     * @param contenuto l'oggetto Contenuto da aggiungere.
     * @return l'ID del contenuto appena aggiunto.
     * @throws SQLException se si verifica un errore durante l'operazione di inserimento.
     */
    public int add(Contenuto contenuto) throws SQLException;

    /**
     * Elimina un contenuto dal database partendo dal suo ID.
     *
     * @param id l'ID del contenuto da eliminare.
     * @throws SQLException se si verifica un errore durante l'operazione di eliminazione.
     */
    public void delete(int id) throws SQLException;

    /**
     * Recupera un contenuto dal database in base al suo ID.
     *
     * @param id l'ID del contenuto da recuperare.
     * @return l'oggetto Contenuto corrispondente all'ID specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public Contenuto getContenuto(int id) throws SQLException;

    /**
     * Recupera tutti i contenuti dal database.
     *
     * @return una lista di tutti gli oggetti Contenuto presenti nel database.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public List<Contenuto> getContenuti() throws SQLException;

    /**
     * Aggiorna un contenuto esistente nel database.
     *
     * @param contenuto l'oggetto Contenuto con i dati aggiornati.
     * @throws SQLException se si verifica un errore durante l'operazione di aggiornamento.
     */
    public void update(Contenuto contenuto) throws SQLException;
}
