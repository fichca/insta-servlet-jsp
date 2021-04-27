package by.insta.stotage.db;

import by.insta.entity.User;
import by.insta.stotage.SubscribersStorage;
import by.insta.stotage.db.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;

public class SubscribersStorageDBImpl implements SubscribersStorage {
    private DataSource dataSource;

    public SubscribersStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addSubscriber(User user, User subscriber) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO subscription VALUES(?, ?)");

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, subscriber.getId());

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
    public boolean deleteSubscriber(User user, User subscriber) {

        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM subscription WHERE user_id = ? AND subscriber_id = ?");

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, subscriber.getId());
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
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM subscription WHERE user_id = ?");

            preparedStatement.setLong(1, user.getId());
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
    public List<User> getSubscribers(User user) {
        Connection connection = null;
        PreparedStatement preparedStatementSubscribers;
        ResultSet resultSetSubscribers;
        try {
            connection = dataSource.getConnection();
            preparedStatementSubscribers = connection.prepareStatement("select id, name, login, password, role from  subscription  s  join users u on s.subscriber_id = u.id where s.user_id =  ?");
            preparedStatementSubscribers.setLong(1, user.getId());
            resultSetSubscribers = preparedStatementSubscribers.executeQuery();

            return UserMapper.getUserList(resultSetSubscribers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(User user, User subscriber) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from  subscription WHERE user_id = ? AND subscriber_id = ?");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, subscriber.getId());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from  subscription WHERE user_id = ?");
            preparedStatement.setLong(1, user.getId());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }
}
