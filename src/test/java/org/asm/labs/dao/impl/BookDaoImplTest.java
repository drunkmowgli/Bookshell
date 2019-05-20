package org.asm.labs.dao.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@DisplayName("Book DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoImplTest {
    
    @Autowired
    BookDao bookDao;
    
    private Book book = new Book("Test Comic Book", "#Test Add", Collections.singletonList(
            new Author("Jack Kirby")));
    
    @Test
    void add() {
        bookDao.add(book);
    }
}