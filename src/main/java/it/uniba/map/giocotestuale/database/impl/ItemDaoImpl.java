package it.uniba.map.giocotestuale.database.impl;

import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.dao.ItemDao;
import it.uniba.map.giocotestuale.database.domain.ItemRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia ItemDao.
 * Fornisce i metodi per le operazioni CRUD sull'entità Item nel database.
 */
public class ItemDaoImpl implements ItemDao {

    /**
     * Costruttore di default.
     */
    public ItemDaoImpl() {
    }

    /**
     * Connessione al database condivisa tra i metodi.
     */
    static Connection conn = DatabaseConnection.getConnection();

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(ItemRecord item) throws SQLException {

        String query = "INSERT INTO item (stato, descrizione, id_item) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, item.getStato());
        ps.setString(2, item.getDescrizione());
        ps.setInt(3, item.getIdItem());

        int n = ps.executeUpdate();
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM item WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRecord getItem(int id) throws SQLException {
        String query = "SELECT * FROM item WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ItemRecord item = null;
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            item = new ItemRecord();
            item.setId(rs.getInt("id"));
            item.setStato(rs.getString("stato"));
            item.setDescrizione(rs.getString("descrizione"));
            item.setIdItem(rs.getInt("id_item"));
        }

        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemRecord> getItems() throws SQLException {
        String query = "SELECT * FROM item";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<ItemRecord> ls = new ArrayList<ItemRecord>();

        while (rs.next()) {
            ItemRecord item = new ItemRecord();
            item.setId(rs.getInt("id"));
            item.setStato(rs.getString("stato"));
            item.setDescrizione(rs.getString("descrizione"));
            item.setIdItem(rs.getInt("id_item"));
            ls.add(item);
        }
        return ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ItemRecord item) throws SQLException {
        // Aggiorna tutto il contenuto o solo i campi realmente variati
        String query = "UPDATE item SET stato = ?, descrizione = ?, id_item = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, item.getStato());
        ps.setString(2, item.getDescrizione());
        ps.setInt(3, item.getIdItem());
        ps.setInt(4, item.getId());
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescrizioneByIdItemAndStato(int id, String stato) throws SQLException {

        String descrizione = null;
        //id_room e stato sono alternate key in tabella
        String query = "SELECT descrizione FROM item WHERE id_item = ? and lower(stato) = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, stato.toLowerCase());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            descrizione = rs.getString("descrizione");
        }

        return descrizione;
    }
}
