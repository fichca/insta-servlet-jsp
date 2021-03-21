package by.insta.dao;

import by.insta.entity.Like;
import by.insta.entity.User;

import java.util.List;

public interface LikeStorage {

    boolean addLike(Like like);

    boolean deleteLikeByUser(User user);

    boolean deleteLikeById(long id);

    boolean deleteLikeByPostId(long postId);

    Like getLikeById(long id);

    List<Like> getLikesPage(int start, long postId);

    int getCountLikesPage(long postId);

    List<Like> getAllLikesByPostId(long postId);

    List<Like> getAllLikes();

    boolean contains(Like like);

    boolean contains(long id);

    boolean contains(User user);

}
