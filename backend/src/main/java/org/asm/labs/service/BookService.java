package org.asm.labs.service;

import org.asm.labs.model.Book;

import java.util.List;

public interface BookService {

    /**
     * Add book to DB.
     *
     * @param title        - Book's title
     * @param authorsNames - List of Authors
     * @param genreName      - Genre's name
     */
    void save(String title, String authorsNames, String genreName) throws AuthorNotExistException, GenreNotExistException;

    /**
     * Get all Books from DB.
     *
     * @return List of Books
     */
    List<Book> findAll();

    /**
     * Get Book by Id
     *
     * @param id - Book's id
     *
     * @return Book
     */
    Book findById(long id) throws BookNotExistException;

    /**
     * Remove Book from DB.
     *
     * @param id - Book's id
     */
    void remove(long id) throws BookNotExistException;

    /**
     * Count number of Books in DB.
     *
     * @return number of Books
     */
    long count();
}
