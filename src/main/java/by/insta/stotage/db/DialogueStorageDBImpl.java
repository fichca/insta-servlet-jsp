package by.insta.stotage.db;

import by.insta.entity.Dialogue;
import by.insta.entity.Role;
import by.insta.entity.User;
import by.insta.stotage.DialogueStorage;
import by.insta.stotage.db.mapper.DialogMapper;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;

public class DialogueStorageDBImpl implements DialogueStorage {

    private DataSource dataSource;

    public DialogueStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addDialog(Dialogue dialogue) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dialogues VALUES(DEFAULT, ?, ?)");

            preparedStatement.setLong(1, dialogue.getFirstUser().getId());
            preparedStatement.setLong(2, dialogue.getSecondUser().getId());
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
    public boolean deleteDialog(Dialogue dialogue) {
        return false;
    }

    @Override
    public boolean deleteDialogById(long id) {
        return false;
    }

    @Override
    public Dialogue getById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select d.id, u.id, u.name, u.login, u.password, u.role, us.id, us.name, us.login, us.password, us.role  from dialogues d join users u on d.first_user_id = u.id join users us on d.second_user_id = us.id where d.id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return DialogMapper.getDialog(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Dialogue getByUsers(User firstUser, User secondUser) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select d.id, u.id, u.name, u.login, u.password, u.role, us.id, us.name, us.login, us.password, us.role  from dialogues d join users u on d.first_user_id = u.id join users us on d.second_user_id = us.id where d.first_user_id = ? AND d.second_user_id = ? OR d.first_user_id = ? AND d.second_user_id = ?");

            preparedStatementUser.setLong(1, firstUser.getId());
            preparedStatementUser.setLong(2, secondUser.getId());

            preparedStatementUser.setLong(3, secondUser.getId());
            preparedStatementUser.setLong(4, firstUser.getId());

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return DialogMapper.getDialog(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Dialogue> getDialogsByUser(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select d.id, u.id, u.name, u.login, u.password, u.role, us.id, us.name, us.login, us.password, us.role  from dialogues d join users u on d.first_user_id = u.id join users us on d.second_user_id = us.id where d.first_user_id = ? or  d.second_user_id = ?");

            preparedStatementUser.setLong(1, user.getId());
            preparedStatementUser.setLong(2, user.getId());


            ResultSet resultSet = preparedStatementUser.executeQuery();
            return DialogMapper.getDialogsList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Dialogue> getAllDialogs() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select d.id, u.id, u.name, u.login, u.password, u.role, us.id, us.name, us.login, us.password, us.role  from dialogues d join users u on d.first_user_id = u.id join users us on d.second_user_id = us.id");

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return DialogMapper.getDialogsList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(Dialogue dialogue) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from dialogues where id = ? AND first_user_id = ? AND second_user_id = ? OR id = ? AND first_user_id = ? AND second_user_id = ?");

            preparedStatementUser.setLong(1, dialogue.getId());
            preparedStatementUser.setLong(2, dialogue.getFirstUser().getId());
            preparedStatementUser.setLong(3, dialogue.getSecondUser().getId());

            preparedStatementUser.setLong(4, dialogue.getId());
            preparedStatementUser.setLong(5, dialogue.getSecondUser().getId());
            preparedStatementUser.setLong(6, dialogue.getFirstUser().getId());

            return preparedStatementUser.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from dialogues where id = ?");

            preparedStatementUser.setLong(1, id);

            return preparedStatementUser.executeQuery().next();
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
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from dialogues where first_user_id = ? or second_user_id = ?");

            preparedStatementUser.setLong(1, user.getId());
            preparedStatementUser.setLong(2, user.getId());

            return preparedStatementUser.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(User firstUser, User secondUser) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from dialogues where first_user_id = ? AND second_user_id = ? OR first_user_id = ? AND second_user_id = ?");

            preparedStatementUser.setLong(1, firstUser.getId());
            preparedStatementUser.setLong(2, secondUser.getId());

            preparedStatementUser.setLong(3, secondUser.getId());
            preparedStatementUser.setLong(4, firstUser.getId());

            return preparedStatementUser.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }
}
