package org.asm.labs.service;

import org.asm.labs.model.Book;

import java.util.List;

public interface BookService {
    
    /**
     * Save book to DB/
     * @param book - book
     */
    void save(Book book);

    /**
     * Get all Books from DB.
     *
     * @return List of Books
     */
    List<Book> findAll();

    /**
     * Get Book by Id
     *
     * @param bookId - Book's id
     *
     * @return Book
     */
    Book findById(String bookId) throws BookNotExistException;

    /**
     * Remove Book from DB.
     *
     * @param bookId - Book's id
     */
    void remove(String bookId) throws BookNotExistException;

    /**
     * Count number of Books in DB.
     *
     * @return number of Books
     */
    long count();
}
