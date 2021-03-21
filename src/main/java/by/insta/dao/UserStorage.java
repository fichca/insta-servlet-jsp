package by.insta.dao;

import by.insta.entity.User;

import java.util.List;

public interface UserStorage {

    boolean addUser(User user);

    User getUserById(long id);

    User getUserByLogin(String login);

    List<User> getAllUsers();

    boolean contains(User user);

    boolean contains(long id);

    boolean contains(String login);
}
