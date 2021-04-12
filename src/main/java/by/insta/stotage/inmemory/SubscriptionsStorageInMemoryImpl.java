package by.insta.stotage.inmemory;

import by.insta.entity.User;
import by.insta.stotage.SubscriptionsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubscriptionsStorageInMemoryImpl implements SubscriptionsStorage {
    private static final HashMap<User, List<User>> SUBSCRIPTIONS = new HashMap<>();

    @Override
    public boolean addSubscription(User user, User subscriber) {
   if (!SUBSCRIPTIONS.containsKey(subscriber)){
        SUBSCRIPTIONS.put(subscriber, new ArrayList<>());
    }
        List<User> users = SUBSCRIPTIONS.get(subscriber);
        return users.add(user);
    }

    @Override
    public boolean deleteSubscription(User user, User subscriber) {
        List<User> users = SUBSCRIPTIONS.get(subscriber);
        return users.remove(user);
    }

    @Override
    public boolean deleteUser(User user) {
        SUBSCRIPTIONS.remove(user);
        return true;
    }

    @Override
    public List<User> getSubscription(User user) {
        return SUBSCRIPTIONS.get(user);
    }

    @Override
    public boolean contains(User user) {
        return SUBSCRIPTIONS.containsKey(user);
    }

    @Override
    public boolean contains(User user, User subscriber) {
        if(!SUBSCRIPTIONS.containsKey(subscriber)){
            return false;
        }
        List<User> users = SUBSCRIPTIONS.get(subscriber);
        return users.contains(user);
    }
}
