package by.insta.stotage;

import by.insta.entity.Dialogue;
import by.insta.entity.User;

import java.util.List;

public interface DialogueStorage {

    boolean addDialog(Dialogue dialogue);

    boolean deleteDialog(Dialogue dialogue);

    boolean deleteDialogById(long id);

    Dialogue getById(long id);

    Dialogue getByUsers(User fistUser, User secondUser);

    List<Dialogue> getDialogsByUser(User user);

    List<Dialogue> getAllDialogs();

    boolean contains(Dialogue dialogue);

    boolean contains(long id);

    boolean contains(User user);

    boolean contains(User fistUser, User secondUser);


}
