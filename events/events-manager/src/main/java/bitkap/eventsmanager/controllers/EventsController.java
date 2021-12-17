package bitkap.eventsmanager.controllers;

import bitkap.eventsmanager.models.Comment;
import bitkap.eventsmanager.models.Event;
import bitkap.eventsmanager.models.Person;
import bitkap.eventsmanager.payloads.response.MessageResponse;
import bitkap.eventsmanager.repository.CommentRepository;
import bitkap.eventsmanager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/api/events")
public class EventsController {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/load_all_events")
    public ResponseEntity<?> loadAllEvent(){
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/load_events")
    public ResponseEntity<?> loadEvent(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);

        Page<Event> pageEvnt;
        if(title == null)
            pageEvnt = eventRepository.findAll(paging);
        else
            pageEvnt = eventRepository.findByTitleContainingIgnoreCase(title, paging);

        List events = new ArrayList<Event>();
        events = pageEvnt.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("events", events);
        response.put("currentPage", pageEvnt.getNumber());
        response.put("currentPageSize", pageEvnt.getNumberOfElements());
        response.put("totalItems", pageEvnt.getTotalElements());
        response.put("totalPages", pageEvnt.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/load_event")
    public ResponseEntity<?> loadAllEvent(
            @RequestParam(required = false) String id
    ){
        if(id==null)
            return ResponseEntity.badRequest().body((new MessageResponse("The provided id cannot be Null")));
        return ResponseEntity.ok(eventRepository.findById(id));
    }




    @PostMapping("/create_event")
    public ResponseEntity<?> createEvent(@RequestBody Event event){
        eventRepository.save(event);
        MessageResponse msg = new MessageResponse("New event successfully created. title: " +event.getTitle()+ " Id: " + event.getId());
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/update_event")
    public ResponseEntity<?> updateEvent(@RequestBody Event eventRequest){
        if(eventRequest.getId()==null)
            return ResponseEntity.badRequest().body((new MessageResponse("No provided id")));
        else{
            Optional<Event> eventOptional = eventRepository.findById(eventRequest.getId());
            if(eventOptional.isPresent()){
                Event event0 = eventOptional.get();
              if(eventRequest.getTitle()!=null)
                  event0.setTitle(eventRequest.getTitle());
              if(eventRequest.getDescription()!=null)
                  event0.setDescription(eventRequest.getDescription());
              if(eventRequest.getPerson()!=null){
                  Person p = new Person();
                  p.setName(eventRequest.getPerson().getName());
                  p.setSurname(eventRequest.getPerson().getSurname());
                  event0.setPerson(p);
              }
              eventRepository.save(event0);
              return ResponseEntity.ok((new MessageResponse("Event successfully updated")));
            }else
                return ResponseEntity.badRequest().body((new MessageResponse("No such event")));
        }
    }




    @DeleteMapping("/delete_event")
    public ResponseEntity<?> deleteEvent(
            @RequestParam String id
    ){
        eventRepository.deleteById(id);
        MessageResponse msg = new MessageResponse("Event with id : "+ id +" successfully deleted.");
        return ResponseEntity.ok(msg);
    }


    /**
     * COMMENTAIRES SUR LES EVENEMENTS*/

    @GetMapping("/load_comment")
    public ResponseEntity<?> loadComment(
            @RequestParam String id
    ){
        return ResponseEntity.ok(commentRepository.findById(id));
    }



    @PostMapping("/add_comment")
    public ResponseEntity<?> addComment(@RequestBody Comment comment){
        if(comment.getEventId()!=null){
            Event event = eventRepository.findById(comment.getEventId())
                    .orElseThrow(() -> new RuntimeException("Error: Event is not found."));
            commentRepository.save(comment);
            event.getComments().add(comment);
            eventRepository.save(event);
        }
        MessageResponse msg = new MessageResponse("Your comment has been successfully added to the event");
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/update_comment")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment){
        if(comment.getId()==null)
            return ResponseEntity.badRequest().body((new MessageResponse("Vous devez preciser l'id du commentaire à modifier")));

        Comment comment0 = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new RuntimeException("Error: No comment with such an id : " + comment.getId()));

        comment0.setDescription(comment.getDescription());
        comment0.setDate(comment.getDate());

        commentRepository.save(comment0);
        MessageResponse msg = new MessageResponse("Your comment has been successfully updated");
        return ResponseEntity.ok(msg);
    }


    @DeleteMapping("/delete_comment")
    public ResponseEntity<?> deleteComment(@RequestParam String id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: No comments with such id :"+id));
        Event event = eventRepository.findById(comment.getEventId())
                .orElseThrow(()->new RuntimeException("Error: No event with such id :"+comment.getEventId()));
        event.getComments().remove(comment);
        eventRepository.save(event);
        commentRepository.deleteById(id);
        return ResponseEntity.ok((new MessageResponse("Comment successfully deleted")));
    }
}
