package com.scrf1.backend.controllers;

import com.scrf1.backend.entityDtos.CommentRequest;
import com.scrf1.backend.entityDtos.EventPage;
import com.scrf1.backend.entityDtos.EventRequest;
import com.scrf1.backend.models.Comment;
import com.scrf1.backend.models.Event;
import com.scrf1.backend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    // events
    @PostMapping(value = "/events/create", produces = "application/json")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest eventRequest)
    {
        return eventService.createEvent(eventRequest);
    }

    @GetMapping(value = "/events/{id}", produces = "application/json")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping(value = "/events/all", produces = "application/json")
    public ResponseEntity<EventPage> getEvents(@RequestParam(name = "currentPage", defaultValue = "0") Integer currentPage,
                                               @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return eventService.getAllEvents(currentPage, pageSize);
    }

    @PutMapping(value = "/events/update", produces = "application/json")
    public ResponseEntity<Event> updateEvent(@RequestParam(name = "eventId") Long eventId,
                                             @RequestBody Event newEvent
    ) {
        return eventService.updateEvent(eventId, newEvent);
    }

    @DeleteMapping(value = "/events/delete/{id}", produces = "application/json")
    public ResponseEntity<Long> deleteEvent(@PathVariable("id") Long eventId) {
        return eventService.deleteEvent(eventId);
    }

    // comments
    @PostMapping(value = "/comments/create", produces = "application/json")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest)
    {
        return eventService.createComment(commentRequest);
    }

    @GetMapping(value = "/comments/all",  produces = "application/json")
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam("eventId") Long eventId)
    {
        return eventService.getAllComments(eventId);
    }
    @GetMapping(value = "/comments/{id}",  produces = "application/json")
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long commentId)
    {
        return eventService.getCommentById(commentId);
    }

    @DeleteMapping(value = "/comments/delete", produces = "application/json")
    public ResponseEntity<Long> deleteComment(@RequestParam("commentId") Long commentId) {
        return eventService.deleteComment(commentId);
    }

    @PutMapping(value = "/comments/update", produces = "application/json")
    public ResponseEntity<Comment> updateComment(@RequestParam("commentId") Long commentId,
                                                 @RequestBody Comment newComment) {
        return eventService.updateComment(commentId, newComment);
    }

}
