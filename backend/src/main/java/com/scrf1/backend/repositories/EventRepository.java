package com.scrf1.backend.repositories;

import com.scrf1.backend.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Page<Event> findAll(Pageable pageable);

}
