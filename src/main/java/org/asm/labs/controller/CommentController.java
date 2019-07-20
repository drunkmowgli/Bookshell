package org.asm.labs.controller;

import org.asm.labs.model.Comment;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private final CommentService commentService;

    @GetMapping("/books/{id}/comments")
    public String getComments(@PathVariable String id,
                              Model model) {
        model.addAttribute("id", id);
        List<Comment> comments = commentService.findByBookId(id);
        model.addAttribute("comments", comments);
        return "comments";
    }

    @PostMapping("/books/{id}/comments")
    public String addComment(@PathVariable String id,
                             @RequestParam String description,
                             Model model) throws BookNotExistException {
        model.addAttribute("id", id);
        commentService.save(description, id);
        return "redirect:/books/{id}/comments";
    }
}
