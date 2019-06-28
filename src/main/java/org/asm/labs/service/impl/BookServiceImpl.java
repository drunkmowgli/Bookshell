//package org.asm.labs.service.impl;
//
//import org.asm.labs.repository.BookRepositoryJpa;
//import org.asm.labs.entity.Author;
//import org.asm.labs.entity.Book;
//import org.asm.labs.entity.Genre;
//import org.asm.labs.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BookServiceImpl implements BookService {
//
//
//    private final BookRepositoryJpa bookRepositoryJpa;
//
//    private final AuthorService authorService;
//
//    private final GenreService genreService;
//
//    @Autowired
//    public BookServiceImpl(BookRepositoryJpa bookRepositoryJpa, AuthorService authorService, GenreService genreService) {
//        this.bookRepositoryJpa = bookRepositoryJpa;
//        this.authorService = authorService;
//        this.genreService = genreService;
//    }
//
//
//    @Override
//    public void add(String title, String authorsIds, int genreId) throws BookAlreadyExistException, AuthorDoesntExistException, GenreDoesntExistException {
//        String[] authorsStringIds = authorsIds.split(",");
//        List<Author> authors = new ArrayList<>();
//        for (String authorId :
//                authorsStringIds) {
//            Author author = authorService.getById(Integer.parseInt(authorId));
//            authors.add(author);
//        }
//        Genre genre = genreService.getById(genreId);
//        Book book = new Book(title, authors, genre);
//        try {
//            bookRepositoryJpa.add(book);
//        } catch (DuplicateKeyException e) {
//            throw new BookAlreadyExistException();
//        }
//    }
//
//    @Override
//    public List<Book> getAll() {
//        return bookRepositoryJpa.findAll();
//    }
//
//    @Override
//    public Book getById(int id) throws BookDoesntExistException {
//        return bookRepositoryJpa.findById(id)
//                .orElseThrow(BookDoesntExistException::new);
//    }
//
//    @Override
//    public void remove(int bookId) throws BookDoesntExistException {
//        Book book = bookRepositoryJpa.findById(bookId)
//                .orElseThrow(BookDoesntExistException::new);
//        bookRepositoryJpa.remove(book);
//    }
//
//    @Override
//    public int count() {
//        return bookRepositoryJpa.count();
//    }
//}
