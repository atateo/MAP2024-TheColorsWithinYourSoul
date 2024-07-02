package it.uniba.map.giocotestuale.database.score;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per le operazioni di accesso ai dati per l'entità Score.
 */
public interface ScoreDao {

    /**
     * Aggiunge un nuovo punteggio al database.
     *
     * @param score l'oggetto Score da aggiungere.
     * @return l'ID del punteggio appena aggiunto.
     * @throws SQLException se si verifica un errore durante l'operazione di inserimento.
     */
    public int add(Score score) throws SQLException;

    /**
     * Elimina un punteggio dal database partendo dal suo ID.
     *
     * @param id l'ID del punteggio da eliminare.
     * @throws SQLException se si verifica un errore durante l'operazione di eliminazione.
     */
    public void delete(int id) throws SQLException;

    /**
     * Recupera un punteggio dal database in base al suo ID.
     *
     * @param id l'ID del punteggio da recuperare.
     * @return l'oggetto Score corrispondente all'ID specificato.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public Score getScore(int id) throws SQLException;

    /**
     * Recupera tutti i punteggi dal database.
     *
     * @return una lista di tutti gli oggetti Score presenti nel database.
     * @throws SQLException se si verifica un errore durante l'operazione di recupero.
     */
    public List<Score> getScores() throws SQLException;

    /**
     * Aggiorna un punteggio esistente nel database.
     *
     * @param score l'oggetto Score con i dati aggiornati.
     * @throws SQLException se si verifica un errore durante l'operazione di aggiornamento.
     */
    public void update(Score score) throws SQLException;
}
