package org.asm.labs.service;

import org.asm.labs.entity.Author;

import java.util.List;

public interface AuthorService {

    /**
     * Add Author to DB.
     *
     * @param author - Author
     */
    void add(Author author);

    /**
     * Get all Authors from DB.
     *
     * @return List of Authors
     */
    List<Author> getAll();

    /**
     * Get Author from DB by Name.
     *
     * @param name - Author's name
     *
     * @return Author
     */
    Author getByName(String name);

    /**
     * Get Author from DB by id.
     *
     * @param id - Author's id
     *
     * @return Author
     */
    Author getById(int id);

    /**
     * Remove Author from DB.
     *
     * @param authorName - Author
     */
    void remove(String authorName);

    /**
     * Count number of Authors in DB.
     *
     * @return number of Authors
     */
    int count();
}
