package com.test.demo.events.repositories;

import com.test.demo.comments.models.Comment;
import com.test.demo.events.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    Page<Event> findAll(Pageable pageable);


}
