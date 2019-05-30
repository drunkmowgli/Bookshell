package org.asm.labs.service;

import org.asm.labs.entity.Book;

import java.util.List;

public interface BookService {

    /**
     * Add book to DB.
     *
     * @param title        - Book's title
     * @param authorsNames - List of Authors
     * @param genreId      - Genre's id
     */
    void add(String title, String authorsNames, int genreId) throws BookAlreadyExistException, AuthorDoesntExistException;

    /**
     * Get all Books from DB.
     *
     * @return List of Books
     */
    List<Book> getAll();

    /**
     * Get Book by Id
     *
     * @param id - Book's id
     *
     * @return Book
     */
    Book getById(int id) throws BookDoesntExistException;

    /**
     * Remove Book from DB.
     *
     * @param id - Book's id
     */
    void remove(int id) throws BookDoesntExistException;

    /**
     * Count number of Books in DB.
     *
     * @return number of Books
     */
    int count();
}
