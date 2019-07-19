package org.asm.labs.controller;

import org.asm.labs.model.Comment;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String getAll(Model model) {
        List<Comment> comments = commentService.findAll();
        model.addAttribute("comments", comments);
        return "comments";
    }

    @GetMapping("/commentAddForm/{id}")
    public String commentAddForm(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "add_comment_form";
    }

    //TODO:Realized this method
    @PostMapping("/comments")
    public String addComment(@RequestParam String description,
                             @ModelAttribute("id") String bookId,
                             Model model) throws BookNotExistException {

        System.out.println("MASLOV: " + bookId);
        Comment comment = commentService.save(description, bookId);
        model.addAttribute("comment", comment);
        return "redirect:/comments";
    }
}
