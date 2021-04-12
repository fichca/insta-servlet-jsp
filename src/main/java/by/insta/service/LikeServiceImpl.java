package by.insta.service;

import by.insta.stotage.LikeStorage;
import by.insta.entity.Like;
import by.insta.entity.User;

import java.util.List;
import java.util.NoSuchElementException;

public class LikeServiceImpl implements LikeService {

    private final LikeStorage likeStorage;

    public LikeServiceImpl(LikeStorage likeStorage) {
        this.likeStorage = likeStorage;
    }

    @Override
    public boolean addLike(Like like) {
        if (likeStorage.contains(like)) return false;
        return likeStorage.addLike(like);
    }

    @Override
    public boolean deleteLikeByUser(User user) {
        if (likeStorage.contains(user)){
            return likeStorage.deleteLikeByUser(user);
        }
        return false;
    }

    @Override
    public boolean deleteLikeById(long id) {
        if (likeStorage.contains(id)){
            return likeStorage.deleteLikeById(id);
        }
        return false;
    }

    @Override
    public boolean deleteLikeByPostId(long postId) {
        return false;
    }

    @Override
    public Like getLikeById(long id) {
        if (likeStorage.contains(id)){
            likeStorage.getLikeById(id);
        }throw new NoSuchElementException();
    }

    @Override
    public List<Like> getLikesPage(int start, long postId) {
        return likeStorage.getLikesPage(start, postId);
    }

    @Override
    public int getCountLikesPage(long postId) {
        return likeStorage.getCountLikesPage(postId);
    }

    @Override
    public List<Like> getAllLikesByPostId(long postId) {
        return likeStorage.getAllLikesByPostId(postId);
    }

    @Override
    public List<Like> getAllLikes() {
        return likeStorage.getAllLikes();
    }
}
