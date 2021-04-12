package by.insta.stotage.db;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.stotage.PostStorage;

import java.util.List;

public class PostStorageDBImpl implements PostStorage {
    @Override
    public boolean add(Post post) {
        return false;
    }

    @Override
    public boolean delete(Post post) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Post getById(long id) {
        return null;
    }

    @Override
    public Post getByTitle(String title) {
        return null;
    }

    @Override
    public List<Post> getPage(int start) {
        return null;
    }

    @Override
    public int getCountPages() {
        return 0;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public List<Post> getAllApprove() {
        return null;
    }

    @Override
    public List<Post> getAllNotApprove() {
        return null;
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return null;
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return null;
    }

    @Override
    public boolean contains(String title) {
        return false;
    }

    @Override
    public boolean contains(Post post) {
        return false;
    }

    @Override
    public boolean contains(long id) {
        return false;
    }
}
