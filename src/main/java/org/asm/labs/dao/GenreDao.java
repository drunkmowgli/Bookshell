package org.asm.labs.dao;

import org.asm.labs.entity.Genre;

import java.util.List;

public interface GenreDao {

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
