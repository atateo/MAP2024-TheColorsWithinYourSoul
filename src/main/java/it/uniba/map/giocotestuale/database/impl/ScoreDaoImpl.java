package it.uniba.map.giocotestuale.database.impl;

import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.database.dao.ScoreDao;
import it.uniba.map.giocotestuale.database.domain.Score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia ScoreDao.
 * Fornisce i metodi per le operazioni CRUD sull'entità Score nel database.
 * 
 * @author tateo.antimo
 */
public class ScoreDaoImpl implements ScoreDao {

    /**
     * Costruttore di default.
     */
    public ScoreDaoImpl() {
    }

    /**
     * Connessione al database condivisa tra i metodi.
     */
    static Connection conn = DatabaseConnection.getConnection();

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(Score score) throws SQLException {
        String query = "INSERT INTO score (player, time) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, score.getPlayer());
        ps.setString(2, score.getTime());
        int n = ps.executeUpdate();
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM score WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Score getScore(int id) throws SQLException {
        String query = "SELECT * FROM score WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Score score = null;
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
        	score = new Score();
        	score.setId(rs.getInt("id"));
        	score.setPlayer(rs.getString("player"));
        	score.setTime(rs.getString("time"));
        }
        return score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getScores() throws SQLException {
        String query = "SELECT * FROM score";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Score> ls = new ArrayList<Score>();

        while (rs.next()) {
        	Score score = new Score();
        	score.setId(rs.getInt("id"));
        	score.setPlayer(rs.getString("player"));
        	score.setTime(rs.getString("time"));
            ls.add(score);
        }
        return ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Score score) throws SQLException {
        String query = "UPDATE score SET player = ?, time = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, score.getPlayer());
        ps.setString(2, score.getTime());
        ps.setInt(3, score.getId());
        ps.executeUpdate();
    }
}