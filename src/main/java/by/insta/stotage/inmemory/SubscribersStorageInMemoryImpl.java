package by.insta.stotage.inmemory;

import by.insta.entity.User;
import by.insta.stotage.SubscribersStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubscribersStorageInMemoryImpl implements SubscribersStorage {

    private static final HashMap<User, List<User>> SUBSCRIBERS = new HashMap<>();

    @Override
    public boolean addSubscriber(User user, User subscriber) {
        if (!SUBSCRIBERS.containsKey(user)){
            SUBSCRIBERS.put(user, new ArrayList<>());
        }
        List<User> users = SUBSCRIBERS.get(user);

        return users.add(subscriber);
    }

    @Override
    public boolean deleteSubscriber(User user, User subscriber) {
        List<User> users = SUBSCRIBERS.get(user);
         return users.remove(subscriber);
    }

    @Override
    public boolean deleteUser(User user) {
        SUBSCRIBERS.remove(user);
        return true;
    }

    @Override
    public List<User> getSubscribers(User user) {
        return SUBSCRIBERS.get(user);
    }

    @Override
    public boolean contains(User user, User subscriber) {
        if(!SUBSCRIBERS.containsKey(user)){
            return false;
        }
        List<User> users = SUBSCRIBERS.get(user);
        return users.contains(subscriber);
    }

    @Override
    public boolean contains(User user) {
        return SUBSCRIBERS.containsKey(user);
    }
}
