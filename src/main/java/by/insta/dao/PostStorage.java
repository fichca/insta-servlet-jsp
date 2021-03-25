package by.insta.dao;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.List;

public interface PostStorage {

    boolean add(Post post);

    Post getById(long id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();

    List<Post> getAllByUser(User user);

    List<Post> getAllByCategory(Category category);

    boolean contains(String title);

    boolean contains(Post post);

    boolean contains(long id);

}
