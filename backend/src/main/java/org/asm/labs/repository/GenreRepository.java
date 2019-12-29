package org.asm.labs.repository;

import org.asm.labs.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	Optional<Genre> findByGenreName(String genreName);
}
