/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import auto.Auto;
import auto.Motorizado;
import auto.MotorizadoException;
import fecha.MiCalendario;
import fecha.MiCalendarioException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class AutoDAOTXT extends DAO<Auto, String>{

    private RandomAccessFile raf;
    
    AutoDAOTXT(String path) throws DAOException {
        File file = new File(path);
        try {
            raf = new RandomAccessFile(file, "rws");
        } catch (FileNotFoundException ex) {
            throw new DAOException("El archivo no existe ==> "+ex);
        }
    }

    
    @Override
    public void insertar(Auto entidad) throws DAOException {
        try {
            if (existe(entidad.getVin())) {
                throw new DAOException("El auto con el VIN "+entidad.getVin()+" ya existe");
            }
            // Se posiciona al final del archivo
            raf.seek(raf.length());
            raf.writeBytes(entidad.toString()+System.lineSeparator());
        } catch (IOException ex) {
            Logger.getLogger(AutoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Auto entidad) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(String vin) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Auto buscar(String vin) throws DAOException {
        try {
            // Se posiciona al comienzo del archivo
            raf.seek(0);
            String linea;
            String campos[];
            while((linea = raf.readLine())!=null) {
                campos = linea.split(Motorizado.DELIM);
                if (vin.equals(campos[0])) {
                    return Auto.str2Auto(campos);
                }
            }
            
            return null;
        } catch (IOException ex) {
            throw new DAOException("Error de I/O ==> "+ex.getMessage());
        } catch (MotorizadoException | MiCalendarioException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<Auto> listar() throws DAOException {
        List<Auto> autos = new ArrayList<>();
        try {
            autos.add(new Auto("FIAT", "UNO", "BSB222", new MiCalendario(1, 1, 2000), 22222.44, Integer.SIZE, "12345678901234567", new MiCalendario(2, 3, 2000)));
            autos.add(new Auto("FORD", "Fiesta", "BSB223", new MiCalendario(1, 1, 2000), 22222.44, Integer.SIZE, "12345678901234568", new MiCalendario(2, 3, 2000)));
            autos.add(new Auto("TOYOTA", "Etios", "BSB224", new MiCalendario(1, 1, 2000), 22222.44, Integer.SIZE, "12345678901234569", new MiCalendario(2, 3, 2000)));
        } catch (MiCalendarioException ex) {
            Logger.getLogger(AutoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MotorizadoException ex) {
            Logger.getLogger(AutoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autos;
    }

    @Override
    public Boolean existe(String vin) throws DAOException {
        try {
            // Se posiciona al comienzo del archivo
            raf.seek(0);
            String linea;
            while((linea = raf.readLine())!=null) {
                if (vin.equals(linea.substring(0, Motorizado.VIN_LONG))) {
                    return true;
                }
            }
            
            return false;
        } catch (IOException ex) {
            throw new DAOException("Error de I/O ==> "+ex.getMessage());
        }
    }
    
}
