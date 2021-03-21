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
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        if (userStorage.contains(login)){
            return userStorage.getUserByLogin(login);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

}
