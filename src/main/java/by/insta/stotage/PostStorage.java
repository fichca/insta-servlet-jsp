package by.insta.stotage;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;

import java.util.List;

public interface PostStorage {

    boolean add(Post post);

    //    void addCountViewByPostId(long postId);
    void addUserViewByPost(Post post, User user);

    boolean delete(Post post);

    boolean delete(long id);

    void approvePost(Post post);

    void rejectPost(Post post);

    Post getById(long id);

    Post getByTitle(String title);

    List<Post> getPage(int start);

    int getCountPages();

    List<Post> getAll();

    List<Post> getAllApprove();

    List<Post> getAllNotApprove();

    List<Post> getAllByUser(User user);

    List<Post> getAllByCategory(Category category);

    boolean contains(String title);

    boolean contains(Post post);

    boolean contains(long id);

}
