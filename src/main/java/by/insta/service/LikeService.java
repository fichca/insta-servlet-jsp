package by.insta.service;

import by.insta.entity.Like;
import by.insta.entity.User;

import java.util.List;

public interface LikeService {

    boolean addLike(Like like);

    boolean deleteLikeByUser(User user);

    boolean deleteLikeById(long id);

    boolean deleteLikeByPostId(long postId);

    Like getLikeById(long id);

    List<Like> getLikesPage(int start, long postId);

    int getCountLikesPage(long postId);

    List<Like> getAllLikesByPostId(long postId);

    List<Like> getAllLikes();
}
