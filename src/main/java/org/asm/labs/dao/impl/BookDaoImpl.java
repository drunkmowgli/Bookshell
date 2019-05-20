package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    
    private final NamedParameterJdbcOperations jdbc;
    
    private final AuthorDao authorDao;
    
    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations jdbc, AuthorDao authorDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
    }
    
    @Override
    public void add(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        int bookId = book.getId();
        try {
            jdbc.update(
                    "insert into books (title, description) values (:title, :description)",
                    params
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        book.getAuthors().forEach(author -> {
            Author authorWithId = checkAuthor(book, author);
            Map<String, Object> authorParams = new HashMap<>();
            if (authorWithId != null) {
                authorParams.put("author_id", authorWithId.getId());
                authorParams.put("book_id", bookId);
            }
            jdbc.update(
                    "insert into books (id, author_id) values (:book_id, :author_id)",
                    authorParams
            );
        });
    }
    
    private Author checkAuthor(Book book, Author author) {
        Author authorWithId;
        try {
            authorWithId = authorDao.getByName(author.getName());
            return authorWithId;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            Map<String, Object> removeParams = new HashMap<>();
            removeParams.put("title", book.getTitle());
            jdbc.update(
                    "delete from books where title = :title",
                    removeParams
            );
            return null;
        }
    }
    
    private static class BookMapper implements RowMapper<Book> {
        
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            return new Book(id, title, description);
        }
    }
}
