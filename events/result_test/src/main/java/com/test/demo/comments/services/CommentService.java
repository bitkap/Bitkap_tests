package com.test.demo.comments.services;

import com.test.demo.comments.entityDto.CommentDto;
import com.test.demo.comments.models.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    ResponseEntity<Comment> getCommentById(Long id);
    ResponseEntity<List<Comment>> getAllComments();
    ResponseEntity<Comment> createComment(CommentDto commentDo) ;
    ResponseEntity<Comment> updateComment(CommentDto commentDto);
    ResponseEntity<?> deleteCommentById(Long id);



}
