package com.test.demo.events.entityDto;


import com.test.demo.comments.models.Comment;
import com.test.demo.events.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {
    private Long  id;
    private String title;
    private String description;
    private String person;
    private List<Comment> comments;
}