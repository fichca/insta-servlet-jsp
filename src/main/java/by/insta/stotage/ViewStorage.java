package by.insta.stotage;

import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.entity.View;

public interface ViewStorage {
    void addView(View view);

    void addUserViewByPostId(long postId, User user);

    void delete(View view);

    void delete(long postId);

    View getViewByPostId(int postId);

    View getViewById(long id);

    View getAllViews();

    boolean contains(long id);

    boolean contains(View view);

    boolean contains(Post post);

}
