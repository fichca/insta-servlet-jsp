package by.insta.stotage.db.mapper;

import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User getUser(ResultSet resultSet){

        try {
            resultSet.next();
            return createUser(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<User> getUserList(ResultSet resultSet){

        List<User> users = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        User user = createUser(resultSet);
                        users.add(user);
                    }
                    return users;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static User createUser(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String login = resultSet.getString(3);
        String password = resultSet.getString(4);
        String role = resultSet.getString(5);

        return new User(id, name, login, password, Role.valueOf(role));
    }
}
