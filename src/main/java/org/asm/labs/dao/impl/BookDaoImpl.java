package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        params.put("author_id", book.getAuthor().getId());
        try {
            jdbc.update(
                    "insert into books (title, description, author_id) values (:title, :description, :author_id)",
                    params
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
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
