/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import org.asm.labs.domain.Book;
import org.asm.labs.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
public class BookController {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @PostMapping("/api/v1/books")
    public Mono<Book> create(@RequestBody Book book) {
        book.setAuthors(book.getAuthors()
                            .stream()
                            .filter(a -> a.getName() != null && !a.getName().equals(""))
                            .collect(Collectors.toList()));
        return bookRepository.save(book);
    }
    
    
}
