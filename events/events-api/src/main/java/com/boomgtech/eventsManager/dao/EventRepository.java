package com.boomgtech.eventsManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.boomgtech.eventsManager.entities.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
