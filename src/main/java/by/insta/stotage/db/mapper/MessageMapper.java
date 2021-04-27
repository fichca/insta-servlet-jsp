package by.insta.stotage.db.mapper;

import by.insta.entity.Message;
import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageMapper {
    public static Message getMessage(ResultSet resultSet){

        try {
            resultSet.next();
            return createMessage(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<Message> getMessageList(ResultSet resultSet){

        List<Message> messages = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        Message message = createMessage(resultSet);
                        messages.add(message);
                    }
                    return messages;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static Message createMessage(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        long dialogId = resultSet.getLong(2);
        long userId = resultSet.getLong(3);
        String name = resultSet.getString(4);
        String login = resultSet.getString(5);
        String password = resultSet.getString(6);
        String role = resultSet.getString(7);
        String text = resultSet.getString(8);
        Timestamp timestamp = resultSet.getTimestamp(9);
        return new Message(id, dialogId, new User(userId, name, login, password, Role.valueOf(role)), text, timestamp);
    }
}
