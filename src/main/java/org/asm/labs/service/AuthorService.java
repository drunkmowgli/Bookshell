package org.asm.labs.service;

import org.asm.labs.entities.Author;

public interface AuthorService {

    /**
     * Add Author to DB
     * @param author - Author
     */
    void add(Author author);

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
     * Count number of Authors in DB.
     * @return number of Authors
     */
    int count();
}
