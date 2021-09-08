package com.bitkap.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;


    @NotNull
    @Size( max = 100)
    @Column(unique = true)
    private String titre;

    @NotNull
    @Size( max = 250)
    private String description;

    @NotNull
    private String date;

    @NotNull
    private String time;

    @NotNull
    private String locality;

    @OneToMany(mappedBy = "event")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Comment> comments = new ArrayList<>();

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(optional = false,targetEntity = User.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

}
