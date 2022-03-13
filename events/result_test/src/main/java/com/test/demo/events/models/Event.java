package com.test.demo.events.models;

import lombok.Data;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity
@Table(name = "Event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title",length = 100)
    @Size(min = 3, max = 100)
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "person")
    private String person;

}
