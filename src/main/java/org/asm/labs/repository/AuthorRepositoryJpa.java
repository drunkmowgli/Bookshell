package org.asm.labs.repository;

import org.asm.labs.entity.Author;

import java.util.List;

public interface AuthorRepositoryJpa {

    /**
     * Insert Author to DB.
     *
     * @param author - Author
     */
    void save(Author author);

    /**
     * Get all Authors from DB.
     *
     * @return List of Authors
     */
    List<Author> findAll();

    /**
     * Get Author from DB by id.
     *
     * @param id - Author's id
     *
     * @return Author
     */
    Author findById(int id);

    /**
     * Remove Author from DB.
     *
     * @param author - Author
     */
    void remove(Author author);

//    /**
//     * Count number of Authors.
//     *
//     * @return number of Authors
//     */
//    int count();

}
