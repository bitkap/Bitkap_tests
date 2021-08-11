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
public class CommentService {
	
	@Autowired
    CommentRepository commentRepository;
	
	public Optional<Comment> getOneById(long id)
    {         
        return commentRepository.findById(id);
    }
    
    public Comment saveComment(Comment comment)
    {         
        return commentRepository.save(comment);
    }
    
    public void deleteEvent(Comment comment)
    {         
    	commentRepository.delete(comment);
    }
}