package by.insta.stotage.db;

import by.insta.entity.Category;
import by.insta.stotage.CategoryStorage;
import by.insta.stotage.db.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoryStorageDBImpl implements CategoryStorage {

    private DataSource dataSource;

    public CategoryStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addCategory(Category category) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories VALUES(DEFAULT, ?)");

            preparedStatement.setString(1, category.getName());
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
    public boolean deleteCategoryById(long id) {
        return false;
    }

    @Override
    public boolean deleteCategoryByName(String name) {
        return false;
    }

    @Override
    public Category getCategoryById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from categories where id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            preparedStatementUser.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        return null;
    }

    @Override
    public boolean contains(Category category) {
        return false;
    }

    @Override
    public boolean contains(long id) {
        return false;
    }

    @Override
    public boolean contains(String name) {
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
