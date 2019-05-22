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
}
