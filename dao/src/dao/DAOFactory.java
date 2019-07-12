/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class DAOFactory {
    
    public static final String TIPO_DAO = "TIPO_DAO";
    public static final String FILE_NAME = "FILE_NAME";
    public static final String STR_CONNECTION = "STR_CONNECTION";
    public static final String TIPO_DAO_TXT = "TIPO_DAO_TXT";
    public static final String TIPO_DAO_SQL = "TIPO_DAO_SQL";
    private static DAOFactory instance;
    
    private DAOFactory () {
        
    }
    
    public static DAOFactory getInstance() {
        if (instance==null) {
            instance  = new DAOFactory();
        }
        
        return instance;
    }
    
    public DAO crearDAO(Map<String, String> config) 
            throws DAOException {
        String  tipoDAO = config.get(TIPO_DAO);
        switch(tipoDAO) {
            case TIPO_DAO_TXT:
                String fileName = config.get(FILE_NAME);
                return new AutoDAOTXT(fileName);
            case TIPO_DAO_SQL:
                String strConn = config.get(STR_CONNECTION);
                return new AutoDAOSQL(strConn, "root", "root");
            default:
                throw new DAOException("NO IMPLEMTADO");
        }
    }
}
