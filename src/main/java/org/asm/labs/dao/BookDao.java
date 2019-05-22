package org.asm.labs.dao;

import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;

import java.util.List;

public interface BookDao {
    
    /**
     * Insert book to DB.
     * @param book - Book
     */
    void add(Book book);

    /**
     * Get all books from DB.
     * @return List of Books
     */
    List<Book> getAll();

    /**
     * Get book from DB by Title.
     * @param title - Book's title
     * @return Book
     */
    Book getByTitle(String title);

    /**
     * Get book from DB by ID.
     * @param id - Book's id
     * @return Book
     */
    Book getById(int id);

    /**
     * Get books from DB by Genre
     * @param genre - Genre
     * @return Book
     */
    List<Book> getAllByGenre(Genre genre);

    /**
     * Remove Book from DB
     * @param book - Book
     */
    void remove(Book book);

    /**
     * Count number of Books.
     * @return number of Books
     */
    int count();
}
