package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private long id;
    private long postId;
    private String comment;
    private User user;
    private Date date = new Date();

    public Comment(long postId, String comment, User user) {
        this.postId = postId;
        this.comment = comment;
        this.user = user;
    }

}
