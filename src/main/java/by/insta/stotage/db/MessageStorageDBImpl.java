package by.insta.stotage.db;

import by.insta.entity.Message;
import by.insta.entity.Role;
import by.insta.entity.User;
import by.insta.stotage.MessageStorage;
import by.insta.stotage.db.mapper.CommentMapper;
import by.insta.stotage.db.mapper.MessageMapper;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;

public class MessageStorageDBImpl implements MessageStorage {

    private DataSource dataSource;

    public MessageStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/insta");

        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("org.postgresql.Driver");

        MessageStorageDBImpl messageStorageDB = new MessageStorageDBImpl(dataSource);
//        System.out.println(messageStorageDB.contains(new Message(1, 1, new User(1, "test", "test", "test", Role.USER), "teeeeeest", new Date())));
//        System.out.println(messageStorageDB.contains(new Message(2, new User(1, "test", "test", "test", Role.USER), "teeeeeest")));
//        System.out.println(messageStorageDB.contains(new Message(1, new User(2, "test", "test", "test", Role.USER), "teeeeeest")));
//        System.out.println(messageStorageDB.contains(1));
//        System.out.println(messageStorageDB.contains(3));
//        System.out.println(messageStorageDB.contains(new User(2, "test", "test", "test", Role.USER)));
//        System.out.println(messageStorageDB.contains(new User(3, "test", "test", "test", Role.USER)));
    }
    @Override
    public boolean addMessage(Message message) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages VALUES(DEFAULT, ?, ?, ?, ?)");
            preparedStatement.setLong(1, message.getDialogId());
            preparedStatement.setLong(2, message.getUser().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            Util.closeConnection(connection);
        }
        return true;
    }

    @Override
    public boolean deleteMessage(Message message) {
        return false;
    }

    @Override
    public boolean deleteMessageById(long id) {
        return false;
    }

    @Override
    public Message getById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select m.id, m.dialog_id, u.id, u.name, u.login, u.password, u.role, m.text, m.date  from  messages m join users u on m.user_id = u.id where m.id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return MessageMapper.getMessage(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Message> getMessageByDialogId(long dialogId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select m.id, m.dialog_id, u.id, u.name, u.login, u.password, u.role, m.text, m.date  from  messages m join users u on m.user_id = u.id where m.dialog_id = ?");

            preparedStatementUser.setLong(1, dialogId);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return MessageMapper.getMessageList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Message> getMessageByUser(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select m.id, m.dialog_id, u.id, u.name, u.login, u.password, u.role, m.text, m.date  from  messages m join users u on m.user_id = u.id where m.user_id = ?");

            preparedStatementUser.setLong(1, user.getId());

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return MessageMapper.getMessageList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Message> getMessageByDate(Date date) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select m.id, m.dialog_id, u.id, u.name, u.login, u.password, u.role, m.text, m.date  from  messages m join users u on m.user_id = u.id where m.date = ?");

            preparedStatementUser.setTimestamp(1, (Timestamp) date);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return MessageMapper.getMessageList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Message> getAllMessage() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select m.id, m.dialog_id, u.id, u.name, u.login, u.password, u.role, m.text, m.date  from  messages m join users u on m.user_id = u.id");

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return MessageMapper.getMessageList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(Message message) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  messages WHERE id = ? AND dialog_id = ? AND user_id = ? AND text = ?");
            preparedStatement.setLong(1, message.getId());
            preparedStatement.setLong(2, message.getDialogId());
            preparedStatement.setLong(3, message.getUser().getId());
            preparedStatement.setString(4, message.getText());
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  messages WHERE id = ?");
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
    public boolean containsByDialogId(long dialogId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  messages WHERE dialog_id = ?");
            preparedStatement.setLong(1, dialogId);
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
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  messages WHERE user_id = ?");
            preparedStatement.setLong(1, user.getId());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(Date date) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  messages WHERE date = ?");
            preparedStatement.setTimestamp(1, (Timestamp) date);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }
}
