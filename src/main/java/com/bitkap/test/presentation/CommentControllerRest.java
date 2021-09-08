package com.bitkap.test.presentation;


import com.bitkap.test.model.Comment;
import com.bitkap.test.service.iservice.IComment;
import com.bitkap.test.service.iservice.IEvent;
import com.bitkap.test.service.iservice.IUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class CommentControllerRest {

    private final IComment iComment;
    private final IEvent iEvent;
    private final IUser iUser;


    public CommentControllerRest(IComment iComment, IEvent iEvent, IUser iUser) {
        this.iComment = iComment;
        this.iEvent = iEvent;
        this.iUser = iUser;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // get all comments by eventId and userId.
    // You have possibility to specify page, size and sorting to parameter your result
    // example of url for this get method :
    //localhost:8080/api/events/7/comments/users/1
    @GetMapping("/events/{eventId}/comments/users/{userId}")
    public Page<Comment> getAllCommentsByEventIdAndUserId(
            @PathVariable(value = "eventId") Long eventId,
            @PathVariable(value = "userId") Long userId,Pageable pageable) {
        return iComment.findByEventIdAndUserId(eventId, userId, pageable);
    }

    // get all comments in function of page, size and sort
    //example of url for this get method :
    // localhost:8080/api/events/users/comments (all comments) or
    // localhost:8080/api/events/users/comments?page=0&size=4&sort=date or description
    @GetMapping( "/events/users/comments")
    Page<Comment> getAllCommentPageable(Pageable pageable)   {
        return iComment.getAllComment(pageable);
    }


    //create comment in using userId and eventId
    // example of url for this post method :
    //localhost:8080/api/users/1/comments/events/7
    @PostMapping("/users/{userId}/comments/events/{eventId}")
    public Comment createComment(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "eventId") Long eventId,
            @Valid @RequestBody Comment comment){

        return iComment.saveComment(userId, eventId, comment);
    }


    // update comment by eventId and userId
    // example of url for this put method :
    // localhost:8080/api/events/7/comments/16/users/1
    @PutMapping("/events/{eventId}/comments/{commentId}/users/{userId}")
    public Comment updateCommentByEventIdAndUserId(
            @PathVariable(value = "eventId") Long eventId,
            @PathVariable(value = "commentId") Long commentId,
            @PathVariable(value = "userId") Long userId,
            @Valid @RequestBody Comment commentRequest) {

        iEvent.checkExistsEventById(eventId);

        System.out.println("je passe good ici");

        iUser.checkExistsUserById(userId);

        return iComment.updateComment(commentRequest, commentId);

    }


    //delete comment by eventId and userId
    // example of url for this delete method :
    // localhost:8080/api/events/7/users/1/comments/16
    @DeleteMapping("/events/{eventId}/users/{userId}/comments/{commentId}")
    public ResponseEntity<?> deleteCommentByEventIdAndUserId(
            @PathVariable(value = "eventId") Long eventId,
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "commentId") Long commentId) {

        return iComment.deleteByEventIdUserIdAndCommentId(eventId, userId, commentId);
    }



}
