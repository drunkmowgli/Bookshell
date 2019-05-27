package org.asm.labs.service;

import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;

import java.util.List;

public interface BookService {

    /**
     * Add book to DB.
     * @param title - Book's title
     * @param authorsNames - List of Authors
     * @param genreName - Genre's name
     */
    void add(String title, String authorsNames, String genreName);

    /**
     * Get all Books from DB.
     * @return List of Books
     */
    List<Book> getAll();

    /**
     * Get Book from DB by Title.
     * @param title - Book's title
     * @return Book
     */
    Book getByTitle(String title);

    /**
     * Get Book by Id
     * @param id - Book's id
     * @return Book
     */
    Book getById(int id);

    /**
     * Get all books by Genre.
     * @param genre - Genre
     * @return List of Books by Genre
     */
    List<Book> getAllByGenre(Genre genre);

    /**
     * Get all books from DB by Author
     * @param author - Author
     * @return List of Books
     */
    List<Book> getAllByAuthor(Author author);

    /**
     * Remove Book from DB.
     * @param book - Book
     */
    void remove(Book book);

    /**
     * Count number of Books in DB.
     * @return number of Books
     */
    int count();
}
