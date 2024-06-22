/**
 * 
 */
package it.uniba.map.giocotestuale.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.uniba.map.giocotestuale.database.DatabaseConnection;
import it.uniba.map.giocotestuale.model.Contenuto;

/**
 * @author tateo.antimo
 *
 */
public class ContenutoDaoImpl implements ContenutoDao {

	/**
	 * 
	 */
	public ContenutoDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	static Connection conn = DatabaseConnection.getConnection();

	@Override
	public int add(Contenuto contenuto) throws SQLException {
		String query
		= "insert into contenuto"
				+ "(label, messaggio, idItem, isRisposta) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, contenuto.getLabel());
		ps.setString(2, contenuto.getMessaggio());
		ps.setInt(3, contenuto.getIdItem());
		ps.setBoolean(4, contenuto.isRisposta());
		int n = ps.executeUpdate();

		return n;
	}

	@Override
	public void delete(int id) throws SQLException {
		String query
		= "delete from contenuto where id =?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	@Override
	public Contenuto getContenuto(int id) throws SQLException {
		String query
		= "select * from contenuto where id= ?";
		PreparedStatement ps
		= conn.prepareStatement(query);

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

	@Override
	public List<Contenuto> getContenuti() throws SQLException {
		String query = "select * from contenuto";
		PreparedStatement ps
		= conn.prepareStatement(query);
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

	@Override
	public void update(Contenuto contenuto) throws SQLException {
		//questo lo devo pensare bene (aggiornare tutto contenuto o solo i campi realmente variati?)
		String query = "update contenuto set"
				+" label = ?,"
				+" messaggio = ?,"
				+" isRisposta = ?,"
				+" idItem = ?"
				+" where id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, contenuto.getLabel());
			ps.setString(2, contenuto.getMessaggio());
			ps.setBoolean(3, contenuto.isRisposta());
			ps.setInt(4, contenuto.getIdItem());
			ps.setInt(5, contenuto.getId());

			ps.executeUpdate();
	}

}
