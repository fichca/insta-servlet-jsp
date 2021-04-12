package by.insta.stotage.db;

import by.insta.entity.Comment;
import by.insta.stotage.CommentStorage;

import java.util.List;

public class CommentStorageDBImpl implements CommentStorage {
    @Override
    public boolean add(Comment comment) {
        return false;
    }

    @Override
    public Comment getById(int id) {
        return null;
    }

    @Override
    public List<Comment> getCommentsPage(int start, long postId) {
        return null;
    }

    @Override
    public int getCountCommentsPage(long postId) {
        return 0;
    }

    @Override
    public List<Comment> getAllCommentsByPostId(long postId) {
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public boolean contains(Comment comment) {
        return false;
    }

    @Override
    public boolean contains(int id) {
        return false;
    }
}
