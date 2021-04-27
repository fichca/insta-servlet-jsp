package by.insta.service;

import by.insta.entity.Post;
import by.insta.entity.View;

public interface ViewService {
    void addView(Post post);

    void delete(Post post);

    void delete(long id);

    View getViewByPost(Post post);

    View getViewById(long id);

    View getAllViews();
}
