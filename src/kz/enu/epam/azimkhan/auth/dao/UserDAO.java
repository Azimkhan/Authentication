package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class UserDAO extends AbstractDAO<Integer, User>{

    private static final String SELECT_ALL = "SELECT id, username, password FROM users";
    private static final String FIND_BY_ID = "SELECT id, username, password FROM users WHERE id = ?";
    private static final String FIND_BY_LOGIN_PASSWORD = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
    private static final String DELETE_BY_ID = "DELETE users WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO users (username, password) VALUES(?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ? WHERE id = ?";
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public List<User> findAll() {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        LinkedList<User> users = new LinkedList<User>();

        if (connection != null){
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
        }

        return users;
    }

    /**
     * Find a user by id
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {

        User user = null;
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();

        if (connection != null) {
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
        }

        return user;
    }

    /**
     * Find user by username and hashed password
     * @param login
     * @param password
     * @return
     */
    public User findByLoginAndPassword(String login, String password){
        User user = null;

        if(login != null && password != null){
            ConnectionPool connectionPool = ConnectionPool.INSTANCE;
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = null;

            if (connection != null) {
                try {
                    statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
                    statement.setString(1, login);
                    statement.setString(2, password);

                    ResultSet resultSet = statement.executeQuery();

                    if(resultSet.next()){
                        user = createFromResultSet(resultSet);
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                } finally {
                    connectionPool.release(connection);
                }
            }

        }

        return user;
    }

    /**
     * Delete user by id
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        boolean result = false;

        if (null != id) {
            Connection connection = connectionPool.getConnection();
            if (connection != null) {
                try {
                    PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
                    statement.setInt(1, id);
                    statement.executeQuery();
                    result = true;

                } catch (SQLException e) {
                    result = false;
                } finally {
                    connectionPool.release(connection);
                }
            }
        }

        return result;
    }

    /**
     * Delete user
     * @param entity
     * @return
     */
    @Override
    public boolean delete(User entity) {
        if (entity != null){
            return delete(entity.getId());
        }

        return false;
    }

    /**
     * Create user
     * @param entity
     * @return
     */
    @Override
    public boolean create(User entity) {
        if (entity != null){
            ConnectionPool connectionPool = ConnectionPool.INSTANCE;
            Connection connection = connectionPool.getConnection();

            if (connection != null){
                try {
                    PreparedStatement statement = connection.prepareStatement(CREATE_USER);
                    statement.setString(1, entity.getUsername());
                    statement.setString(2, entity.getPassword());

                    int result = statement.executeUpdate();

                    logger.info("User create result = " + result);

                    return (result > 0);

                } catch (SQLException e) {
                    logger.error(e.getMessage());
                } finally {
                    connectionPool.release(connection);
                }
            }
        }

        return false;
    }

    /**
     * Update user
     * @param entity
     * @return
     */
    @Override
    public User update(User entity) {
        return null;
    }

    /**
     * Create user from result set
     * @param set
     * @return
     * @throws SQLException
     */
    private User createFromResultSet(ResultSet set) throws SQLException{

        User user = new User();
        user.setId(set.getInt("id"));
        user.setUsername(set.getString("username"));
        user.setPassword(set.getString("password"));

        return user;
    }
}
