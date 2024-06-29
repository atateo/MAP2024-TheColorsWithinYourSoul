package it.uniba.map.giocotestuale.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia ContenutoDao.
 * Fornisce i metodi per le operazioni CRUD sull'entità Contenuto nel database.
 * 
 * @autor tateo.antimo
 */
public class ContenutoDaoImpl implements ContenutoDao {

    /**
     * Costruttore di default.
     */
    public ContenutoDaoImpl() {
    }

    /**
     * Connessione al database condivisa tra i metodi.
     */
    static Connection conn = DatabaseConnection.getConnection();

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(Contenuto contenuto) throws SQLException {
        String query = "INSERT INTO contenuto (label, messaggio, idItem, isRisposta) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, contenuto.getLabel());
        ps.setString(2, contenuto.getMessaggio());
        ps.setInt(3, contenuto.getIdItem());
        ps.setBoolean(4, contenuto.isRisposta());
        int n = ps.executeUpdate();
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM contenuto WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contenuto getContenuto(int id) throws SQLException {
        String query = "SELECT * FROM contenuto WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Contenuto contenuto = null;
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contenuto = new Contenuto();
            contenuto.setId(rs.getInt("id"));
            contenuto.setLabel(rs.getString("label"));
            contenuto.setMessaggio(rs.getString("messaggio"));
            contenuto.setIdItem(rs.getInt("idItem"));
            contenuto.setRisposta(rs.getBoolean("isRisposta"));
        }

        return contenuto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Contenuto> getContenuti() throws SQLException {
        String query = "SELECT * FROM contenuto";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Contenuto> ls = new ArrayList<Contenuto>();

        while (rs.next()) {
            Contenuto contenuto = new Contenuto();
            contenuto.setId(rs.getInt("id"));
            contenuto.setLabel(rs.getString("label"));
            contenuto.setMessaggio(rs.getString("messaggio"));
            contenuto.setRisposta(rs.getBoolean("isRisposta"));
            contenuto.setIdItem(rs.getInt("idItem"));
            ls.add(contenuto);
        }
        return ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Contenuto contenuto) throws SQLException {
        // Aggiorna tutto il contenuto o solo i campi realmente variati
        String query = "UPDATE contenuto SET label = ?, messaggio = ?, isRisposta = ?, idItem = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, contenuto.getLabel());
        ps.setString(2, contenuto.getMessaggio());
        ps.setBoolean(3, contenuto.isRisposta());
        ps.setInt(4, contenuto.getIdItem());
        ps.setInt(5, contenuto.getId());
        ps.executeUpdate();
    }
}
