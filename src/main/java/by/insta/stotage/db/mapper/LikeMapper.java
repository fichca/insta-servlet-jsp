package by.insta.stotage.db.mapper;

import by.insta.entity.Comment;
import by.insta.entity.Like;
import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LikeMapper {

    public static Like getLike(ResultSet resultSet){
        try {
            resultSet.next();
            return createLike(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<Like> getLikeList(ResultSet resultSet){

        List<Like> likes = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        Like like = createLike(resultSet);
                        likes.add(like);
                    }
                    return likes;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static Like createLike(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        long postId = resultSet.getLong(2);
        long userId = resultSet.getLong(3);
        String name = resultSet.getString(4);
        String login = resultSet.getString(5);
        String password = resultSet.getString(6);
        String role = resultSet.getString(7);
        Timestamp timestamp = resultSet.getTimestamp(8);
        return new Like(id, postId, new User(userId, name, login, password, Role.valueOf(role)), timestamp);
    }

}
