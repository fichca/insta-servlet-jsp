package by.insta.service;

import by.insta.dao.CommentStorage;
import by.insta.entity.Comment;

import java.util.List;
import java.util.NoSuchElementException;

public class CommentServiceImpl implements CommentService {

    private final CommentStorage commentStorage;

    public CommentServiceImpl(CommentStorage commentStorage) {
        this.commentStorage = commentStorage;
    }

    @Override
    public boolean add(long postId, String comment) {
        return commentStorage.add(postId, comment);
    }

    @Override
    public Comment getById(int id) {
        if (commentStorage.contains(id)){
            return commentStorage.getById(id);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Comment> getCommentsPage(int start, long postId) {
        return commentStorage.getCommentsPage(start, postId);
    }

    @Override
    public int getCountCommentsPage(long postId) {
        return commentStorage.getCountCommentsPage(postId);
    }

    @Override
    public List<Comment> getAllByPostId(long postId) {
        return commentStorage.getAllByPostId(postId);
    }

    @Override
    public List<Comment> getAll() {
        return commentStorage.getAll();
    }
}