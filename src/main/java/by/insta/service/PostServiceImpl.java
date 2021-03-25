package by.insta.service;

import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorage;
import by.insta.entity.Category;
import by.insta.entity.Like;
import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

public class PostServiceImpl implements PostService {

    private final PostStorage postStorage;
    LikeService likeService = new LikeServiceImpl(new LikeStorageImpl());

    public PostServiceImpl(PostStorage postStorage) {
        this.postStorage = postStorage;
    }


    @Override
    public boolean add(Post post) {
        if (!postStorage.contains(post.getTitle())) {
            return postStorage.add(post);
        }
        return false;
    }

    @Override
    public Post getById(long id) {
        if (postStorage.contains(id)) {
            List<Like> allLikesByPostId = likeService.getAllLikesByPostId(id);
            Post post = postStorage.getById(id);
            post.setLikes(allLikesByPostId);
            return post;
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

    @Override
    public List<Post> getAllByUser(User user) {
        return postStorage.getAllByUser(user);
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return postStorage.getAllByCategory(category);
    }
}
