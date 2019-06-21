package org.asm.labs.dao.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.asm.labs.dao.impl.SqlQueryTemplates.*;

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
                .addValue("book_title", book.getTitle())
                .addValue("genre_id", book.getGenre().getId());
        jdbc.update(
                INSERT_BOOK,
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
                    INSERT_BOOK_TO_REFERENCE,
                    authorsParameterSource,
                    authorsKeyHolder
            );
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                SELECT_ALL_BOOK_BY_ORDER,
                new HashMap<>(),
                new ListResultSetExtractor()
        );
    }

    @Override
    public Optional<Book> getById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", id);
        List<Book> books = jdbc.query(
                SELECT_BOOK_BY_ID,
                params,
                new ListResultSetExtractor()
        );
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

    @Override
    public void remove(Book book) throws DataAccessException {
        Map<String, Object> referenceParams = new HashMap<>();
        referenceParams.put("book_id", book.getId());
        jdbc.update(
                DELETE_BOOK_FROM_REFERENCE,
                referenceParams
        );
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", book.getId());
        jdbc.update(
                DELETE_BOOK_BY_ID,
                params
        );
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                COUNT_BOOKS,
                new HashMap<>(),
                Integer.class
        );
    }

    private static class ListResultSetExtractor implements ResultSetExtractor<List<Book>> {
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
}