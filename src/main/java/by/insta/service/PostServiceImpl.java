package by.insta.service;

import by.insta.dao.PostStorage;
import by.insta.entity.Post;

import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

public class PostServiceImpl implements PostService {

    private final PostStorage postStorage;

    public PostServiceImpl(PostStorage postStorage) {
        this.postStorage = postStorage;
    }


    @Override
    public boolean add(URL url, String title, String description) {
        if (!postStorage.contains(title)) {
            return postStorage.add(url, title, description);
        }
        return false;
    }

    @Override
    public Post getById(int id) {
        if (postStorage.contains(id)) {
            return postStorage.getById(id);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Post getByTitle(String title) {
        if (postStorage.contains(title)){
            return postStorage.getByTitle(title);
        } throw new NoSuchElementException();
    }

    @Override
    public List<Post> getPage(int start) {
        return postStorage.getPage(start);
    }

    @Override
    public int getCountPages() {
        return postStorage.getCountPages();
    }

    @Override
    public List<Post> getAll() {
        return postStorage.getAll();
    }
}
