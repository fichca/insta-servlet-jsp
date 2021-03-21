package by.insta.dao;

import by.insta.Constant;
import by.insta.entity.Comment;
import by.insta.entity.Like;
import by.insta.entity.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class LikeStorageImpl implements LikeStorage {

    private static final List<Like> likes = new ArrayList<>();

    @Override
    public boolean addLike(Like like) {
        int size = likes.size();
        like.setId(++size);
        return likes.add(like);
    }

    @Override
    public boolean deleteLikeByUser(User user) {
        for (Like like : likes) {
            if (like.getUser().equals(user)){
                likes.remove(like);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLikeById(long id) {
        for (Like like : likes) {
            if (like.getId() == id){
                likes.remove(like);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLikeByPostId(long postId) {
        for (Like like : likes) {
            if (like.getPostId() == postId){
                likes.remove(like);
                return true;
            }
        }
        return false;
    }


    @Override
    public Like getLikeById(long id) {
        for (Like like : likes) {
            if (like.getId() == id){
                return like;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Like> getLikesPage(int start, long postId) {
        if (likes.isEmpty()){
            return likes;
        }
        int end = start;
        for (int i = start; i < likes.size(); i++) {
            if (end == start + Constant.LIKES_ON_PAGE){
                break;
            }
            end++;
        }
        return likes.subList(start, end);
    }

    @Override
    public int getCountLikesPage(long postId) {
        if (likes.isEmpty()){
            return 1;
        }
        return ((likes.size() - 1) / Constant.LIKES_ON_PAGE) + 1;
    }

    @Override
    public List<Like> getAllLikesByPostId(long postId) {
        List<Like> likesByPost = new ArrayList<>();
        for (Like like : likes) {
            if (like.getPostId() == postId){
                likesByPost.add(like);
            }
        }
        return likesByPost;
    }

    @Override
    public List<Like> getAllLikes() {
        return likes;
    }

    @Override
    public boolean contains(Like like) {
        return likes.contains(like);
    }

    @Override
    public boolean contains(long id) {
        for (Like like : likes) {
            if (like.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (Like like : likes) {
            if (like.getUser().equals(user)){
                return true;
            }
        }
        return false;
    }
}
