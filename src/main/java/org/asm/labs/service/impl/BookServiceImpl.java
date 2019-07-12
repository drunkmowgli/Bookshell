package org.asm.labs.service.impl;

import org.asm.labs.model.Book;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.AuthorService;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void save(Book book) {
        bookRepository.save(book);
    }
    
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String bookId) throws BookNotExistException {
        return bookRepository.findById(bookId).orElseThrow(BookNotExistException::new);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void remove(String bookId) throws BookNotExistException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotExistException::new);
        bookRepository.delete(book);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }
}
