package org.asm.labs.repository;

import org.asm.labs.entity.Book;

import java.util.List;

public interface BookRepositoryJpa {

    /**
     * Insert book to DB.
     *
     * @param book - Book
     */
    void save(Book book);

    /**
     * Get all books from DB.
     *
     * @return List of Books
     */
    List<Book> findAll();

    /**
     * Get book from DB by ID.
     *
     * @param id - Book's id
     *
     * @return Book
     */
    Book findById(int id);

    /**
     * Remove Book from DB
     *
     * @param book - Book
     */
    void remove(Book book);

    /**
     * Count number of Books.
     *
     * @return number of Books
     */
    long count();
}
