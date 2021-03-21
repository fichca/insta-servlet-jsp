package by.insta.service;

import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.List;

public interface PostService {

    boolean add(Post post);

    Post getById(int id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();
}
