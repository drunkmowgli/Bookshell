package org.asm.labs.service.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
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


    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Book save(String title, String authorsNames, String genreName) {
        String[] eachAuthorsName = authorsNames.split(",");
        Set<Author> authors = new HashSet<>();
        for (String authorName :
                eachAuthorsName) {
            Author author = new Author(authorName);
            authors.add(author);
        }
        Genre genre = new Genre(genreName);
        Book book = new Book(
                title,
                authors,
                genre
        );
        bookRepository.save(book);
        return book;
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
