package org.asm.labs.controller;

import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    
    private final CommentService commentService;

    @Autowired
    public BookController(BookService bookService, CommentService commentService) {this.bookService = bookService;
        this.commentService = commentService;
    }


    @GetMapping({"/", "/books"})
    public String getAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/bookAddForm")
    public String bookAddForm() {
        return "add_book_form";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam String title,
                          @RequestParam String authors,
                          @RequestParam String genre,
                          Model model) {
        Book book = bookService.save(title, authors, genre);
        model.addAttribute("book", book);
        return "redirect:/";
    }
    
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
