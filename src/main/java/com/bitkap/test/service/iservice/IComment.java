package com.bitkap.test.service.iservice;


import com.bitkap.test.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IComment {

    // create new comment in associating to one user and one event
    Comment saveComment(Long userId, Long eventId, Comment comment);

    // update comment by her commentId
    Comment updateComment(Comment newComment, Long commentId);

    // get comments by eventId and userId
    Page<Comment> findByEventIdAndUserId(Long userId,Long eventId, Pageable pageable);

    // get all comments
    Page<Comment> getAllComment(Pageable pageable);

    //delete method
    ResponseEntity<?> deleteByEventIdUserIdAndCommentId(Long eventId ,Long userId,Long commentId);

}
