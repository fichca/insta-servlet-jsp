package by.insta.service;

import by.insta.dao.DialogueStorage;
import by.insta.dao.MessageStorage;
import by.insta.entity.Dialogue;
import by.insta.entity.Message;
import by.insta.entity.User;

import java.util.List;
import java.util.NoSuchElementException;

public class DialogServiceImpl implements DialogService {

    private final DialogueStorage dialogueStorage;
    private final MessageStorage messageStorage;

    public DialogServiceImpl(DialogueStorage dialogueStorage, MessageStorage messageStorage) {
        this.dialogueStorage = dialogueStorage;
        this.messageStorage = messageStorage;
    }

    @Override
    public boolean addDialog(Dialogue dialogue) {
        if (!dialogueStorage.contains(dialogue)) {
            dialogueStorage.addDialog(dialogue);
        }
        return false;
    }

    @Override
    public boolean deleteDialog(Dialogue dialogue) {
        if (dialogueStorage.contains(dialogue)) {
            return dialogueStorage.deleteDialog(dialogue);
        }
        return false;
    }

    @Override
    public boolean deleteDialogById(long id) {
        if (dialogueStorage.contains(id)) {
            return dialogueStorage.deleteDialogById(id);
        }
        return false;
    }

    @Override
    public Dialogue getById(long id) {
        if (dialogueStorage.contains(id)) {
            List<Message> messageByDialogId = messageStorage.getMessageByDialogId(id);
            Dialogue dialogue = dialogueStorage.getById(id);
            dialogue.setMessages(messageByDialogId);
            return dialogue;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Dialogue getByUsers(User fistUser, User secondUser) {
        if (dialogueStorage.contains(fistUser, secondUser)) {
            Dialogue dialogue = dialogueStorage.getByUsers(fistUser, secondUser);
            List<Message> messageByDialogId = messageStorage.getMessageByDialogId(dialogue.getId());
            dialogue.setMessages(messageByDialogId);
            return dialogue;
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Dialogue> getDialogsByUser(User user) {
        List<Dialogue> dialogsByUser = dialogueStorage.getDialogsByUser(user);
        for (Dialogue dialogue : dialogsByUser) {
            List<Message> messageByDialogId = messageStorage.getMessageByDialogId(dialogue.getId());
            dialogue.setMessages(messageByDialogId);
        }
        return dialogsByUser;
    }

    @Override
    public List<Dialogue> getAllDialogs() {
        return dialogueStorage.getAllDialogs();
    }

    @Override
    public boolean contains(User fistUser, User secondUser) {
        return dialogueStorage.contains(fistUser, secondUser);
    }
}
