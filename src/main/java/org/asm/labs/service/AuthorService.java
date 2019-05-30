package org.asm.labs.service;

import org.asm.labs.entity.Author;

import java.util.List;

public interface AuthorService {

    /**
     * Add Author to DB.
     *
     * @param authorName - Author's name
     */
    void add(String authorName) throws AuthorAlreadyExistException;

    /**
     * Get all Authors from DB.
     *
     * @return List of Authors
     */
    List<Author> getAll();

    /**
     * Get Author from DB by id.
     *
     * @param authorId - Author's id
     *
     * @return Author
     */
    Author getById(int authorId) throws AuthorDoesntExistException;

    /**
     * Remove Author from DB.
     *
     * @param authorId - Author's id
     */
    void remove(int authorId) throws AuthorDoesntExistException;

    /**
     * Count number of Authors in DB.
     *
     * @return number of Authors
     */
    int count();
}
