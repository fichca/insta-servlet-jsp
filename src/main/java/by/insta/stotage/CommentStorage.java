package by.insta.stotage;

import by.insta.entity.Comment;

import java.util.List;

public interface CommentStorage {

    boolean add(Comment comment);

    Comment getById(long id);

    List<Comment> getCommentsPage(int start, long postId);

    int getCountCommentsPage(long postId);

    List<Comment> getAllCommentsByPostId(long postId);

    List<Comment> getAll();

    boolean contains(Comment comment);

    boolean contains(long id);
}
