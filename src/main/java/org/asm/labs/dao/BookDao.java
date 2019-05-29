package org.asm.labs.dao;

import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    /**
     * Insert book to DB.
     *
     * @param book - Book
     */
    void add(Book book);

    /**
     * Get all books from DB.
     *
     * @return List of Books
     */
    List<Book> getAll();

    /**
     * Get book from DB by Title.
     *
     * @param title - Book's title
     *
     * @return Book
     */
    List<Book> getByTitle(String title);

    /**
     * Get book from DB by ID.
     *
     * @param id - Book's id
     *
     * @return Book
     */
    Optional<Book> getById(int id);

    /**
     * Get all books from DB by Genre
     *
     * @param genre - Genre
     *
     * @return Book
     */
    List<Book> getAllByGenre(Genre genre);

    /**
     * Get all books from DB by Author
     *
     * @param author - Author
     *
     * @return List of Books
     */
    List<Book> getAllByAuthor(Author author);

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
    int count();
}
