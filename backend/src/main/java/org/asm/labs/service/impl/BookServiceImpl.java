package org.asm.labs.service.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class BookServiceImpl implements BookService {
    
    
    private final BookRepository bookRepository;
    
    private final AuthorService authorService;
    
    private final GenreService genreService;
    
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }
    
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(String title, String authorsIds, long genreId) throws AuthorNotExistException,
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
        bookRepository.save(book);
    }
    
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    @Override
    public Book findById(long id) throws BookNotExistException {
        return bookRepository.findById(id).orElseThrow(BookNotExistException::new);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void remove(long bookId) throws BookNotExistException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotExistException::new);
        bookRepository.delete(book);
    }
    
    @Override
    public long count() {
        return bookRepository.count();
    }
}