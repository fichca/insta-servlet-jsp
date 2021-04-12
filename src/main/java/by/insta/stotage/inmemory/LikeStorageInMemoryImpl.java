package by.insta.stotage.inmemory;

import by.insta.Constant;
import by.insta.stotage.LikeStorage;
import by.insta.entity.Like;
import by.insta.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LikeStorageInMemoryImpl implements LikeStorage {

    private static final List<Like> LIKES = new ArrayList<>();

    @Override
    public boolean addLike(Like like) {
        int size = LIKES.size();
        like.setId(++size);
        return LIKES.add(like);
    }

    @Override
    public boolean deleteLikeByUser(User user) {
        for (Like like : LIKES) {
            if (like.getUser().equals(user)){
                LIKES.remove(like);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLikeById(long id) {
        for (Like like : LIKES) {
            if (like.getId() == id){
                LIKES.remove(like);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLikeByPostId(long postId) {
        for (Like like : LIKES) {
            if (like.getPostId() == postId){
                LIKES.remove(like);
                return true;
            }
        }
        return false;
    }


    @Override
    public Like getLikeById(long id) {
        for (Like like : LIKES) {
            if (like.getId() == id){
                return like;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Like> getLikesPage(int start, long postId) {
        if (LIKES.isEmpty()){
            return LIKES;
        }
        int end = start;
        for (int i = start; i < LIKES.size(); i++) {
            if (end == start + Constant.LIKES_ON_PAGE){
                break;
            }
            end++;
        }
        return LIKES.subList(start, end);
    }

    @Override
    public int getCountLikesPage(long postId) {
        if (LIKES.isEmpty()){
            return 1;
        }
        return ((LIKES.size() - 1) / Constant.LIKES_ON_PAGE) + 1;
    }

    @Override
    public List<Like> getAllLikesByPostId(long postId) {
        List<Like> likesByPost = new ArrayList<>();
        for (Like like : LIKES) {
            if (like.getPostId() == postId){
                likesByPost.add(like);
            }
        }
        return likesByPost;
    }

    @Override
    public List<Like> getAllLikes() {
        return LIKES;
    }

    @Override
    public boolean contains(Like like) {
        return LIKES.contains(like);
    }

    @Override
    public boolean contains(long id) {
        for (Like like : LIKES) {
            if (like.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (Like like : LIKES) {
            if (like.getUser().equals(user)){
                return true;
            }
        }
        return false;
    }
}
