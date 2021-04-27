package by.insta.service;

import by.insta.entity.Comment;

import java.util.List;

public interface CommentService {
    boolean add(Comment comment);

    Comment getById(long id);

    List<Comment> getCommentsPage(int start, long postId);

    int getCountCommentsPage(long postId);

    List<Comment> getAllCommentsByPostId(long postId);

    List<Comment> getAll();
}
