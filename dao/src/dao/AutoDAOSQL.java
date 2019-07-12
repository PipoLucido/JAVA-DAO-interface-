/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import auto.Auto;
import auto.MotorizadoException;
import fecha.MiCalendario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class AutoDAOSQL extends DAO<Auto, String>{

    private Connection conn;
    private PreparedStatement psInsert;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;
    private PreparedStatement psSelect;
    
    AutoDAOSQL(String connection, String user, String pwd) 
            throws DAOException {
        try {
            try {
                // jdbc:mysql://localhost:3306/
                conn = DriverManager.
                        getConnection(connection, user, pwd);
            } catch (SQLException ex) {
                throw new DAOException("No se pudo conectar ==> "+
                        ex.getMessage());
            }
            // INSERT
            String sqlInsert = "INSERT INTO `universidad`.`autos`\n" +
                    "(`VIN`,\n" +
                    "`PATENTE`,\n" +
                    "`FECHA_FAB`,\n" +
                    "`PRECIO`,\n" +
                    "`MARCA`,\n" +
                    "`AÑO`)\n" +
                    "VALUES(\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?);";
            psInsert = conn.prepareStatement(sqlInsert);
            
            // SELECT
            String sqlSelectOne = "SELECT * FROM universidad.autos WHERE VIN = ?";
            psSelect = conn.prepareStatement(sqlSelectOne);
            
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
        
    }
    
    @Override
    public void insertar(Auto auto) throws DAOException {
        try {
            int index=1;
            psInsert.setString(index, auto.getVin());
            psInsert.setString(++index, auto.getPatente());
            psInsert.setDate(++index, MiCalendario.converto2SqlDate(auto.getFechaFab()));
            psInsert.setDouble(++index, auto.getPrecio());
            psInsert.setString(++index, auto.getMarca());
            psInsert.setInt(++index, auto.getAnio());
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void modificar(Auto entidad) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(String clave) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Auto buscar(String vin) throws DAOException {
        Auto auto = null;
        try {
            psSelect.setString(1, vin);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next())  {
                auto = new Auto();
                auto.setVin(rs.getString("VIN"));
                auto.setPatente(rs.getString("PATENTE"));
                auto.setFechaFab(MiCalendario.convert2MiCalendario(rs.getDate("FECHA_FAB")));
            }
        } catch (SQLException | MotorizadoException ex) {
            throw new DAOException(ex.getMessage());
        }
        
        return auto;
    }

    @Override
    public List<Auto> listar() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean existe(String clave) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
