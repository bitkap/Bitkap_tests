package bitkap.eventsmanager.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Document(collection = "events")
public class Event {
    @Id
    private String id;

    @NotBlank
    @Size(max=100)
    private String title;

    private String description;

    @NotNull
    private Person person;

    @DBRef
    private ArrayList<Comment> comments;

    public Event(String id, String title, String description, Person person, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.person = person;
        this.comments = comments;
    }

    public Event() {
        comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
