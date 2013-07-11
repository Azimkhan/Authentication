package kz.enu.epam.azimkhan.auth.test;

import org.junit.Test;
import org.junit.Assert;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class ConnectionPoolTest {

    @org.junit.Test
    public void test() throws SQLException, ClassNotFoundException {

        ConnectionPool pool = ConnectionPool.INSTANCE;
        Connection connection = pool.get();
        Assert.assertNotNull(connection);
        pool.release(connection);
    }
}
