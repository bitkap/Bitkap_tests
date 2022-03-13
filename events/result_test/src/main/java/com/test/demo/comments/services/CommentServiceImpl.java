package com.test.demo.comments.services;

import com.test.communs.DefaultProperties;
import com.test.communs.SimpleMessage;
import com.test.demo.comments.entityDto.CommentDto;
import com.test.demo.comments.models.Comment;
import com.test.demo.comments.repositories.CommentRepository;
import com.test.demo.events.models.Event;
import com.test.demo.events.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl   implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EventRepository eventRepository;
    Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Override
    public ResponseEntity<Comment> getCommentById(Long id) {
        return ResponseEntity.ok(commentRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentRepository.findAll());
    }

    @Override
    public ResponseEntity<Comment> createComment(CommentDto commentDto) {
        Optional<Event> event = eventRepository.findById(commentDto.getEvent());
        log.debug(" COMMENT DTO",commentDto);
        Comment comment = new Comment();
        comment.setEvent(event.get());
        comment.setDescription(commentDto.getDescription());
        comment.setTitle(commentDto.getTitle());
        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @Override
    public ResponseEntity<Comment> updateComment(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).get();
        comment.setTitle(commentDto.getTitle());
        comment.setDescription(commentDto.getDescription());
        comment.setEvent(eventRepository.findById(commentDto.getEvent()).get());
        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @Override
    public ResponseEntity<?> deleteCommentById(Long id) {
        SimpleMessage simpleMessage = new SimpleMessage();
        try {
            commentRepository.deleteById(id);
            Comment comment = commentRepository.findById(id).get();
        }catch(EmptyResultDataAccessException e) {
            simpleMessage.setTitle("SUCCESS");
            simpleMessage.setMessage("DELETED MESSAGE");
            return ResponseEntity.ok(simpleMessage);
        }
            simpleMessage.setTitle("FAILED");
            simpleMessage.setMessage("MESSAGE NOT DELETED");
            return ResponseEntity.ok(simpleMessage);
    }
}
