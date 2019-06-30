package org.asm.labs.service.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.repository.BookRepositoryJpa;
import org.asm.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {


    private final BookRepositoryJpa bookRepositoryJpa;

    private final AuthorService authorService;

    private final GenreService genreService;


    @Autowired
    public BookServiceImpl(BookRepositoryJpa bookRepositoryJpa,
                           AuthorService authorService,
                           GenreService genreService) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.authorService = authorService;
        this.genreService = genreService;
    }


    @Override
    public void save(String title, String authorsIds, int genreId) throws AuthorNotExistException,
            GenreNotExistException {
        String[] authorsStringIds = authorsIds.split(",");
        Set<Author> authors = new HashSet<>();
        for (String authorId :
                authorsStringIds) {
            Author author = authorService.findById(Integer.parseInt(authorId));
            authors.add(author);
        }
        Genre genre = genreService.findById(genreId);
        Book book = new Book(title, authors, genre);
        bookRepositoryJpa.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    public Book findById(int id) throws BookNotExistException {
        try {
            return bookRepositoryJpa.findById(id);
        } catch (NoResultException e) {
            throw new BookNotExistException();
        }
    }

    @Override
    public void remove(int bookId) throws BookNotExistException {
        try {
            Book book = bookRepositoryJpa.findById(bookId);
            bookRepositoryJpa.remove(book);
        } catch (NoResultException e) {
            throw new BookNotExistException();
        }

    }

    @Override
    public long count() {
        return bookRepositoryJpa.count();
    }
}
