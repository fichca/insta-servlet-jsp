package by.insta.dao;

import by.insta.entity.Dialogue;
import by.insta.entity.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class DialogueStorageImpl implements DialogueStorage {
    private static final List<Dialogue> DIALOGUES = new LinkedList<>();

    @Override
    public boolean addDialog(Dialogue dialogue) {
        int size = DIALOGUES.size();
        dialogue.setId(++size);
        return DIALOGUES.add(dialogue);
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
        for (Dialogue dialogue : DIALOGUES) {
            if (dialogue.getId() == id){
                return dialogue;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Dialogue getByUsers(User fistUser, User secondUser) {
        for (Dialogue dialogue : DIALOGUES) {
            User dialogueFistUser = dialogue.getFistUser();
            User dialogueSecondUser = dialogue.getSecondUser();

            if (dialogueFistUser.equals(fistUser) && dialogueSecondUser.equals(secondUser)){
                return dialogue;
            }else if (dialogueSecondUser.equals(fistUser) && dialogueFistUser.equals(secondUser)){
                return dialogue;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Dialogue> getDialogsByUser(User user) {

        List<Dialogue> dialoguesByUser = new LinkedList<>();
        for (Dialogue dialogue : DIALOGUES) {
            User dialogueFistUser = dialogue.getFistUser();
            User dialogueSecondUser = dialogue.getSecondUser();

            if (dialogueFistUser.equals(user) || dialogueSecondUser.equals(user)){
               dialoguesByUser.add(dialogue);
            }
        } return dialoguesByUser;
    }

    @Override
    public List<Dialogue> getAllDialogs() {
        return DIALOGUES;
    }

    @Override
    public boolean contains(Dialogue dialogue) {
        return DIALOGUES.contains(dialogue);
    }

    @Override
    public boolean contains(long id) {
        for (Dialogue dialogue : DIALOGUES) {
            if (dialogue.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (Dialogue dialogue : DIALOGUES) {
            if (dialogue.getFistUser().equals(user) || dialogue.getSecondUser().equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User fistUser, User secondUser) {
        for (Dialogue dialogue : DIALOGUES) {
            User dialogueFistUser = dialogue.getFistUser();
            User dialogueSecondUser = dialogue.getSecondUser();

            if (dialogueFistUser.equals(fistUser) && dialogueSecondUser.equals(secondUser)) {
                return true;
            } else if (dialogueSecondUser.equals(fistUser) && dialogueFistUser.equals(secondUser)) {
                return true;
            }

        }
        return false;
    }
}
