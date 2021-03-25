package by.insta.service;

import by.insta.dao.UserStorage;
import by.insta.entity.User;

import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService {
    private  final UserStorage userStorage;

    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    @Override
    public boolean addUser(User user) {
        if (userStorage.contains(user.getLogin())){
            return false;
        }
        return userStorage.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        if (userStorage.contains(id)){
            return userStorage.getUserById(id);
        }
        throw new NoSuchElementException();
    }

    @Override
    public User getUserByLogin(String login) {
        if (userStorage.contains(login)){
            return userStorage.getUserByLogin(login);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean deleteUserById(long id) {
        if (userStorage.contains(id)){
            return userStorage.deleteUserById(id);
        }
        return false;
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        if (userStorage.contains(login)){
            return userStorage.deleteUserByLogin(login);
        }
        return false;
    }

    @Override
    public boolean deleteUserByUser(User user) {
        if (userStorage.contains(user)){
            return userStorage.deleteUserByUser(user);
        }
        return false;
    }

    @Override
    public boolean updateNameById(long id, String name) {
        if (userStorage.contains(id)){
            return userStorage.updateNameById(id, name);
        }
        return false;
    }

    @Override
    public boolean updatePasswordById(long id, String password) {
        if (userStorage.contains(id)){
            return userStorage.updateNameById(id, password);
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    @Override
    public List<User> getAllUsersByName(String name) {
        return userStorage.getAllUsersByName(name);
    }
}
