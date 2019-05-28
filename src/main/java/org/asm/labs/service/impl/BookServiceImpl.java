package org.asm.labs.service.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }


    @Override
    public void add(String title, String authorsNames, String genreName) throws BookAlreadyExistException {
        String[] authorsString = authorsNames.split(",");
        List<Author> authors = new ArrayList<>();
        for (String authorName :
                authorsString) {
            Author author = authorService.getByName(authorName);
            authors.add(author);
        }
        Genre genre = genreService.getByGenreName(genreName);
        Book book = new Book(title, authors, genre);
        try {
            bookDao.add(book);
        } catch (DuplicateKeyException e) {
            throw new BookAlreadyExistException();
        }
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getByTitle(String title) throws BookDoesntExistException {
        try {
            return bookDao.getByTitle(title);
        } catch (EmptyResultDataAccessException e) {
            throw new BookDoesntExistException();
        }
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {
        return bookDao.getAllByGenre(genre);
    }

    @Override
    public List<Book> getAllByAuthor(Author author) {
        return bookDao.getAllByAuthor(author);
    }

    @Override
    public Book getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public void remove(String bookName) throws BookDoesntExistException {
        try {
            Book book = bookDao.getByTitle(bookName);
            bookDao.remove(book);
        } catch (EmptyResultDataAccessException e) {
            throw new BookDoesntExistException();
        }
    }

    @Override
    public int count() {
        return bookDao.count();
    }
}
