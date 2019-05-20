package org.asm.labs.dao;

import org.asm.labs.entity.Book;

public interface BookDao {
    
    /**
     * Insert book to Books.
     * @param book - Book
     */
    void add(Book book);
}
