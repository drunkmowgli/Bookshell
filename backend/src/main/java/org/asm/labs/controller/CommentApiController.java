package org.asm.labs.controller;

import org.asm.labs.model.Comment;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    
    private final CommentService commentService;
    
    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @GetMapping("/api/v1/books/{id}/comments")
    public List<Comment> getAllCommentToBook(@PathVariable long id) {
        return commentService.findByBookId(id);
    }

    @PostMapping("/api/v1/books/{id}/comments")
    public Comment addComment(@RequestParam String commentDescription,
                              @PathVariable long id) {
        return commentService.save(commentDescription, id);
    }
}
