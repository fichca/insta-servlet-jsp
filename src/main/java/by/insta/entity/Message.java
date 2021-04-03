package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private long id;
    private long dialogId;
    private User user;
    private String text;
    private Date date = new Date();

    public Message(long dialogId, User user, String text) {
        this.dialogId = dialogId;
        this.user = user;
        this.text = text;
    }
}
