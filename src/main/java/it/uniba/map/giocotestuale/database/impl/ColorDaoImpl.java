package it.uniba.map.giocotestuale.database.impl;

import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.dao.ColorDao;
import it.uniba.map.giocotestuale.database.domain.ColorRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia ColorDao.
 * Fornisce i metodi per le operazioni CRUD sull'entità Color nel database.
 */
public class ColorDaoImpl implements ColorDao {

    /**
     * Costruttore di default.
     */
    public ColorDaoImpl() {
    }

    /**
     * Connessione al database condivisa tra i metodi.
     */
    static Connection conn = DatabaseConnection.getConnection();

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(ColorRecord color) throws SQLException {
    	int n = 0;
        String query = "INSERT INTO color (descrizione) VALUES (?)";

        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, color.getDescrizione());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            n = rs.getInt(1);
        }
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM color WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorRecord getColor(int id) throws SQLException {
        String query = "SELECT * FROM color WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ColorRecord color = null;
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            color = new ColorRecord();
            color.setId(rs.getInt("id"));
            color.setDescrizione(rs.getString("descrizione"));
        }

        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ColorRecord> getColors() throws SQLException {
        String query = "SELECT * FROM color";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<ColorRecord> ls = new ArrayList<>();

        while (rs.next()) {
            ColorRecord item = new ColorRecord();
            item.setId(rs.getInt("id"));
            item.setDescrizione(rs.getString("descrizione"));
            ls.add(item);
        }
        return ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ColorRecord color) throws SQLException {
        // Aggiorna tutto il contenuto o solo i campi realmente variati
        String query = "UPDATE color SET descrizione = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, color.getDescrizione());
        ps.setInt(2, color.getId());
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescrizioneById(int id) throws SQLException {

        String descrizione = null;
        //id_room e stato sono alternate key in tabella
        String query = "SELECT descrizione FROM color WHERE id = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            descrizione = rs.getString("descrizione");
        }

        return descrizione;
    }
}
