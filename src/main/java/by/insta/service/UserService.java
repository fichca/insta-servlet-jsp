package by.insta.service;

import by.insta.entity.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUserById(long id);

    User getUserByLogin(String login);

    List<User> getAllUsers();

}
