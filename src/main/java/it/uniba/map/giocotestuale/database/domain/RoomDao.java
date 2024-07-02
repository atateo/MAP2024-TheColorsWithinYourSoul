package it.uniba.map.giocotestuale.database.domain;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per le operazioni di accesso ai dati per l'entità Room(stanza).
 */
public interface RoomDao {

    /**
     * Aggiunge un nuovo record Room al database.
     *
     * @param room l'oggetto Room da aggiungere.
     * @return l'ID dell'oggetto room appena aggiunto.
     * @throws SQLException se si verifica un errore durante l'operazione di inserimento.
     */
    public int add(RoomRecord room) throws SQLException;

    /**
     * Elimina un oggetto room dal database partendo dal suo ID.
     *
     * @param id l'ID della room da eliminare.
     * @throws SQLException se si verifica un errore durante l'operazione di eliminazione.
     */
    public void delete(int id) throws SQLException;

    /**
     * Recupera un oggetto Room dal database in base al suo ID.
     *
     * @param id l'ID della room da recuperare.
     * @return l'oggetto Room corrispondente all'ID specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public RoomRecord getRoom(int id) throws SQLException;

    /**
     * Recupera tutti i record di room dal database.
     *
     * @return una lista di tutti gli oggetti Room presenti nel database.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public List<RoomRecord> getRooms() throws SQLException;

    /**
     * Aggiorna un oggetto room esistente nel database.
     *
     * @param room l'oggetto Room con i dati aggiornati.
     * @throws SQLException se si verifica un errore durante l'operazione di aggiornamento.
     */
    public void update(RoomRecord room) throws SQLException;
    
    /**
     * Recupera un oggetto Room dal database in base al suo idRoom (id nel game).
     *
     * @param idRoom l'idRoom della room da recuperare.
     * @param stato lo stato della room da recuperare.
     * @return la descrizione del record Room corrispondente all'idRoom e allo stato specificati.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public String getDescrizioneByIdRoomAndStato(int idRoom, String stato) throws SQLException;
}
