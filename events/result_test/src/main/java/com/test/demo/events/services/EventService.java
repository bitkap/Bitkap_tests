package com.test.demo.events.services;

import com.test.demo.comments.entityDto.CommentDto;
import com.test.demo.comments.models.Comment;
import com.test.demo.events.entityDto.EventDto;
import com.test.demo.events.entityDto.EventPage;
import com.test.demo.events.models.Event;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {
    ResponseEntity<Event> getEventById(Long id);
    ResponseEntity<EventPage> getAllEvents(Integer currentPage, Integer pageSize);
    ResponseEntity<Event> createEvent(Event event);
    ResponseEntity<Event> updateEvent(EventDto eventDto);
    ResponseEntity<?> deleteEventById(Long id);
}
