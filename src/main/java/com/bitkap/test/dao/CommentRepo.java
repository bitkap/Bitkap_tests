package com.bitkap.test.dao;


import com.bitkap.test.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentRepo extends JpaRepository<Comment, Long> {

   Page<Comment> findAll(Pageable pageable);
   Page<Comment> findByEvent_IdAndUser_Id(Long eventId,Long userId,Pageable pageable);
   Optional<Comment> findByEvent_IdAndUser_IdAndId(Long eventId,Long userId,Long commentId);
}
