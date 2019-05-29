package org.asm.labs.dao.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;


    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add(Book book) throws DataAccessException {
        KeyHolder booksKeyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("bookTitle", book.getTitle())
                .addValue("genre_id", book.getGenre().getId());
        jdbc.update(
                "insert into books (title, genre_id) values (:bookTitle, :genre_id)",
                parameterSource,
                booksKeyHolder
        );

        KeyHolder authorsKeyHolder = new GeneratedKeyHolder();
        for (Author author :
                book.getAuthors()) {
            SqlParameterSource authorsParameterSource = new MapSqlParameterSource()
                    .addValue("book_id", booksKeyHolder.getKeys().get("id"))
                    .addValue("author_id", author.getId());
            jdbc.update(
                    "insert into reference (book_id, author_id) values (:book_id, :author_id)",
                    authorsParameterSource,
                    authorsKeyHolder
            );
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select * from books b inner join genres g on b.genre_id = g.id " +
                        "                 inner join reference r on b.id = r.book_id " +
                        "                 inner join authors a on r.author_id = a.id " +
                        "                 order by b.id ASC," +
                        "                          g.id ASC," +
                        "                          a.id ASC",
                new HashMap<>(),
                new BookExtractor()
        );
    }


    @Override
    public List<Book> getByTitle(String title) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("bookTitle", title);
        return jdbc.query(
                "select * from books b inner join genres g on b.genre_id = g.id" +
                        "                 inner join reference r on b.id = r.book_id" +
                        "                 inner join authors a on r.author_id = a.id where b.title = :bookTitle " +
                        "order by b.id ASC," +
                        "         g.id ASC," +
                        "         a.id ASC",
                params,
                new BookExtractor()
        );
    }

    @Override
    public Book getById(int id) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", id);
        return jdbc.queryForObject(
                "select * from books b inner join genres g on b.genre_id = g.id" +
                        "                 inner join reference r on b.id = r.book_id" +
                        "                 inner join authors a on r.author_id = a.id where b.id = :bookId",
                params,
                new BookMapper()
        );
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {
        Map<String, Object> params = new HashMap<>();
        params.put("genreName", genre.getGenreName());
        return jdbc.query(
                "select * from books b inner join genres g on b.genre_id = g.id" +
                        "                 inner join reference r on b.id = r.book_id" +
                        "                 inner join authors a on r.author_id = a.id where g.genre = :genreName",
                params,
                new BookExtractor()
        );
    }

    @Override
    public List<Book> getAllByAuthor(Author author) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("author_id", author.getId());
        return jdbc.query(
                "select * from books b inner join genres g on b.genre_id = g.id " +
                        "inner join reference r on b.id = r.book_id " +
                        "inner join authors a on r.author_id = a.id where a.id = :author_id",
                params,
                new BookExtractor()
        );
    }

    @Override
    public void remove(Book book) {
        Map<String, Object> referenceParams = new HashMap<>();
        referenceParams.put("book_id", book.getId());
        jdbc.update(
                "delete from reference where book_id = :book_id",
                referenceParams
        );
        Map<String, Object> params = new HashMap<>();
        params.put("bookTitle", book.getTitle());
        jdbc.update(
                "delete from books where title = :bookTitle",
                params
        );
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from books",
                new HashMap<>(),
                Integer.class
        );
    }

    private static class BookExtractor implements ResultSetExtractor<List<Book>> {

        @Override
        public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, List<Author>> bookToAuthors = new HashMap<>();
            Map<Integer, String> bootToTitle = new HashMap<>();
            Map<Integer, Genre> bookToGenre = new HashMap<>();
            while (rs.next()) {
                int checkingKey = rs.getInt("book_id");
                if (!bookToAuthors.containsKey(checkingKey)) {
                    bookToAuthors.put(checkingKey, new ArrayList<>());
                }
                List<Author> authors = bookToAuthors.get(checkingKey);
                Author author = new Author(rs.getInt("author_id"), rs.getString("author_name"));
                authors.add(author);
                bootToTitle.put(checkingKey, rs.getString("title"));
                Genre genre = new Genre(rs.getInt("genre_id"), rs.getString("genre"));
                bookToGenre.put(checkingKey, genre);
            }

            Set<Integer> booksKeySet = bookToAuthors.keySet();

            List<Book> books = new ArrayList<>();

            for (Integer key : booksKeySet) {
                int bookId = key;
                String title = bootToTitle.get(key);
                List<Author> authors = bookToAuthors.get(key);
                Genre genre = bookToGenre.get(key);
                Book book = new Book(bookId, title, authors, genre);
                books.add(book);
            }

            return books;
        }
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            String authorName = resultSet.getString("author_name");
            int genreId = resultSet.getInt("genre_id");
            String genreName = resultSet.getString("genre");
            Author author = new Author(authorId, authorName);
            List<Author> authors = new ArrayList<>();
            authors.add(author);
            Genre genre = new Genre(genreId, genreName);
            return new Book(id, title, authors, genre);
        }
    }
}