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
        for (User user : users) {
            if (user.getId() == id){
                return user;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        } throw new NoSuchElementException();
    }

    @Override
    public boolean deleteUserById(long id) {
        for (User user : users) {
            if (user.getId() == id){
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)){
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUserByUser(User user) {
        for (User user1 : users) {
            if (user1.equals(user)){
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateNameById(long id, String name) {
        for (User user : users) {
            if (user.getId() == id){
                if (user.getName().equals(name)){
                    return false;
                }
                user.setName(name);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePasswordById(long id, String password) {
        for (User user : users) {
            if (user.getId() == id){
                if (user.getPassword().equals(password)){
                    return false;
                }
                user.setPassword(password);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<User> getAllUsersByName(String name) {
        ArrayList<User> usersByName = new ArrayList<>();
        for (User user : users) {
            if (user.getName().equals(name)){
                usersByName.add(user);
            }
        }
        return usersByName;
    }

    @Override
    public boolean contains(User user) {
        return users.contains(user);
    }

    @Override
    public boolean contains(long id) {
        for (User user : users) {
            if (user.getId() == id){
                return true;
            }
        }
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
