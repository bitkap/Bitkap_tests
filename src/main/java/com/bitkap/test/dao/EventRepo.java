package com.bitkap.test.dao;



import com.bitkap.test.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {


    boolean existsById(Long eventId);
    Page<Event> findAll(Pageable pageable);
    Page<Event> findByUser_Id(Long id, Pageable pageable);
    Optional<Event> findByUser_IdAndId(Long userId, Long eventId);


}
