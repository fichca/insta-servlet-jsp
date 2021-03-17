package by.insta.dao;

import by.insta.Constant;
import by.insta.entity.Comment;

import java.util.*;

public class CommentStorageImpl implements CommentStorage {

    private final static LinkedList<Comment> comments = new LinkedList<>();

    @Override
    public boolean add(long postId, String comment) {
        int size = comments.size();
        Comment newComment = new Comment(postId, ++size, comment);
        comments.addFirst(newComment);
        return true;
    }

    @Override
    public Comment getById(int id) {

        for (Comment comment : comments) {
            if (comment.getId() == id){
                return comment;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Comment> getCommentsPage(int start, long postId) {
        if (comments.isEmpty()){
            return comments;
        }
        int end = start;
        for (int i = start; i < comments.size(); i++) {
            if (end == start + Constant.COMMENT_ON_PAGE){
                break;
            }
            end++;
        }
        return comments.subList(start, end);
    }

    @Override
    public int getCountCommentsPage(long postId) {
        if (comments.isEmpty()){
            return 1;
        }
        return ((comments.size() - 1) / Constant.COMMENT_ON_PAGE) + 1;
    }

    @Override
    public List<Comment> getAllByPostId(long postId) {
        ArrayList<Comment> commentsByPost = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getPostId() == postId){
                commentsByPost.add(comment);
            }
        }
        return commentsByPost;
    }

    @Override
    public List<Comment> getAll() {
        return comments;
    }

    @Override
    public boolean contains(Comment comment) {
        return comments.contains(comment);
    }

    @Override
    public boolean contains(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id){
                return true;
            }
        } return false;
    }
}
