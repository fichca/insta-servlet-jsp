package by.insta.service;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.List;

public interface PostService {

    boolean add(Post post);

    Post getById(long id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();

    List<Post> getAllByUser(User user);

    List<Post> getAllByCategory(Category category);
}
