package com.scrf1.backend.services;

import com.scrf1.backend.entityDtos.CommentRequest;
import com.scrf1.backend.entityDtos.EventPage;
import com.scrf1.backend.entityDtos.EventRequest;
import com.scrf1.backend.models.Comment;
import com.scrf1.backend.models.Event;
import com.scrf1.backend.repositories.CommentRepository;
import com.scrf1.backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EventRepository eventRepository;
    private final int DEFAULT_PAGE_SIZE = 5;

    @Override
    public ResponseEntity<Event> createEvent(EventRequest r) {
        if(
                r.getTitle() == null || (r.getTitle() != null && r.getTitle().isEmpty()) || (r.getTitle() != null && !r.getTitle().isEmpty() && r.getTitle().length() > 100) ||
                r.getPerson() == null || (r.getPerson() != null && r.getPerson().isEmpty())
        )
            return ResponseEntity.badRequest().body(null);

        Event event = new Event();
        event.setTitle(r.getTitle());
        event.setDescription(r.getDescription());
        event.setPerson(r.getPerson());

        return ResponseEntity.ok(eventRepository.save(event));
    }

    @Override
    public ResponseEntity<EventPage> getAllEvents(Integer currentPage, Integer pageSize) {
        if(currentPage == null) currentPage = 0;
        if(pageSize == null) pageSize = DEFAULT_PAGE_SIZE;

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Event> events = eventRepository.findAll(pageable);

        if(events.hasContent()) {
            EventPage eventPage = new EventPage();
            eventPage.setEvents(events.getContent());
            eventPage.setCurrentPage(events.getNumber());
            eventPage.setTotalItems(events.getTotalElements());
            eventPage.setTotalNumberPages(events.getTotalPages());

            return ResponseEntity.ok(eventPage);
        }
        return null;
    }

    @Override
    public ResponseEntity<Event> getEventById(Long eventId) {
        if(eventId == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(eventRepository.findById(eventId).get());
    }

    @Override
    public ResponseEntity<Long> deleteEvent(Long eventId) {
        if(eventId == null)
            return ResponseEntity.badRequest().body(null);
        eventRepository.deleteById(eventId);
        return ResponseEntity.ok(eventId);
    }

    @Override
    public ResponseEntity<Event> updateEvent(Long eventId, Event newEvent) {
        if(eventId == null || newEvent == null)
            return ResponseEntity.badRequest().body(null);
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()) {
            event.get().setPerson(newEvent.getPerson() != null && !newEvent.getPerson().isEmpty()?
                        newEvent.getPerson() : event.get().getPerson());
            event.get().setDescription(newEvent.getDescription() != null && !newEvent.getDescription().isEmpty()?
                        newEvent.getDescription() : event.get().getDescription());
            event.get().setTitle(newEvent.getTitle() != null && !newEvent.getTitle().isEmpty()?
                        newEvent.getTitle() : event.get().getTitle());

            return ResponseEntity.ok(eventRepository.save(event.get()));
        }
        return null;
    }

    @Override
    public ResponseEntity<Comment> createComment(CommentRequest r) {
        if (r.getDescription() == null || (r.getDescription() != null && r.getDescription().isEmpty()) ||
            r.getEventId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Event> event = eventRepository.findById(r.getEventId());
        if(event.isPresent()) {
            Comment comment = new Comment();
            comment.setDate(new Date());
            comment.setDescription(r.getDescription());
            comment.setEvent(event.get());

            commentRepository.save(comment);
        }

        return null;
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments(Long eventId) {
        if(eventId == null)
            return ResponseEntity.badRequest().body(null);

        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()) {
            return ResponseEntity.ok(commentRepository.findByEvent(event.get()));
        }
        return null;
    }

    @Override
    public ResponseEntity<Comment> getCommentById(Long commentId) {
        if(commentId == null)
            return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok(commentRepository.findById(commentId).get());
    }

    @Override
    public ResponseEntity<Long> deleteComment(Long commentId) {
        if(commentId == null)
            return ResponseEntity.badRequest().body(null);

        commentRepository.deleteById(commentId);
        return ResponseEntity.ok(commentId);
    }

    @Override
    public ResponseEntity<Comment> updateComment(Long commentId, Comment newComment) {
        if(commentId == null || newComment == null)
            return ResponseEntity.badRequest().body(null);

        Optional<Comment> oldComment = commentRepository.findById(commentId);
        if(oldComment.isPresent()) {
            oldComment.get().setDescription(newComment.getDescription() != null && !newComment.getDescription().isEmpty()?
                    newComment.getDescription() : oldComment.get().getDescription());

            ResponseEntity.ok(commentRepository.save(oldComment.get()));
        }
        return null;
    }
}
