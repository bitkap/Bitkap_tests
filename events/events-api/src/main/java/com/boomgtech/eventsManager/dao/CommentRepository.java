package com.boomgtech.eventsManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boomgtech.eventsManager.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
