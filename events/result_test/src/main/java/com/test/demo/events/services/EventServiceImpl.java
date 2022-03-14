package com.test.demo.events.services;

import com.test.communs.DefaultProperties;
import com.test.communs.SimpleMessage;
import com.test.demo.comments.models.Comment;
import com.test.demo.comments.repositories.CommentRepository;
import com.test.demo.events.entityDto.EventDto;
import com.test.demo.events.entityDto.EventPage;
import com.test.demo.events.models.Event;
import com.test.demo.events.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public ResponseEntity<Event> getEventById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<EventPage> getAllEvents(Integer currentPage, Integer pageSize) {
        if(currentPage == null) currentPage = 0;
        if(pageSize == null) pageSize = DefaultProperties.DEFAULT_PAGE_SIZE;

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Event> events = eventRepository.findAll(pageable);
        List<EventDto> eventDtos= new ArrayList<>();
        for(Event event :events){
            List<Comment> comments = commentRepository.findByEvent(event);
            EventDto eventDto = new EventDto(event.getId(),event.getTitle(),event.getDescription(),event.getPerson(),comments);
            eventDtos.add(eventDto);
        }
        if(events.hasContent()) {
            EventPage eventPage = new EventPage();
            eventPage.setCurrentPage(events.getNumber());
            eventPage.setTotalItems(events.getTotalElements());
            eventPage.setTotalNumberPages(events.getTotalPages());
            eventPage.setEventDtos(eventDtos);
            return ResponseEntity.ok(eventPage);
        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Event> createEvent(Event event) {
        return ResponseEntity.ok(eventRepository.save(event));
    }

    @Override
    public ResponseEntity<Event> updateEvent(EventDto eventDto) {
        Event event = eventRepository.findById(eventDto.getId()).get();
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        return ResponseEntity.ok(eventRepository.save(event));
    }

    @Override
    public ResponseEntity<?> deleteEventById(Long id) {

        SimpleMessage simpleMessage = new SimpleMessage();
        try {
            Event event = eventRepository.findById(id).get();
            List<Comment> comments = commentRepository.findByEvent(event);
            for (Comment comment:comments) {
                commentRepository.deleteById(comment.getId());
            }
            eventRepository.deleteById(id);
            Event event2 = eventRepository.findById(id).get();
        }catch(NoSuchElementException e) {
            simpleMessage.setTitle("SUCCESS");
            simpleMessage.setMessage("DELETED EVENT");
            return ResponseEntity.ok(simpleMessage);
        }
        simpleMessage.setTitle("FAILED");
        simpleMessage.setMessage("EVENT NOT DELETED");
        return ResponseEntity.ok(simpleMessage);
    }
}
