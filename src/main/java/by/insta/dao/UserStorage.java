package by.insta.dao;

import by.insta.entity.User;

import java.util.List;

public interface UserStorage {

    boolean addUser(User user);

    boolean addSubscriber(User user, User subscriber);

    User getUserById(long id);

    User getUserByLogin(String login);

    boolean deleteUserById(long id);

    boolean deleteSubscriber(User user, User subscriber);

    boolean deleteUserByLogin(String login);

    boolean deleteUserByUser(User user);

    boolean updateNameById(long id, String name);

    boolean updatePasswordById(long id, String password);

    List<User> getAllUsers();

    List<User> getAllUsersByName(String name);

    boolean contains(User user);

    boolean contains(long id);

    boolean contains(String login);
}
