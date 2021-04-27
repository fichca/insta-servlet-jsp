package by.insta.stotage.db.mapper;

import by.insta.entity.Role;
import by.insta.entity.User;

import java.nio.channels.NoConnectionPendingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ImgMapper {


    public static List<String> getImgList(ResultSet resultSet){

        List<String> images = new ArrayList<>();
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        while (true){
            while (true) {
                try {
                    while (resultSet.next()) {
                        byte[] imgBytes = createImgBytes(resultSet);
                        byte[] decode = decoder.decode(imgBytes);
                        String s1 = encoder.encodeToString(decode);
                        images.add(s1);
                    }
                    return images;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }



    private static byte[] createImgBytes(ResultSet resultSet) throws SQLException {

        return resultSet.getBytes(1);
    }
}
