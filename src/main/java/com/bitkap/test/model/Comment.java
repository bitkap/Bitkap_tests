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
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotNull
    @Size( max = 100)
    private String description;

    private Date date;

    // fetch = FetchType.LAZY == @NotFound(action = NotFoundAction.IGNORE) so never use both
    @ManyToOne(targetEntity = Event.class,optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Event event;

    @ManyToOne(targetEntity = User.class,optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

}
