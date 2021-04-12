package by.insta.service;

import by.insta.stotage.SubscribersStorage;
import by.insta.stotage.SubscriptionsStorage;
import by.insta.stotage.UserStorage;
import by.insta.entity.User;

import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService {

    private  final UserStorage userStorage;
    private final SubscribersStorage subscribersStorage;
    private final SubscriptionsStorage subscriptionsStorage;

    public UserServiceImpl(UserStorage userStorage, SubscribersStorage subscribersStorage, SubscriptionsStorage subscriptionsStorage) {
        this.userStorage = userStorage;
        this.subscribersStorage = subscribersStorage;
        this.subscriptionsStorage = subscriptionsStorage;
    }

    @Override
    public boolean addUser(User user) {
        if (userStorage.contains(user.getLogin())){
            return false;
        }
        return userStorage.addUser(user);
    }

    @Override
    public boolean addSubscriber(User user, User subscriber) {
        if (!subscribersStorage.contains(user, subscriber)){
            subscribersStorage.addSubscriber(user, subscriber);
        }
        if (!subscriptionsStorage.contains(user, subscriber)){
            subscriptionsStorage.addSubscription(user, subscriber);
        }
        setSubscriber(subscriber);
        return true;
    }

    private void setSubscriber(User user){
        List<User> subscribers = subscribersStorage.getSubscribers(user);
        List<User> subscription = subscriptionsStorage.getSubscription(user);

        user.setSubscribers(subscribers);
        user.setSubscriptions(subscription);
    }

    @Override
    public User getUserById(long id) {
        if (userStorage.contains(id)){
            User user = userStorage.getUserById(id);
            setSubscriber(user);
            return userStorage.getUserById(id);
        }
        throw new NoSuchElementException();
    }

    @Override
    public User getUserByLogin(String login) {
        if (userStorage.contains(login)){
            User user = userStorage.getUserByLogin(login);
            setSubscriber(user);
            return user;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean deleteUserById(long id) {
        if (userStorage.contains(id)){
            User user = userStorage.getUserById(id);
            if (subscriptionsStorage.contains(user)){
                subscriptionsStorage.deleteUser(user);
            } else if (subscribersStorage.contains(user)){
                subscribersStorage.deleteUser(user);
            }
            setSubscriber(user);
            return userStorage.deleteUserById(id);
        }
        return false;
    }

    @Override
    public boolean deleteSubscriber(User user, User subscriber) {
        if (subscribersStorage.contains(user, subscriber)){
            subscribersStorage.deleteSubscriber(user, subscriber);
        }

        if (subscriptionsStorage.contains(user, subscriber)){
            subscriptionsStorage.deleteSubscription(user, subscriber);
        }
        setSubscriber(subscriber);
        return true;
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        if (userStorage.contains(login)){
            User user = userStorage.getUserByLogin(login);
            if (subscriptionsStorage.contains(user)){
                subscriptionsStorage.deleteUser(user);
            } else if (subscribersStorage.contains(user)){
                subscribersStorage.deleteUser(user);
            }
            return userStorage.deleteUserByLogin(login);
        }
        return false;
    }

    @Override
    public boolean deleteUserByUser(User user) {
        if (userStorage.contains(user)){
            if (subscriptionsStorage.contains(user)){
                subscriptionsStorage.deleteUser(user);
            } else if (subscribersStorage.contains(user)){
                subscribersStorage.deleteUser(user);
            }
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
