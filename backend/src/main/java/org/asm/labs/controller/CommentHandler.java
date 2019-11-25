package org.asm.labs.controller;

import org.asm.labs.domain.Comment;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class CommentHandler {
    
    private final CommentRepository commentRepository;
    
    private final BookRepository bookRepository;
    
    @Autowired
    public CommentHandler(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }
    
    Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(HashMap.class)
                            .flatMap(payload -> createComment(
                                serverRequest.pathVariable("id"),
                                String.valueOf(payload.get("commentDescription"))))
                            .flatMap(response -> ServerResponse.created(UriComponentsBuilder
                                .fromPath(serverRequest
                                    .uri()
                                    .toString())
                                .path("/{id}")
                                .buildAndExpand(response.getId()).toUri()).build());
    }
    
    public Mono<ServerResponse> read(ServerRequest request) {
        return ServerResponse.ok().body(readComments(request.pathVariable("id")), Comment.class);
    }
    
    private Flux<Comment> readComments(String id) {
        return bookRepository.findById(id)
                             .flatMapMany(book -> commentRepository.findByBookId(book.getId()));
    }
    
    private Mono<Comment> createComment(String id, String commentDescription) {
        return bookRepository.findById(id)
                             .map(book -> new Comment(book, commentDescription))
                             .flatMap(commentRepository::save);
    }
}
