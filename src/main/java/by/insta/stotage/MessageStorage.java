package by.insta.stotage;

import by.insta.entity.Message;
import by.insta.entity.User;

import java.util.Date;
import java.util.List;

public interface MessageStorage {

    boolean addMessage(Message message);

    boolean deleteMessage(Message message);

    boolean deleteMessageById(long id);

    Message getById(long id);

    List<Message> getMessageByDialogId(long dialogId);

    List<Message> getMessageByUser(User user);

    List<Message> getMessageByDate(Date date);

    List<Message> getAllMessage();

    boolean contains(Message message);

    boolean contains(long id);

    boolean containsByDialogId(long dialogId);

    boolean contains(User user);

    boolean contains(Date date);
}
