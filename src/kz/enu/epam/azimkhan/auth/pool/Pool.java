package kz.enu.epam.azimkhan.auth.pool;

/**
 * object pool interface
 */
public interface Pool<T>{
    /**
     * Retrieves one object from the pool
     * @return an object
     */
    public T get();

    /**
     * Releases(returns) object to the pool
     * @param t
     */
    public void release(T t);
}
