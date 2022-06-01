package com.scrf1.backend.entityDtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class EventRequest {

    private String title;
    private String description;
    private String person;

}
