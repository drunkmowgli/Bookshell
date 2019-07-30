package org.asm.labs.service;

import org.asm.labs.model.Author;

import java.util.List;

public interface AuthorService {

    /**
     * Add Author to DB.
     *
     * @param authorName - Author's name
     */
    Author save(String authorName);

    /**
     * Get all Authors from DB.
     *
     * @return List of Authors
     */
    List<Author> findAll();

    /**
     * Get Author from DB by id.
     *
     * @param authorId - Author's id
     *
     * @return Author
     */
    Author findById(long authorId) throws AuthorNotExistException;

    /**
     * Get Author from repository by name.
     *
     * @param authorName - Author's name
     *
     * @return Author
     *
     * @throws AuthorNotExistException - Informs us, that author not exist
     */
    Author findByAuthorName(String authorName) throws AuthorNotExistException;

    /**
     * Remove Author from DB.
     *
     * @param authorId - Author's id
     */
    void delete(long authorId) throws AuthorNotExistException;

    /**
     * Count number of Authors in DB.
     *
     * @return number of Authors
     */
    long count();
}
