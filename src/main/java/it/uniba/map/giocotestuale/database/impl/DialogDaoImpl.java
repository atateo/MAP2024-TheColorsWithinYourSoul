package it.uniba.map.giocotestuale.database.impl;

import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.dao.DialogDao;
import it.uniba.map.giocotestuale.database.domain.Dialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia DialogDao.
 * Fornisce i metodi per le operazioni CRUD sull'entità Dialog nel database.
 */
public class DialogDaoImpl implements DialogDao {

    /**
     * Costruttore di default.
     */
    public DialogDaoImpl() {
    }

    /**
     * Connessione al database condivisa tra i metodi.
     */
    static Connection conn = DatabaseConnection.getConnection();

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(Dialog dialog) throws SQLException {

        String query = "INSERT INTO dialog (testo) VALUES (?)";

        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dialog.getTesto());

        int n = ps.executeUpdate();
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dialog WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialog getDialog(int id) throws SQLException {
        String query = "SELECT * FROM dialog WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Dialog dialog = null;
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            dialog = new Dialog();
            dialog.setId(rs.getInt("id"));
            dialog.setTesto(rs.getString("testo"));
        }

        return dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dialog> getDialogs() throws SQLException {
        String query = "SELECT * FROM dialog";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Dialog> ls = new ArrayList<Dialog>();

        while (rs.next()) {
            Dialog item = new Dialog();
            item.setId(rs.getInt("id"));
            item.setTesto(rs.getString("testo"));
            ls.add(item);
        }
        return ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Dialog dialog) throws SQLException {
        // Aggiorna tutto il contenuto o solo i campi realmente variati
        String query = "UPDATE dialog SET testo = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dialog.getTesto());
        ps.setInt(2, dialog.getId());
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTestoById(int id) {

        String testo = null;
        try {
            String query = "SELECT testo FROM dialog WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                testo = rs.getString("testo");
            }
            if (testo == null || testo.isEmpty())
                testo = "Anche se sono il narratore non so cosa dirti...";
        } catch (SQLException e) {
            testo = "Anche se sono il narratore non so cosa dirti...";
        }

        return testo;
    }
}
