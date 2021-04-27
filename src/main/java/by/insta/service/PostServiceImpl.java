package by.insta.service;

import by.insta.entity.Category;
import by.insta.entity.Like;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.stotage.CategoryStorage;
import by.insta.stotage.LikeStorage;
import by.insta.stotage.PostStorage;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PostServiceImpl implements PostService {

    private final PostStorage postStorage;
    private final LikeStorage likeStorage;
    private final CategoryStorage categoryStorage;

    public PostServiceImpl(PostStorage postStorage, LikeStorage likeStorage, CategoryStorage categoryStorage) {
        this.postStorage = postStorage;
        this.likeStorage = likeStorage;
        this.categoryStorage = categoryStorage;
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
        if (postStorage.contains(post)) {
            return postStorage.delete(post);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (postStorage.contains(id)) {

            return postStorage.delete(id);
        }
        return false;
    }

    @Override
    public void addUserViewByPost(Post post, User user) {
        if (postStorage.contains(post)) {
            postStorage.addUserViewByPost(post, user);
        }
    }


    @Override
    public void approvePost(Post post) {
        if (postStorage.contains(post)) {
            postStorage.approvePost(post);
        }
    }

    @Override
    public void rejectPost(Post post) {
        if (postStorage.contains(post)) {
            postStorage.rejectPost(post);
        }
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
        if (postStorage.contains(title)) {
            Post post = postStorage.getByTitle(title);
            List<Like> allLikesByPostId = likeStorage.getAllLikesByPostId(post.getId());
            post.setLikes(allLikesByPostId);
            return post;
        }
        throw new NoSuchElementException();
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
        return getAllByCategory(category);
    }

    @Override
    public List<Post> getAllByCategory(String categoryName) {
        Category category = categoryStorage.getCategoryByName(categoryName);
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

    @Override
    public List<String> getListImg(Collection<Part> parts) throws IOException {
        List<String> imgString = new ArrayList<>(3);
        Base64.Encoder encoder = Base64.getEncoder();
        for (Part part : parts) {
            String contentType = part.getContentType();
            if (contentType != null && contentType.equals("image/jpeg")) {
                InputStream inputStream = part.getInputStream();
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                String s = encoder.encodeToString(buffer);
                imgString.add(s);
            }
        }
        return imgString;
    }

    private List<Post> getAllPostsUsers(List<User> users) {
        List<Post> posts = new LinkedList<>();
        List<Post> all = postStorage.getAllApprove();

        for (Post post : all) {
            User userByPost = post.getUser();
            if (users.contains(userByPost)) {
                posts.add(post);
            }
        }
        return posts;
    }
}
