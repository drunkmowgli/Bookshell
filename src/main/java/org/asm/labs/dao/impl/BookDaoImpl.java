package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookDaoImpl implements BookDao {

    private final AuthorDao authorDao;

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public BookDaoImpl(AuthorDao authorDao, NamedParameterJdbcOperations jdbc) {
        this.authorDao = authorDao;
        this.jdbc = jdbc;
    }

    @Override
    public void add(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("author_id", book.getAuthor().getId());
        try {
            jdbc.update(
                    "insert into books (title, author_id) values (:title, :author_id)",
                    params
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.query(
                "select * from books",
                new HashMap<>(),
                new BookMapper()
        );

        return books.stream().map(this::setAuthor).collect(Collectors.toList());
    }

    /**
     * Set Author to Book
     * @param book - Book
     * @return Book with Author
     */
    private Book setAuthor(Book book) {
        Author author = authorDao.getByBookId(book.getId());
        return new Book(book, author);
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        return jdbc.queryForObject(
                "select * from books where title = :title",
                params,
                new BookMapper()
        );
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(
                "select * from books where id = :id",
                params,
                new BookMapper()
        );
    }

    @Override
    public void remove(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookTitle", book.getTitle());
        try {
            jdbc.update(
                    "delete from books where title = :bookTitle",
                    params
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from books",
                new HashMap<>(),
                Integer.class
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            return new Book(id, title);
        }
    }
}
