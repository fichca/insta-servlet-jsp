package by.insta.stotage.db.mapper;

import by.insta.entity.Comment;
import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static Comment getComment(ResultSet resultSet){

        try {
            resultSet.next();
            return createComment(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<Comment> getCommentList(ResultSet resultSet){

        List<Comment> comments = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        Comment comment = createComment(resultSet);
                        comments.add(comment);
                    }
                    return comments;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static Comment createComment(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        long postId = resultSet.getLong(2);
        String comment = resultSet.getString(3);
        long userId = resultSet.getLong(4);
        String name = resultSet.getString(5);
        String login = resultSet.getString(6);
        String password = resultSet.getString(7);
        String role = resultSet.getString(8);
        Timestamp timestamp = resultSet.getTimestamp(9);
        return new Comment(id, postId, comment,new User(userId, name, login, password, Role.valueOf(role)), timestamp);
    }
}
