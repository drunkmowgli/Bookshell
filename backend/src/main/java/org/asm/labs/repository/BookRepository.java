package org.asm.labs.repository;

import org.asm.labs.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Override
    @EntityGraph("bookGraph")
    List<Book> findAll();
    
}