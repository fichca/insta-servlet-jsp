package by.insta.stotage.db.mapper;

import by.insta.entity.Category;
import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public static Category getCategory(ResultSet resultSet){

        try {
            resultSet.next();
            return createCategory(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<Category> getCategoryList(ResultSet resultSet){

        List<Category> categories = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        Category category = createCategory(resultSet);
                        categories.add(category);
                    }
                    return categories;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static Category createCategory(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);

        return new Category(id, name);
    }
}
