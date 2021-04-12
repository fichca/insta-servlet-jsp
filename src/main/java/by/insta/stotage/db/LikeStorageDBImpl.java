package by.insta.stotage.db;

import by.insta.entity.Like;
import by.insta.entity.User;
import by.insta.stotage.LikeStorage;

import java.util.List;

public class LikeStorageDBImpl implements LikeStorage {
    @Override
    public boolean addLike(Like like) {
        return false;
    }

    @Override
    public boolean deleteLikeByUser(User user) {
        return false;
    }

    @Override
    public boolean deleteLikeById(long id) {
        return false;
    }

    @Override
    public boolean deleteLikeByPostId(long postId) {
        return false;
    }

    @Override
    public Like getLikeById(long id) {
        return null;
    }

    @Override
    public List<Like> getLikesPage(int start, long postId) {
        return null;
    }

    @Override
    public int getCountLikesPage(long postId) {
        return 0;
    }

    @Override
    public List<Like> getAllLikesByPostId(long postId) {
        return null;
    }

    @Override
    public List<Like> getAllLikes() {
        return null;
    }

    @Override
    public boolean contains(Like like) {
        return false;
    }

    @Override
    public boolean contains(long id) {
        return false;
    }

    @Override
    public boolean contains(User user) {
        return false;
    }
}
