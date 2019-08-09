package org.asm.labs.repository;

import org.asm.labs.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {


    Optional<Author> findByName(String authorName);
}
