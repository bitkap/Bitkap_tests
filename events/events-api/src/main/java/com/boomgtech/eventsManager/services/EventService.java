package com.boomgtech.eventsManager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boomgtech.eventsManager.dao.CommentRepository;
import com.boomgtech.eventsManager.dao.EventRepository;
import com.boomgtech.eventsManager.entities.Comment;
import com.boomgtech.eventsManager.entities.Event;
import com.boomgtech.eventsManager.entities.User;

@Service
public class EventService {
	
	@Autowired
    EventRepository eventRepository;
	
	@Autowired
    CommentRepository commentRepository;
     
    public  Page<Event> getEventByPage(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Event> pagedResult = eventRepository.findAll(paging);
          return pagedResult;
        
    }
    
    public Optional<Event> getOneById(long id)
    {         
        return eventRepository.findById(id);
    }
    
    public Event saveEvent(Event event)
    {         
        return eventRepository.save(event);
    }
    
    public void deleteEvent(Event event)
    {         
    	eventRepository.delete(event);
    }
    
    // ----- METHODS TO INTERACT WITH COMMENTS OF EVENTS
    public Comment saveComment(Comment comment)
    {         
        return commentRepository.save(comment);
    }
    
    public void deleteComment(Comment comment)
    {         
    	commentRepository.delete(comment);
    }
}
