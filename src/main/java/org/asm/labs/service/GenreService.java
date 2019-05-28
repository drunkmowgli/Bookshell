package org.asm.labs.service;

import org.asm.labs.entity.Genre;

import java.util.List;

public interface GenreService {
    
    /**
     * Insert Genre to DB.
     * @param genre - Genre
     */
    void add(Genre genre);
    
    /**
     * Get all genres from DB.
     * @return List of Genres
     */
    List<Genre> getAll();

    /**
     * Get Genre by Genre's name from DB.
     * @param genreName - genreName
     * @return Genre
     */
    Genre getByGenreName(String genreName);

    /**
     * Get Genre from DB by id.
     * @param id - Genre's id
     * @return Genre
     */
    Genre getById(int id);

    /**
     * Count number of Genres.
     * @return number of Genres
     */
    int count();

    /**
     * Remove genre from DB.
     * @param genreName - Genre's name
     */
    void remove(String genreName);
}
