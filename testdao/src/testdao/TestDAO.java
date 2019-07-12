/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import auto.Auto;
import auto.MotorizadoException;
import dao.AutoDAOTXT;
import dao.DAO;
import dao.DAOException;
import dao.DAOFactory;
import fecha.MiCalendario;
import fecha.MiCalendarioException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class TestDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAO<Auto, String> dao;
        DAOFactory factory = DAOFactory.getInstance();
        
        Map<String, String> config = 
                new HashMap<String, String>();
//        config.put(DAOFactory.TIPO_DAO, DAOFactory.TIPO_DAO_TXT);
//        config.put(DAOFactory.FILE_NAME, "Auto2.txt");

        config.put(DAOFactory.TIPO_DAO, DAOFactory.TIPO_DAO_SQL);
        config.put(DAOFactory.STR_CONNECTION, 
                "jdbc:mysql://localhost:3306/?serverTimezone=GMT-3");
        
        try {
            dao = factory.crearDAO(config);
        } catch (DAOException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        
        Auto fiat;
        try {
            fiat = new Auto("Fiat", 
                    "Cronos", 
                    "1112", 
                    new MiCalendario(12, 3, 2000)
                    , 200.000
                    , 1999, 
                    "ABC4567890123456A", 
                    new MiCalendario(18, 5, 2019));
        } catch (MotorizadoException | MiCalendarioException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        try {
           //dao.insertar(fiat);
           Auto auto = dao.buscar("ABC4567890123456A");
            System.out.println(auto);
        } catch (DAOException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

    }
    
}
