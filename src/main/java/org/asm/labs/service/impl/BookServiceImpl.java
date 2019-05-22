package org.asm.labs.service.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {this.bookDao = bookDao;}


    @Override
    public void add(Book book) {
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
