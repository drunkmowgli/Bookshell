package org.asm.labs.service;

import org.asm.labs.model.Genre;

import java.util.List;

public interface GenreService {

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
    Genre findById(long id) throws GenreNotExistException;

    /**
     * Count number of Genres.
     *
     * @return number of Genres
     */
    long count();
}
