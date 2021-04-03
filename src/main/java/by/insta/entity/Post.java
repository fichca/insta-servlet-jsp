package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private long id;
    private URL url;
    private String title;
    private String description;
    private Category category;
    private User user;
    private List<Integer> countView = new LinkedList<>();
    private List<Comment> comments;
    private List<Like> likes;
    private boolean approved = false;

    public Post(URL url, String title, String description, Category category, User user) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
    }
}
