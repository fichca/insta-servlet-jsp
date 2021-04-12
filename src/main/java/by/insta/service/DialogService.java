package by.insta.service;

import by.insta.entity.Dialogue;
import by.insta.entity.User;

import java.util.List;

public interface DialogService {

    boolean addDialog(Dialogue dialogue);

    boolean addDialog(String loginByFistUser, String loginBySecondUser);

    boolean deleteDialog(Dialogue dialogue);

    boolean deleteDialogById(long id);

    Dialogue getById(long id);

    Dialogue getByUsers(User fistUser, User secondUser);

    Dialogue getByUsers(String loginByFistUser, String loginBySecondUser);

    List<Dialogue> getDialogsByUser(User user);

    List<Dialogue> getAllDialogs();

    boolean contains(User fistUser, User secondUser);
}
