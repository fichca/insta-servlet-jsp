package by.insta.stotage.inmemory;

import by.insta.stotage.UserStorage;
import by.insta.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserStorageInMemoryImpl implements UserStorage {


    private static final List<User> USERS = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        int size = USERS.size();
        user.setId(++size);
        return USERS.add(user);
    }


    @Override
    public User getUserById(long id) {
        for (User user : USERS) {
            if (user.getId() == id){
                return user;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : USERS) {
            if (user.getLogin().equals(login))
                return user;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean deleteUserById(long id) {
        for (User user : USERS) {
            if (user.getId() == id){
                USERS.remove(user);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean deleteUserByLogin(String login) {
        for (User user : USERS) {
            if (user.getLogin().equals(login)){
                USERS.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUserByUser(User user) {
        for (User user1 : USERS) {
            if (user1.equals(user)){
                USERS.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateNameById(long id, String name) {
        for (User user : USERS) {
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
        for (User user : USERS) {
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
        return USERS;
    }

    @Override
    public List<User> getAllUsersByName(String name) {
        ArrayList<User> usersByName = new ArrayList<>();
        for (User user : USERS) {
            if (user.getName().equals(name)){
                usersByName.add(user);
            }
        }
        return usersByName;
    }

    @Override
    public boolean contains(User user) {
        return USERS.contains(user);
    }

    @Override
    public boolean contains(long id) {
        for (User user : USERS) {
            if (user.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        for (User user : USERS) {
            if (user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}
