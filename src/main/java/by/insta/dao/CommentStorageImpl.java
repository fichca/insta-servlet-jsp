package by.insta.dao;

import by.insta.Constant;
import by.insta.entity.Comment;

import java.util.*;

public class CommentStorageImpl implements CommentStorage {

    private final static LinkedList<Comment> COMMENTS = new LinkedList<>();

    @Override
    public boolean add(Comment comment) {
        int size = COMMENTS.size();
        comment.setId(++size);
        COMMENTS.addFirst(comment);
        return true;
    }

    @Override
    public Comment getById(int id) {

        for (Comment comment : COMMENTS) {
            if (comment.getId() == id){
                return comment;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Comment> getCommentsPage(int start, long postId) {
        List<Comment> allCommentsByPostId = getAllCommentsByPostId(postId);
        if (allCommentsByPostId.isEmpty()){
            return allCommentsByPostId;
        }
        int end = start;
        for (int i = start; i < allCommentsByPostId.size(); i++) {
            if (end == start + Constant.COMMENT_ON_PAGE){
                break;
            }
            end++;
        }
        return allCommentsByPostId.subList(start, end);
    }

    @Override
    public int getCountCommentsPage(long postId) {
        List<Comment> allByPostId = getAllByPostId(postId);
        if (allByPostId.isEmpty()){
            return 1;
        }
        return ((allByPostId.size() - 1) / Constant.COMMENT_ON_PAGE) + 1;
    }

    @Override
    public List<Comment> getAllCommentsByPostId(long postId) {
        return getAllByPostId(postId);
    }

    private List<Comment> getAllByPostId(long postId) {
        ArrayList<Comment> commentsByPost = new ArrayList<>();
        for (Comment comment : COMMENTS) {
            if (comment.getPostId() == postId){
                commentsByPost.add(comment);
            }
        }
        return commentsByPost;
    }


    @Override
    public List<Comment> getAll() {
        return COMMENTS;
    }

    @Override
    public boolean contains(Comment comment) {
        return COMMENTS.contains(comment);
    }

    @Override
    public boolean contains(int id) {
        for (Comment comment : COMMENTS) {
            if (comment.getId() == id){
                return true;
            }
        } return false;
    }
}
