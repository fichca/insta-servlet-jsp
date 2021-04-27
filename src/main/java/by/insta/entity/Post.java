package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private long id;
    private String title;
    private String description;
    private Category category;
    private User user;
    private List<String> imgStringBase64;
    private Set <User> views =new HashSet<>();
    private List<Comment> comments;
    private List<Like> likes;
    private boolean approved = false;


    public Post(String title, String description, Category category, User user, List<String> imgStringBase64) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.imgStringBase64 = imgStringBase64;
    }

    public Post(long id, String title, String description, Category category, User user, boolean approved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.approved = approved;
    }

    public Post(long id, String title, String description, Category category, User user, List<String> imgStringBase64, Set<User> views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.imgStringBase64 = imgStringBase64;
        this.views = views;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
//                ", url=" + url +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", user=" + user +
//                ", countView=" + countView +
                ", comments=" + comments +
                ", likes=" + likes +
                ", approved=" + approved +
                '}';
    }
}
