package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.entity.User;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class UserDAO extends AbstractDAO<Integer, User>{

    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String DELETE_USER = "DELETE users WHERE is = ?";
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public List<User> findAll() {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.get();
        LinkedList<User> users = new LinkedList<User>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet set = statement.executeQuery();


            while(set.next()){

                User user = createFromResultSet(set);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            connectionPool.release(connection);
        }

        return users;
    }

    @Override
    public User findById(Integer id) {

        User user = null;
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.get();

        try{
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();

            set.next();
            if (!set.wasNull()){
                user = createFromResultSet(set);
            }

        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            connectionPool.release(connection);
        }

        return user;
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

    private User createFromResultSet(ResultSet set) throws SQLException{

        User user = new User();
        user.setId(set.getInt("id"));
        user.setUsername(set.getString("username"));
        user.setPassword(set.getString("password"));

        return user;
    }
}
