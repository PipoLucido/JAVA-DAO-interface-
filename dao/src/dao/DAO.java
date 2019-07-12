/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Gabriel
 * @param <T> Tipo de dato genérico
 */
public abstract class DAO<T,K> {
    
    public abstract void insertar(T entidad) throws DAOException;
    public abstract void modificar(T entidad) throws DAOException;
    public abstract void eliminar(K clave) throws DAOException;
    public abstract T buscar(K clave) throws DAOException;
    public abstract Boolean existe(K clave) throws DAOException;
    public abstract List<T> listar() throws DAOException;

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
