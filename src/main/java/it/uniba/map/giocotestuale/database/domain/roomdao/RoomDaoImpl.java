package it.uniba.map.giocotestuale.database.domain.roomdao;

import it.uniba.map.giocotestuale.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia RoomDao.
 * Fornisce i metodi per le operazioni CRUD sull'entità Room nel database.
 * 
 */
public class RoomDaoImpl implements RoomDao {

    /**
     * Costruttore di default.
     */
    public RoomDaoImpl() {
    }

    /**
     * Connessione al database condivisa tra i metodi.
     */
    static Connection conn = DatabaseConnection.getConnection();

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(RoomRecord room) throws SQLException {
    	
        String query = "INSERT INTO room (stato, descrizione, id_room) VALUES (?, ?, ?)";
        
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, room.getStato());
        ps.setString(2, room.getDescrizione());
        ps.setInt(3, room.getIdRoom());
        
        int n = ps.executeUpdate();
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM room WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomRecord getRoom(int id) throws SQLException {
        String query = "SELECT * FROM room WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        RoomRecord room = null;
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
        	room = new RoomRecord();
        	room.setId(rs.getInt("id"));
        	room.setStato(rs.getString("stato"));
        	room.setDescrizione(rs.getString("descrizione"));
        	room.setIdRoom(rs.getInt("id_room"));
        }

        return room;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoomRecord> getRooms() throws SQLException {
        String query = "SELECT * FROM room";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<RoomRecord> ls = new ArrayList<RoomRecord>();

        while (rs.next()) {
            RoomRecord room = new RoomRecord();
            room.setId(rs.getInt("id"));
            room.setStato(rs.getString("stato"));
            room.setDescrizione(rs.getString("descrizione"));
            room.setIdRoom(rs.getInt("id_room"));
            ls.add(room);
        }
        return ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(RoomRecord room) throws SQLException {
        // Aggiorna tutto il contenuto o solo i campi realmente variati
        String query = "UPDATE room SET stato = ?, descrizione = ?, id_room = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, room.getStato());
        ps.setString(2, room.getDescrizione());
        ps.setInt(3, room.getIdRoom());
        ps.setInt(4, room.getId());
        ps.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescrizioneByIdRoomAndStato(int id, String stato) throws SQLException {
    	
    	String descrizione = null;
    	//id_room e stato sono alternate key in tabella
        String query = "SELECT descrizione FROM room WHERE id_room = ? and lower(stato) = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, stato.toLowerCase());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
        	descrizione=rs.getString("descrizione");
        }

        return descrizione;
    }
}
