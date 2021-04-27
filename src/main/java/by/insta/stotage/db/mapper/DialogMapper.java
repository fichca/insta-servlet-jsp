package by.insta.stotage.db.mapper;

import by.insta.entity.Comment;
import by.insta.entity.Dialogue;
import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DialogMapper {

    public static Dialogue getDialog(ResultSet resultSet){

        try {
            resultSet.next();
            return createDialog(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoConnectionPendingException();
    }

    public static List<Dialogue> getDialogsList(ResultSet resultSet){

        List<Dialogue> dialogues = new ArrayList<>();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        Dialogue dialog = createDialog(resultSet);
                        dialogues.add(dialog);
                    }
                    return dialogues;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static Dialogue createDialog(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong(1);

        long idByFirstUser = resultSet.getLong(2);
        String nameByFirstUser = resultSet.getString(3);
        String loginByFirstUser = resultSet.getString(4);
        String passwordByFirstUser = resultSet.getString(5);
        String roleByFirstUser = resultSet.getString(6);

        long idBySecondUser = resultSet.getLong(7);
        String nameBySecondUser = resultSet.getString(8);
        String loginBySecondUser = resultSet.getString(9);
        String passwordBySecondUser = resultSet.getString(10);
        String roleBySecondUser = resultSet.getString(11);
        User firstUser = new User(idByFirstUser, nameByFirstUser, loginByFirstUser, passwordByFirstUser, Role.valueOf(roleByFirstUser));
        User secondUser = new User(idBySecondUser, nameBySecondUser, loginBySecondUser, passwordBySecondUser, Role.valueOf(roleBySecondUser));

        return new Dialogue(id, firstUser, secondUser);
    }
}
