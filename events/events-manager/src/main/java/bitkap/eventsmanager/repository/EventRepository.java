package bitkap.eventsmanager.repository;

import bitkap.eventsmanager.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, String> {
    Page<Event> findAll(Pageable pageable);
    Page<Event> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
