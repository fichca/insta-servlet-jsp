package by.insta.stotage;

import by.insta.entity.User;

import java.util.List;

public interface SubscribersStorage {

    boolean addSubscriber(User user, User subscriber);

    boolean deleteSubscriber(User user, User subscriber);

    boolean deleteUser(User user);

    List<User> getSubscribers(User user);

    boolean contains(User user, User subscriber);

    boolean contains(User user);
}
