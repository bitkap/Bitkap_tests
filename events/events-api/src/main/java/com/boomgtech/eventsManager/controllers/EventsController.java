package com.boomgtech.eventsManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boomgtech.eventsManager.dao.EventRepository;
import com.boomgtech.eventsManager.entities.Comment;
import com.boomgtech.eventsManager.entities.Event;
import com.boomgtech.eventsManager.entities.User;
import com.boomgtech.eventsManager.requests.CommentRequest;
import com.boomgtech.eventsManager.requests.EventRequest;
import com.boomgtech.eventsManager.services.CommentService;
import com.boomgtech.eventsManager.services.EventService;
import com.boomgtech.eventsManager.services.UserService;

@RestController
@RequestMapping("/api/events")
public class EventsController {

	@Autowired
	EventService eventService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<Event>> getEventsByPage(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
			
		Page<Event> list =  eventService.getEventByPage(pageNo, pageSize, sortBy);
		return new ResponseEntity<Page<Event>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Event> getOneEvent(@PathVariable int id) {
		
		Event event = eventService.getOneById(id).get();
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Event> saveEvent(@RequestBody EventRequest eventRequest) {
		
		Event eventToSave = new Event();
		User user = userService.getOneById(eventRequest.getUserId()).get();
		
		eventToSave.setTitle(eventRequest.getTitle());
		eventToSave.setDescription(eventRequest.getDescription());
		eventToSave.setUser(user);
		
		Event eventSaved = eventService.saveEvent(eventToSave);
		return new ResponseEntity<Event>(eventSaved, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Event> updateEvent(@RequestBody EventRequest eventRequest) {
		
		Event eventToSave = eventService.getOneById(eventRequest.getId()).get();
		User user = userService.getOneById(eventRequest.getUserId()).get();
		
		eventToSave.setTitle(eventRequest.getTitle());
		eventToSave.setDescription(eventRequest.getDescription());
		eventToSave.setUser(user);
		
		Event eventSaved = eventService.saveEvent(eventToSave);
		return new ResponseEntity<Event>(eventSaved, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEvent(@PathVariable int id) {
		
		Event event = eventService.getOneById(id).get();
		eventService.deleteEvent(event);
		return new ResponseEntity<String>("Evènement supprimé avec succès!", HttpStatus.OK);
	}
	
	// ------ 
	@PostMapping("/comments")
	public ResponseEntity<Comment> saveComment(@RequestBody CommentRequest commentRequest) {
		
		Comment commentToSave = new Comment();
		Event event = eventService.getOneById(commentRequest.getEventId()).get();
		
		commentToSave.setComment(commentRequest.getComment());
		commentToSave.setDate(commentRequest.getDate());
		commentToSave.setEvent(event);
		
		Comment commentSaved = commentService.saveComment(commentToSave);
		return new ResponseEntity<Comment>(commentSaved, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable int id) {
		
		Comment comment = commentService.getOneById(id).get();
		eventService.deleteComment(comment);
		return new ResponseEntity<String>("Commantaire supprimé avec succès!", HttpStatus.OK);
	}
}
