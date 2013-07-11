package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.entity.Entity;

import java.util.List;

/**
 * Abstract Data Access Object
 * @param <K>
 * @param <T>
 */
public abstract class AbstractDAO <K, T extends Entity> {
    public abstract List<T> findAll();
    public abstract T findById(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
}