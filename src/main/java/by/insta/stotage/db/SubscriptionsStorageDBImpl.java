package by.insta.stotage.db;

import by.insta.entity.User;
import by.insta.stotage.SubscriptionsStorage;
import by.insta.stotage.db.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class SubscriptionsStorageDBImpl implements SubscriptionsStorage {
    private DataSource dataSource;

    public SubscriptionsStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean addSubscription(User user, User subscriber) {

        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO subscription VALUES(?, ?)");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, subscriber.getId());

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
    public boolean deleteSubscription(User user, User subscriber) {

        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
           preparedStatement = connection.prepareStatement("DELETE FROM subscription WHERE user_id = ? AND subscriber_id = ?");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, subscriber.getId());
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
    public boolean deleteUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM subscription WHERE subscriber_id = ?");

            preparedStatement.setInt(1, user.getId());
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
    public List<User> getSubscription(User user) {
        Connection connection = null;
        PreparedStatement preparedStatementSubscriptions;
        ResultSet resultSetSubscriptions;
        try {
            connection = dataSource.getConnection();
            preparedStatementSubscriptions = connection.prepareStatement("select id, name, login, password, role from  subscription  s  join users u on s.user_id = u.id where s.subscriber_id = ?");
            preparedStatementSubscriptions.setLong(1, user.getId());
            resultSetSubscriptions = preparedStatementSubscriptions.executeQuery();


            List<User> userList = UserMapper.getUserList(resultSetSubscriptions);
            preparedStatementSubscriptions.close();
            return userList;
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
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from  subscription WHERE subscriber_id = ?");
            preparedStatement.setInt(1, user.getId());
            boolean next = preparedStatement.executeQuery().next();
            preparedStatement.close();
            return next;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }


    @Override
    public boolean contains(User user, User subscriber) {

        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            connection = dataSource.getConnection();
             preparedStatement = connection.prepareStatement("select * from  subscription WHERE user_id = ? AND subscriber_id = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, subscriber.getId());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }


    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
