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

    /**
     * Get Genre from DB by Genres's name.
     * @param genreName - Genre's name
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
}
