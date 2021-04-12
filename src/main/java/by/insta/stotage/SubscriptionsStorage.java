package by.insta.stotage;

import by.insta.entity.User;

import java.util.List;

public interface SubscriptionsStorage {

    boolean addSubscription(User user, User subscriber);

    boolean deleteSubscription(User user, User subscriber);

    boolean deleteUser(User user);

    List<User> getSubscription(User user);

    boolean contains(User user);

    boolean contains(User user, User subscriber);
}
