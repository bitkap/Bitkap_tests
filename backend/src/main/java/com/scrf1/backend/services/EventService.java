package com.scrf1.backend.services;

import com.scrf1.backend.entityDtos.CommentRequest;
import com.scrf1.backend.entityDtos.EventPage;
import com.scrf1.backend.entityDtos.EventRequest;
import com.scrf1.backend.models.Comment;
import com.scrf1.backend.models.Event;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {

    // events
    public ResponseEntity<Event> createEvent(EventRequest eventRequest);
    public ResponseEntity<EventPage> getAllEvents(Integer currentPage, Integer pageSize);
    public ResponseEntity<Event> getEventById(Long eventId);
    public ResponseEntity<Long> deleteEvent(Long eventId);
    public ResponseEntity<Event> updateEvent(Long eventId, Event newEvent);

    // comments
    public ResponseEntity<Comment> createComment(CommentRequest commentRequest);
    public ResponseEntity<List<Comment>> getAllComments(Long eventId);
    public ResponseEntity<Comment> getCommentById(Long commentId);
    public ResponseEntity<Long> deleteComment(Long commentId);
    public ResponseEntity<Comment> updateComment(CommentRequest commentRequest);

}
