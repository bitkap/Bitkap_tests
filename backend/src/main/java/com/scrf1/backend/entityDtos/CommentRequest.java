package com.scrf1.backend.entityDtos;

import lombok.Data;

@Data
public class CommentRequest {

    private String description;
    private Long eventId;
    private Long commentId;

}
