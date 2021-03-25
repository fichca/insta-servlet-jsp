package by.insta.service;

import by.insta.entity.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUserById(long id);

    User getUserByLogin(String login);

    boolean deleteUserById(long id);

    boolean deleteUserByLogin(String login);

    boolean deleteUserByUser(User user);

    boolean updateNameById(long id, String name);

    boolean updatePasswordById(long id, String password);

    List<User> getAllUsers();

    List<User> getAllUsersByName(String name);

}
