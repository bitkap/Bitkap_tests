package com.bitkap.test.presentation;




import com.bitkap.test.model.Event;
import com.bitkap.test.service.iservice.IEvent;
import com.bitkap.test.service.iservice.IUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class EventControllerRest {


    private final IUser iUser;
    private final IEvent iEvent;


    public EventControllerRest(IUser iUser, IEvent iEvent) {
        this.iUser = iUser;
        this.iEvent = iEvent;

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // get all pageable events by userId.
    // You have possibility to specify page, size and sorting to parameter your result
    // example of url for this get method :
    //localhost:8080/api/users/1/events
    @GetMapping("/users/{userId}/events")
    public Page<Event> getAllEventsByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return iEvent.findByUserId(userId, pageable);
    }

    // get all pageable events
    // example of url for this get method :
    //localhost:8080/api/users/events
    //localhost:8080/api/users/events?page=1&size=1
    @GetMapping( "/users/events")
    Page<Event> getAllEventPageable(Pageable pageable) {
        return iEvent.getAllEvent(pageable);
    }



    // create new event in associating it at userId
    // example of url for this post method :
    //localhost:8080/api/users/1/events
    @PostMapping("/users/{userId}/events")
    public Event createEvent(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Event event){
        return iEvent.saveEvent(userId, event);
    }

    // update event by userId
    // example of url for this put method :
    // localhost:8080/api/users/1/events/7
    @PutMapping("/users/{userId}/events/{eventId}")
    public Event updateEventByUserId(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "eventId") Long eventId,
            @Valid @RequestBody Event eventRequest) {


        iUser.checkExistsUserById(userId);

        System.out.println("i pass well here");

        return iEvent.updateEvent(eventRequest, eventId);

    }



    // delete event by userId
    // example of url for this delete method :
    //localhost:8080/api/users/1/events/7
    @DeleteMapping("/users/{userId}/events/{eventId}")
    public ResponseEntity<?> deleteByUserIdAndEventId(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "eventId") Long eventId)
            {

        return iEvent.deleteByUserIdAndEventId(userId, eventId);
    }




}
