package org.asm.labs.repository;

import org.asm.labs.domain.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    
}
