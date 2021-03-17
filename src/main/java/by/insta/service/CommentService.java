package by.insta.service;

import by.insta.entity.Comment;

import java.util.List;

public interface CommentService {
    boolean add(long postId, String comment);

    Comment getById(int id);

    List<Comment> getCommentsPage(int start, long postId);

    int getCountCommentsPage(long postId);

    List<Comment> getAllByPostId(long postId);

    List<Comment> getAll();
}
