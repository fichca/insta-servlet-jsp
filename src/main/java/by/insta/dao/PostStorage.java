package by.insta.dao;

import by.insta.entity.Post;

import java.net.URL;
import java.util.List;

public interface PostStorage {

    boolean add(URL url, String title, String description);

    Post getById(int id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();

    boolean contains(String title);

    boolean contains(Post post);

    boolean contains(int id);

}
