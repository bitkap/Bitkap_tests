package com.test.demo.events.entityDto;


import com.test.demo.events.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventPage {
    private Long totalItems;
    private Integer totalNumberPages;
    private Integer currentPage;
    private List<EventDto> eventDtos;
}