package com.scrf1.backend.entityDtos;

import com.scrf1.backend.models.Event;
import lombok.Data;

import java.util.List;

@Data
public class EventPage {

    private Long totalItems;
    private Integer totalNumberPages;
    private Integer currentPage;
    private List<Event> events;

}
