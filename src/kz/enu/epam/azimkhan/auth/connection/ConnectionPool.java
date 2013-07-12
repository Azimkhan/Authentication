package kz.enu.epam.azimkhan.auth.connection;

import kz.enu.epam.azimkhan.auth.pool.Pool;
import kz.enu.epam.azimkhan.auth.resource.DBConfigurationManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Database connection pool
 */
public enum ConnectionPool implements Pool<Connection>{
    INSTANCE;

    private final int POOL_SIZE = 10;
    private final DBConfigurationManager config = DBConfigurationManager.INSTANCE;
    private final Logger logger = Logger.getRootLogger();

    private BlockingQueue<Connection> connections;

    ConnectionPool(){
        initConnections();
    }

    /**
     * Create and fill connection pool
     */
    private final void initConnections()  {
        connections = new LinkedBlockingQueue<Connection>(POOL_SIZE);

        String connectionUrl = config.getString(DBConfigurationManager.DATABASE_CONNECTION_URL);
        String username = config.getString(DBConfigurationManager.DATABASE_USERNAME);
        String password = config.getString(DBConfigurationManager.DATABASE_PASSWORD);
        String driverName = config.getString(DBConfigurationManager.DATABASE_DRIVER_NAME);

        try {
            Class.forName(driverName);
            for (int i = 0; i < POOL_SIZE; i++){
                connections.add(DriverManager.getConnection(connectionUrl, username, password));
            }
        } catch (SQLException e) {
            // What to do?????????
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get single connection from concurrent queue
     * @return connection to use
     */
    @Override
    public final Connection get() {
        try {
            Connection connection = connections.take();
            logger.info("Connection " + connection + " took from connection pool");
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return connection
     * @param connection connection to return to the pool
     */
    @Override
    public final void release(Connection connection) {
        try {
            connections.put(connection);
            logger.info("Connection " + connection + " returned to connection pool");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve pool size
     * @return
     */
    public final int getPoolSize(){
        return POOL_SIZE;
    }

    public void shutDown(){

        for(Connection connection : connections){
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Couldn't close connection: " + e.getMessage());
            }
        }

        logger.info("Connection pool is shut down");
    }
}
