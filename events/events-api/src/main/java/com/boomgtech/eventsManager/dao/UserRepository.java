package com.boomgtech.eventsManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boomgtech.eventsManager.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
