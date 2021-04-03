package by.insta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dialogue {

    private long id;
    private User fistUser;
    private User secondUser;
    private List<Message> messages = new LinkedList<>();

    public Dialogue(User fistUser, User secondUser) {
        this.fistUser = fistUser;
        this.secondUser = secondUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialogue dialogue = (Dialogue) o;
        return Objects.equals(fistUser, dialogue.fistUser) && Objects.equals(secondUser, dialogue.secondUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistUser, secondUser);
    }
}