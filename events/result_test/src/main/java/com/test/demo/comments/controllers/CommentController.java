package com.test.demo.comments.controllers;

import com.test.demo.comments.entityDto.CommentDto;
import com.test.demo.comments.models.Comment;
import com.test.demo.comments.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Comment>> getAllRoles() {
        return commentService.getAllComments();
    }
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }
    @GetMapping("/detail")
    private ResponseEntity<Comment> getComment(@RequestParam("id") Long id)
    {
        return commentService.getCommentById(id);
    }
    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteComment(@RequestParam("id") Long id)
    {
        return commentService.deleteCommentById(id);
    }
    @PutMapping("/update")
    private ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto)
    {
        return commentService.updateComment(commentDto);
    }
}
