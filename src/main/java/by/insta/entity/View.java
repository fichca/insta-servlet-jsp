package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class View {
    private long id;
    private long postId;
    private List<User> userViews = new ArrayList<>();

    public View(long postId, List<User> userViews) {
        this.postId = postId;
        this.userViews = userViews;
    }
}
