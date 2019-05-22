package org.asm.labs.service;

import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;

import java.util.List;

public interface BookService {

    /**
     * Add book to DB
     * @param book - Book
     */
    void add(Book book);

    /**
     * Get all Books from DB
     * @return List of Books
     */
    List<Book> getAll();

    /**
     * Get Book by Title
     * @param title - Book's title
     * @return Book
     */
    Book getByTitle(String title);
    
    /**
     * Get all books by Genre
     * @param genre - Genre
     * @return List of Books by Genre
     */
    List<Book> getAllByGenre(Genre genre);

    /**
     * Get Book by Id
     * @param id - Book's id
     * @return Book
     */
    Book getById(int id);

    /**
     * Remove Book from DB
     * @param book - Book
     */
    void remove(Book book);

    /**
     * Count number of Books in DB.
     * @return number of Books
     */
    int count();
}
