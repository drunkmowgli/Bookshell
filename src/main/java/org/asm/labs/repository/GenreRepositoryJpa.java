package org.asm.labs.repository;

import org.asm.labs.entity.Genre;

import java.util.List;

public interface GenreRepositoryJpa {

    /**
     * Get all genres from DB.
     *
     * @return List of Genres
     */
    List<Genre> findAll();

    /**
     * Get Genre from DB by id.
     *
     * @param id - Genre's id
     *
     * @return Genre
     */
    Genre findById(int id);

    /**
     * Count number of Genres.
     *
     * @return number of Genres
     */
    long count();

}
