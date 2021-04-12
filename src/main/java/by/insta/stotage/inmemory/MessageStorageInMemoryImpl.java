package by.insta.stotage.inmemory;

import by.insta.stotage.MessageStorage;
import by.insta.entity.Message;
import by.insta.entity.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MessageStorageInMemoryImpl implements MessageStorage {
    private static final List<Message> MESSAGES = new LinkedList<>();

    @Override
    public boolean addMessage(Message message) {
        int size = MESSAGES.size();
        message.setId(++size);
        return MESSAGES.add(message);
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
        for (Message message : MESSAGES) {
            if (message.getId() == id){
                return message;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Message> getMessageByDialogId(long dialogId) {
        List<Message> messagesByDialog = new LinkedList<>();
        for (Message message : MESSAGES) {
            if (message.getDialogId() == dialogId){
                messagesByDialog.add(message);
            }
        }
        return messagesByDialog;
    }

    @Override
    public List<Message> getMessageByUser(User user) {
        List<Message> messagesByDialog = new LinkedList<>();
        for (Message message : MESSAGES) {
            if (message.getUser().equals(user)){
                messagesByDialog.add(message);
            }
        }
        return messagesByDialog;
    }

    @Override
    public List<Message> getMessageByDate(Date date) {
        List<Message> messagesByDialog = new LinkedList<>();
        for (Message message : MESSAGES) {
            if (message.getDate().equals(date)){
                messagesByDialog.add(message);
            }
        }
        return messagesByDialog;
    }

    @Override
    public List<Message> getAllMessage() {
        return MESSAGES;
    }

    @Override
    public boolean contains(Message message) {
        return MESSAGES.contains(message);
    }

    @Override
    public boolean contains(long id) {
        for (Message message : MESSAGES) {
            if (message.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByDialogId(long dialogId) {
        for (Message message : MESSAGES) {
            if (message.getDialogId() == dialogId){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (Message message : MESSAGES) {
            if (message.getUser().equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Date date) {
        for (Message message : MESSAGES) {
            if (message.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }
}
