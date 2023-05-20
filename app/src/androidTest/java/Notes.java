import java.util.ArrayList;
import java.util.List;

public class Notes {
    public List<Note> notes;

    public Notes() {
        notes = new ArrayList<>();
    }

    public void Create(int id, String text) {
        Note note = new Note(id, text);
        notes.add(note);
    }

}


