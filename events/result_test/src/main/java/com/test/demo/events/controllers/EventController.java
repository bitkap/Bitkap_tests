package com.test.demo.events.controllers;

import com.test.demo.comments.entityDto.CommentDto;
import com.test.demo.comments.models.Comment;
import com.test.demo.events.entityDto.EventDto;
import com.test.demo.events.entityDto.EventPage;
import com.test.demo.events.models.Event;
import com.test.demo.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<EventPage> getAllEvents(@RequestParam(name = "currentPage", defaultValue = "0") Integer currentPage,
                                                    @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return eventService.getAllEvents(currentPage, pageSize);
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
    @GetMapping("/detail")
    private ResponseEntity<Event> getEvent(@RequestParam("id") Long id)
    {
        return eventService.getEventById(id);
    }
    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteEvent(@RequestParam("id") Long id)
    {
        return eventService.deleteEventById(id);
    }
    @PutMapping("/update")
    private ResponseEntity<Event> updateEvent(@RequestBody EventDto eventDto)
    {
        return eventService.updateEvent(eventDto);
    }
}
