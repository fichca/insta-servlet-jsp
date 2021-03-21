package by.insta.dao;

import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.List;

public interface PostStorage {

    boolean add(Post post);

    Post getById(int id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();

    boolean contains(String title);

    boolean contains(Post post);

    boolean contains(int id);

}
