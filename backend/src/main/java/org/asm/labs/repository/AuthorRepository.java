package org.asm.labs.repository;

import org.asm.labs.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByName(String authorName);
}
