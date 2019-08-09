package org.asm.labs.service;

import org.asm.labs.model.Genre;

import java.util.List;

public interface GenreService {
    
    /**
     * Save Genre in DB.
     * @param genreName - genre
     */
    void save(String genreName);

    /**
     * Get all genres from DB.
     *
     * @return List of Genres
     */
    List<Genre> findAll();

    /**
     * Get Genre from DB by id.
     *
     * @param genreId - Genre's id
     *
     * @return Genre
     */
    Genre findById(String genreId) throws GenreNotExistException;
    
    /**
     * Remove genre from DB.
     * @param genreId - Genre
     */
    void remove(String genreId) throws GenreNotExistException;

    /**
     * Get genre from repository by name
     *
     * @param genreName - genre's name
     *
     * @return Genre
     *
     * @throws GenreNotExistException - Informs us that genre not exist
     */
    Genre findByGenreName(String genreName) throws GenreNotExistException;

    /**
     * Count number of Genres.
     *
     * @return number of Genres
     */
    long count();
}
