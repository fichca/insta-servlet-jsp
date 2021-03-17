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
    private List<Comment> comments;

    public Post(long id, URL url, String title, String description) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
    }
}
