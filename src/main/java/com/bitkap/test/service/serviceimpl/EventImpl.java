package com.bitkap.test.service.serviceimpl;


import com.bitkap.test.dao.EventRepo;
import com.bitkap.test.dao.UserRepo;
import com.bitkap.test.model.Event;
import com.bitkap.test.model.ResourceNotFoundException;
import com.bitkap.test.service.iservice.IEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class EventImpl implements IEvent {
    
    private final EventRepo eventRepo;
    private final UserRepo userRepo;



    public EventImpl(EventRepo eventRepo, UserRepo userRepo) {
        this.eventRepo = eventRepo;
        this.userRepo = userRepo;
    }


    @Override
    public void checkExistsEventById(Long eventId) {
        if (!eventRepo.existsById(eventId)) {
            throw new ResourceNotFoundException("EventId" +eventId + "not found");
        }

    }

    @Override
    public Event saveEvent(Long userId, Event event) {
        return userRepo.findById(userId).map(user -> {
            event.setUser(user);

            return eventRepo.save(event);
        }).orElseThrow(()-> new ResourceNotFoundException("UserId" +userId + "not found"));

    }


    @Override
    public Event updateEvent(Event updateEvent, Long userId) {
        return eventRepo.findById(userId).map(event -> {
            event.setDescription(updateEvent.getDescription());
            event.setTitre(updateEvent.getTitre());
            event.setDate(updateEvent.getDate());
            event.setTime(updateEvent.getTime());
            event.setLocality(updateEvent.getLocality());
            return eventRepo.save(event);
        }).orElseGet(()-> {
            updateEvent.setId(userId);
            return eventRepo.save(updateEvent);
        });
    }

    @Override
    public Page<Event> findByUserId(Long userId, Pageable pageable) {
        return eventRepo.findByUser_Id(userId, pageable);
    }

    @Override
    public Page<Event> getAllEvent(Pageable pageable) {
        return eventRepo.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> deleteByUserIdAndEventId(Long userId, Long eventId) {
        return eventRepo.findByUser_IdAndId(userId, eventId).map(event -> {
            eventRepo.delete(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Event not found with eventId" + eventId + "and UserId" +userId));
    }


    }

