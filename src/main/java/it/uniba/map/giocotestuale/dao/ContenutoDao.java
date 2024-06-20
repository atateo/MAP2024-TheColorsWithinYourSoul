package it.uniba.map.giocotestuale.dao;

import java.sql.SQLException;
import java.util.List;

import it.uniba.map.giocotestuale.model.Contenuto;

public interface ContenutoDao {
 
    public int add(Contenuto contenuto)
        throws SQLException;
    public void delete(int id)
        throws SQLException;
    public Contenuto getContenuto(int id)
        throws SQLException;
    public List<Contenuto> getContenuti()
        throws SQLException;
    public void update(Contenuto contenuto)
        throws SQLException;
}