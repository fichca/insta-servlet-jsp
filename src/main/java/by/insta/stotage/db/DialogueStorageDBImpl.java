package by.insta.stotage.db;

import by.insta.entity.Dialogue;
import by.insta.entity.User;
import by.insta.stotage.DialogueStorage;

import java.util.List;

public class DialogueStorageDBImpl implements DialogueStorage {
    @Override
    public boolean addDialog(Dialogue dialogue) {
        return false;
    }

    @Override
    public boolean deleteDialog(Dialogue dialogue) {
        return false;
    }

    @Override
    public boolean deleteDialogById(long id) {
        return false;
    }

    @Override
    public Dialogue getById(long id) {
        return null;
    }

    @Override
    public Dialogue getByUsers(User fistUser, User secondUser) {
        return null;
    }

    @Override
    public List<Dialogue> getDialogsByUser(User user) {
        return null;
    }

    @Override
    public List<Dialogue> getAllDialogs() {
        return null;
    }

    @Override
    public boolean contains(Dialogue dialogue) {
        return false;
    }

    @Override
    public boolean contains(long id) {
        return false;
    }

    @Override
    public boolean contains(User user) {
        return false;
    }

    @Override
    public boolean contains(User fistUser, User secondUser) {
        return false;
    }
}
