package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private long id;
    private long postId;
    private User user;

    public Like(long postId, User user) {
        this.postId = postId;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return postId == like.postId && Objects.equals(user, like.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, user);
    }
}
