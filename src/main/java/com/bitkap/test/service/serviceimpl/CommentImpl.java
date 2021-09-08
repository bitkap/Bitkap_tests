package com.bitkap.test.service.serviceimpl;


import com.bitkap.test.dao.CommentRepo;
import com.bitkap.test.dao.EventRepo;
import com.bitkap.test.model.Comment;
import com.bitkap.test.model.ResourceNotFoundException;
import com.bitkap.test.model.User;
import com.bitkap.test.service.iservice.IComment;
import com.bitkap.test.service.iservice.IUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CommentImpl implements IComment {

    private final CommentRepo commentRepo;
    private final EventRepo eventRepo;
    private final IUser iUser;

    public CommentImpl(CommentRepo commentRepo, EventRepo eventRepo,IUser iUser) {
        this.commentRepo = commentRepo;
        this.eventRepo = eventRepo;
        this.iUser = iUser;
    }


    @Override
    public Comment saveComment(Long userId, Long eventId, Comment comment) {
        User user = iUser.findUserById(userId);


        return eventRepo.findById(eventId).map(event -> {
            comment.setEvent(event);
            comment.setUser(user);
            comment.setDate(new Date());
            return commentRepo.save(comment);
        }).orElseThrow(()-> new ResourceNotFoundException("EventId" +eventId + "not found"));

    }


    @Override
    public Comment updateComment(Comment newComment, Long commentId) {
        return commentRepo.findById(commentId).map(comment -> {
            comment.setDescription(newComment.getDescription());
            comment.setDate(new Date());
            return commentRepo.save(comment);
        }).orElseGet(()-> {
            newComment.setId(commentId);
            return commentRepo.save(newComment);
        });
    }

    public Page<Comment> findByEventIdAndUserId(Long eventId,Long userId, Pageable pageable) {
        return commentRepo.findByEvent_IdAndUser_Id(eventId, userId, pageable);
    }

    @Override
    public Page<Comment> getAllComment(Pageable pageable) {
        return commentRepo.findAll(pageable);
    }


    @Override
    public ResponseEntity<?> deleteByEventIdUserIdAndCommentId(Long userId,Long eventId,Long commentId) {
        return commentRepo.findByEvent_IdAndUser_IdAndId(userId,eventId,commentId).map(comment -> {
            commentRepo.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with commentId" + commentId + "and EventId" +eventId));
    }





}
