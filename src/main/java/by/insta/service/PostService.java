package by.insta.service;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.List;

public interface PostService {

    boolean add(Post post);

    boolean delete (Post post);

    boolean delete (long id);

    Post getById(long id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();

    List<Post> getAllApprove();

    List<Post> getAllNotApprove();

    List<Post> getAllByUser(User user);

    List<Post> getAllByCategory(Category category);

    List<Post> getAllBySubscribers(User user);

    List<Post> getAllBySubscriptions(User user);
}
