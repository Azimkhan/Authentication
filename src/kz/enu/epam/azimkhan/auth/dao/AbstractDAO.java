package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.entity.Entity;
import kz.enu.epam.azimkhan.auth.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.auth.exception.DAOTechnicalException;

import java.util.List;

/**
 * Abstract Data Access Object
 * @param <K>
 * @param <T>
 */
public abstract class AbstractDAO <K, T extends Entity> {
    public abstract List<T> findAll() throws DAOLogicalException, DAOTechnicalException;
    public abstract T findById(K id) throws DAOLogicalException, DAOTechnicalException;
    public abstract boolean delete(K id) throws DAOLogicalException, DAOTechnicalException;
    public abstract boolean delete(T entity) throws DAOLogicalException, DAOTechnicalException;
    public abstract boolean create(T entity) throws DAOLogicalException, DAOTechnicalException;
    public abstract boolean update(T entity) throws DAOLogicalException, DAOTechnicalException;
}