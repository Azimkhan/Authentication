package kz.enu.epam.azimkhan.auth.test;

import kz.enu.epam.azimkhan.auth.entity.User;
import org.junit.Assert;
import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import org.junit.Test;

import java.sql.*;

/**
 *  User test
 */
public class UserTest {

    @Test
    public void setterGetterTest(){
        final String username = "username";
        final String password = "password";
        final int id = 2;

        User user = new User();

        user.setId(2);
        user.setUsername(username);
        user.setPassword(password);

        Assert.assertEquals(user.getUsername(), username);
        Assert.assertEquals(user.getPassword(), password);
        Assert.assertEquals(user.getId(), id);

    }
}
