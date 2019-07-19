package org.asm.labs.controller;

import org.asm.labs.model.Book;
import org.asm.labs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {this.bookService = bookService;}


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
}
