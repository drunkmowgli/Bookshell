package org.asm.labs.repository;

import org.asm.labs.domain.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    
    Flux<Comment> findByBookId(String id);
}
