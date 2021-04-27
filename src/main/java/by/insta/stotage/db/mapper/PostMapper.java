package by.insta.stotage.db.mapper;

import by.insta.entity.*;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostMapper {
    public static Post getPost(ResultSet resultSet){

        try {
            resultSet.next();
            return createPost(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<Post> getPostList(ResultSet resultSet){

        List<Post> posts = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        Post post = createPost(resultSet);
                        posts.add(post);
                    }
                    return posts;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static Post createPost(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
//        byte[] img = resultSet.getBytes(2);
        String title = resultSet.getString(2);
        String description = resultSet.getString(3);
        long categoryId = resultSet.getLong(4);
        String categoryName = resultSet.getString(5);
        long userId = resultSet.getLong(6);
        String userName = resultSet.getString(7);
        String login = resultSet.getString(8);
        String password = resultSet.getString(9);
        String role = resultSet.getString(10);
        boolean approved = resultSet.getBoolean(11);
        return new Post(id, title,description, new Category(categoryId, categoryName), new User(userId, userName, login, password, Role.valueOf(role)), approved);
    }
}
