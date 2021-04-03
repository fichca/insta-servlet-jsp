package by.insta.service;

import by.insta.entity.Message;
import by.insta.entity.User;

import java.util.Date;
import java.util.List;

public interface MessageService {

    boolean addMessage(Message message);

    boolean deleteMessage(Message message);

    boolean deleteMessageById(long id);

    Message getById(long id);

    List<Message> getMessageByDialogId(long dialogId);

    List<Message> getMessageByUser(User user);

    List<Message> getMessageByDate(Date date);

    List<Message> getAllMessage();
}
