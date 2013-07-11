package kz.enu.epam.azimkhan.auth.test;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.dao.UserDAO;
import kz.enu.epam.azimkhan.auth.entity.User;
import org.junit.Test;

import java.util.List;

/**
 *  User DAO test
 */
public class UserDAOTest {

    @Test
    public void findAllTest(){
        UserDAO dao = new UserDAO();
        List<User> users = dao.findAll();

        System.out.print(users);
    }
}
