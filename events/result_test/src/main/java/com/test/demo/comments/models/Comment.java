package com.test.demo.comments.models;

import com.test.communs.Auditable;
import com.test.demo.events.models.Event;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Comment")
@Data

public class Comment extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @NotNull(message = "Event  not null")
    private Event event;

}
