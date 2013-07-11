package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class UserDAO extends AbstractDAO<Integer, User>{

    private static final String SELECT_ALL = "SELECT * FROM users";

    @Override
    public List<User> findAll() {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.get();
        LinkedList<User> users = new LinkedList<User>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet set = statement.executeQuery();


            while(set.next()){
                User user = new User();
                user.setId(set.getInt("id"));
                user.setUsername(set.getString("username"));
                user.setPassword(set.getString("password"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            //Logging
        }

        return users;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
