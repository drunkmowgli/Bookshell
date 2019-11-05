/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import org.asm.labs.domain.Author;
import org.asm.labs.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class AuthorController {
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    //    @PostMapping("/api/v1/authors")
//    public Mono<Author> create(@RequestBody Author author) {
//        return authorRepository.save(author);
//    }
    @PostMapping("/api/v1/authors")
    public Mono<ResponseEntity> create(@RequestBody Author author) {
        return authorRepository.save(author).map(a -> ResponseEntity.created(URI.create("/api/v1/authors/" + a.getId())).build());
    }
    
    @GetMapping("/api/v1/authors")
    public Flux<Author> readAll() {
        return authorRepository.findAll();
    }
}
