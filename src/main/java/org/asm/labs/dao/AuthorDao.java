package org.asm.labs.dao;

import org.asm.labs.entity.Author;

import java.util.List;

public interface AuthorDao {

    /**
     * Insert Author to AUTHORS.
     * @param author - Author
     */
    void add(Author author);

    /**
     * Get all Authors from DB
     * @return List of Authors
     */
    List<Author> getAll();

    /**
     * Get Author from DB by Name.
     * @param name - Author's name
     * @return Author
     */
    Author getByName(String name);

    /**
     * Get Author from DB by id.
     * @param id - Author's id
     * @return Author
     */
    Author getById(int id);

    /**
     * Remove Author from DB.
     * @param author - Author
     */
    void remove(Author author);

    /**
     * Count number of Authors.
     * @return number if Authors
     */
    int count();

}
