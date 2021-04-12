package by.insta.stotage.db;

import by.insta.entity.Message;
import by.insta.entity.User;
import by.insta.stotage.MessageStorage;

import java.util.Date;
import java.util.List;

public class MessageStorageDBImpl implements MessageStorage {
    @Override
    public boolean addMessage(Message message) {
        return false;
    }

    @Override
    public boolean deleteMessage(Message message) {
        return false;
    }

    @Override
    public boolean deleteMessageById(long id) {
        return false;
    }

    @Override
    public Message getById(long id) {
        return null;
    }

    @Override
    public List<Message> getMessageByDialogId(long dialogId) {
        return null;
    }

    @Override
    public List<Message> getMessageByUser(User user) {
        return null;
    }

    @Override
    public List<Message> getMessageByDate(Date date) {
        return null;
    }

    @Override
    public List<Message> getAllMessage() {
        return null;
    }

    @Override
    public boolean contains(Message message) {
        return false;
    }

    @Override
    public boolean contains(long id) {
        return false;
    }

    @Override
    public boolean containsByDialogId(long dialogId) {
        return false;
    }

    @Override
    public boolean contains(User user) {
        return false;
    }

    @Override
    public boolean contains(Date date) {
        return false;
    }
}
