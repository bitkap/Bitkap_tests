package com.bitkap.test.service.iservice;



import com.bitkap.test.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IEvent {



    // check existence of user by her userId
    void checkExistsEventById(Long userId);

    // create new event in associating at userId
    Event saveEvent(Long userId, Event event);

    // update event by userId
    Event updateEvent(Event updateEvent, Long userId);

    // get event by userId
    Page<Event> findByUserId(Long userId, Pageable pageable);

    // get all events
    Page<Event> getAllEvent(Pageable pageable);

    //delete event by userId
    ResponseEntity<?> deleteByUserIdAndEventId(Long userId,Long eventId);



}
