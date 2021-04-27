package by.insta.stotage.db;

import by.insta.entity.User;
import by.insta.stotage.UserStorage;
import by.insta.stotage.db.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;


public class UserStorageDBImpl implements UserStorage {

    private DataSource dataSource;

    public UserStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean addUser(User user) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }


    @Override
    public User getUserById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from  users where id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
//            preparedStatementUser.execute();
            return UserMapper.getUser(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public User getUserByLogin(String login) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from  users where login = ?");

            preparedStatementUser.setString(1, login);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return UserMapper.getUser(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean deleteUserById(long id) {

        return false;
    }


    @Override
    public boolean deleteUserByLogin(String login) {
        return false;
    }

    @Override
    public boolean deleteUserByUser(User user) {
        return false;
    }

    @Override
    public boolean updateNameById(long id, String name) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean updatePasswordById(long id, String password) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ? WHERE password = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from  users");
            ResultSet resultSet = preparedStatementUser.executeQuery();
            return UserMapper.getUserList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<User> getAllUsersByName(String name) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from  users where name = ?");
            preparedStatementUser.setString(1, name);
            ResultSet resultSet = preparedStatementUser.executeQuery();
            return UserMapper.getUserList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  users WHERE name = ? AND login = ? AND password = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  users WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  users  where login = ?");
            preparedStatement.setString(1, login);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println();
            closeConnection(connection);
        }
        return false;
    }


}
