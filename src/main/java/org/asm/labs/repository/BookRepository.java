package org.asm.labs.repository;

import org.asm.labs.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

}
