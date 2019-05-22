package org.asm.labs.dao.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
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

@Repository
public class BookDaoImpl implements BookDao {
    
    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookTitle", book.getTitle());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        try {
            jdbc.update(
                    "insert into books (title, author_id, genre_id) values (:bookTitle, :author_id, :genre_id)" +
                            "on conflict (title)" +
                            "do nothing",
                    params
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select * from books b inner join authors a on b.author_id = a.id inner join genres g on b.genre_id = g.id",
                new HashMap<>(),
                new BookMapper()
        );
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookTitle", title);
        return jdbc.queryForObject(
                "select * from books b inner join authors a on b.author_id = a.id " +
                        "inner join genres g on b.genre_id = g.id where b.title = :bookTitle",
                params,
                new BookMapper()
        );
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", id);
        return jdbc.queryForObject(
                "select * from books b inner join authors a on b.author_id = a.id " +
                        "inner join genres g on b.genre_id = g.id where b.id = :bookId",
                params,
                new BookMapper()
        );
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {
        Map<String, Object> params = new HashMap<>();
        params.put("genreName", genre.getGenreName());
        return jdbc.query(
                "select * from books b inner join authors a on b.author_id = a.id " +
                        "inner join genres g on b.genre_id = g.id where g.genre = :genreName",
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
            int authorId = resultSet.getInt("author_id");
            String authorName = resultSet.getString("author_name");
            Author author = new Author(authorId, authorName);
            int genreId = resultSet.getInt("genre_id");
            String genreName = resultSet.getString("genre");
            Genre genre = new Genre(genreId, genreName);
            return new Book(id, title, author, genre);
        }
    }
}
