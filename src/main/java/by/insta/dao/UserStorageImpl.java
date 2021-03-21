package by.insta.dao;

import by.insta.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserStorageImpl implements UserStorage {


    private static final List<User> users = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        int size = users.size();
        user.setId(++size);
        return users.add(user);
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        } throw new NoSuchElementException();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean contains(User user) {
        return users.contains(user);
    }

    @Override
    public boolean contains(long id) {
        return false;
    }

    @Override
    public boolean contains(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}
