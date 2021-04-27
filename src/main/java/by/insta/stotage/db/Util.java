package by.insta.stotage.db;

import java.sql.Connection;
import java.sql.SQLException;

public class Util {

    protected static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
