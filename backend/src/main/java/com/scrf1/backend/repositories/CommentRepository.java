package com.scrf1.backend.repositories;

import com.scrf1.backend.models.Comment;
import com.scrf1.backend.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findByEvent(Event event);
}
