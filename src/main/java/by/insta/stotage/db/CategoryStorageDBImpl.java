package by.insta.stotage.db;

import by.insta.entity.Category;
import by.insta.stotage.CategoryStorage;
import by.insta.stotage.db.mapper.CategoryMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;

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
            return CategoryMapper.getCategory(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Category getCategoryByName(String name) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from categories where name = ?");

            preparedStatementUser.setString(1, name);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return CategoryMapper.getCategory(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Category> getAllCategory() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select * from  categories");
            ResultSet resultSet = preparedStatementUser.executeQuery();
            return CategoryMapper.getCategoryList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(Category category) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from categories WHERE id = ? AND name = ?");
            preparedStatement.setLong(1, category.getId());
            preparedStatement.setString(2, category.getName());
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from categories WHERE id = ?");
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
    public boolean contains(String name) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from categories WHERE name = ?");
            preparedStatement.setString(1, name);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }
}
