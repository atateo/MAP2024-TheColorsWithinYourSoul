package it.uniba.map.giocotestuale.database.dao;

import java.sql.SQLException;
import java.util.List;

import it.uniba.map.giocotestuale.database.domain.Dialog;

/**
 * Interfaccia per le operazioni di accesso ai dati per l'entità Dialog(dialogo).
 */
public interface DialogDao {

    /**
     * Aggiunge un nuovo record Dialog al database.
     *
     * @param dialog l'oggetto Dialog da aggiungere.
     * @return l'ID dell'oggetto dialog appena aggiunto.
     * @throws SQLException se si verifica un errore durante l'operazione di inserimento.
     */
    public int add(Dialog dialog) throws SQLException;

    /**
     * Elimina un oggetto dialog dal database partendo dal suo ID.
     *
     * @param id l'ID del dialogo da eliminare.
     * @throws SQLException se si verifica un errore durante l'operazione di eliminazione.
     */
    public void delete(int id) throws SQLException;

    /**
     * Recupera un oggetto Dialog dal database in base al suo ID.
     *
     * @param id l'ID del dialogo da recuperare.
     * @return l'oggetto Dialog corrispondente all'ID specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public Dialog getDialog(int id) throws SQLException;

    /**
     * Recupera tutti i record di dialog dal database.
     *
     * @return una lista di tutti gli oggetti Dialog presenti nel database.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public List<Dialog> getDialogs() throws SQLException;

    /**
     * Aggiorna un oggetto dialog esistente nel database.
     *
     * @param dialog l'oggetto Dialog con i dati aggiornati.
     * @throws SQLException se si verifica un errore durante l'operazione di aggiornamento.
     */
    public void update(Dialog dialog) throws SQLException;
    
    /**
     * Recupera il testo dell'oggetto Dialog dal database in base al suo id.
     *
     * @param id id del dialogo da recuperare.
     * @return testo il testo del Dialog corrispondente all'id specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public String getTestoById(int id) throws SQLException;
}
