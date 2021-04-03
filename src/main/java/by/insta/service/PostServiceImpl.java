package by.insta.service;

import by.insta.dao.LikeStorage;
import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorage;
import by.insta.entity.Category;
import by.insta.entity.Like;
import by.insta.entity.Post;
import by.insta.entity.User;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class PostServiceImpl implements PostService {

    private final PostStorage postStorage;
    private final LikeStorage likeStorage;

    public PostServiceImpl(PostStorage postStorage, LikeStorage likeStorage) {
        this.likeStorage = likeStorage;
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
    public boolean delete(Post post) {
        if (postStorage.contains(post)){
            return postStorage.delete(post);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (postStorage.contains(id)){
            return postStorage.delete(id);
        }
        return false;
    }

    @Override
    public Post getById(long id) {
        if (postStorage.contains(id)) {
            List<Like> allLikesByPostId = likeStorage.getAllLikesByPostId(id);
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
    public List<Post> getAllApprove() {
        return postStorage.getAllApprove();
    }

    @Override
    public List<Post> getAllNotApprove() {
        return postStorage.getAllNotApprove();
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return postStorage.getAllByUser(user);
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return postStorage.getAllByCategory(category);
    }

    @Override
    public List<Post> getAllBySubscribers(User user) {
        List<User> subscribers = user.getSubscribers();
        return getAllPostsUsers(subscribers);
    }

    @Override
    public List<Post> getAllBySubscriptions(User user) {
        List<User> subscriptions = user.getSubscriptions();
        return getAllPostsUsers(subscriptions);
    }

    private List<Post> getAllPostsUsers(List<User> users){
        List<Post> posts = new LinkedList<>();
        List<Post> all = postStorage.getAllApprove();

        for (Post post : all) {
            User userByPost = post.getUser();
            if (users.contains(userByPost)){
                posts.add(post);
            }
        }
        return posts;
    }
}
