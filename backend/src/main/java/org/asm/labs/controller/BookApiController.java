package org.asm.labs.controller;

import org.asm.labs.model.Book;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookApiController {

    private final BookService bookService;

    @Autowired
    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/v1/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @DeleteMapping("/api/v1/books/{id}/delete")
    public void deleteBook(@PathVariable long id) throws BookNotExistException {
        bookService.remove(id);
    }

    @GetMapping("/api/v1/books/{id}")
    public Book getBook(@PathVariable long id) throws BookNotExistException {
        return bookService.findById(id);
    }

}