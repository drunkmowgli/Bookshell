package org.asm.labs.service.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.AuthorService;
import org.asm.labs.service.BookService;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookDao bookDao;
    
    private final AuthorService authorService;
    
    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }


    @Override
    public void add(String title, String authorsNames, String genreName) {
        String[] authorsString = authorsNames.split(",");
        List<Author> authors = new ArrayList<>();
        for (String authorName:
             authorsString) {
            Author author = authorService.getByName(authorName);
            authors.add(author);
        }
        Genre genre = genreService.getByGenreName(genreName);
        Book book = new Book(title, authors, genre);
        bookDao.add(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
    
    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
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
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Override
    public int count() {
        return bookDao.count();
    }
}
