package by.insta.service;

import by.insta.dao.MessageStorage;
import by.insta.entity.Message;
import by.insta.entity.User;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class MessageServiceImpl implements MessageService {

    private final MessageStorage messageStorage;

    public MessageServiceImpl(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public boolean addMessage(Message message) {
        return messageStorage.addMessage(message);
    }

    @Override
    public boolean deleteMessage(Message message) {
        if (messageStorage.contains(message)) {
            return messageStorage.deleteMessage(message);
        }
        return false;
    }

    @Override
    public boolean deleteMessageById(long id) {
        if (messageStorage.contains(id)) {
            return messageStorage.deleteMessageById(id);
        }
        return false;
    }

    @Override
    public Message getById(long id) {
        if (messageStorage.contains(id)){
            return messageStorage.getById(id);
        } throw new NoSuchElementException();
    }

    @Override
    public List<Message> getMessageByDialogId(long dialogId) {
        return messageStorage.getMessageByDialogId(dialogId);
    }

    @Override
    public List<Message> getMessageByUser(User user) {
        return messageStorage.getMessageByUser(user);
    }

    @Override
    public List<Message> getMessageByDate(Date date) {
        return messageStorage.getMessageByDate(date);
    }

    @Override
    public List<Message> getAllMessage() {
        return messageStorage.getAllMessage();
    }
}
