package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private long id;
    private URL url;
    private String title;
    private String description;
    private User user;
    private List<Comment> comments;
    private List<Like> likes;

    public Post(URL url, String title, String description, User user) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
