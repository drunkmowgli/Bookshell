package org.asm.labs.repository.impl;

import org.asm.labs.entity.Book;
import org.asm.labs.repository.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {


    @PersistenceContext
    private EntityManager em;


    @Autowired
    public BookRepositoryJpaImpl(EntityManager em) {
        this.em = em;
    }

//    @Override
//    public void add(Book book) throws DataAccessException {
//        KeyHolder booksKeyHolder = new GeneratedKeyHolder();
//        SqlParameterSource parameterSource = new MapSqlParameterSource()
//                .addValue("book_title", book.getTitle())
//                .addValue("genre_id", book.getGenre().getId());
//        jdbc.update(
//                INSERT_BOOK,
//                parameterSource,
//                booksKeyHolder
//        );
//
//        KeyHolder authorsKeyHolder = new GeneratedKeyHolder();
//        for (Author author :
//                book.getAuthors()) {
//            SqlParameterSource authorsParameterSource = new MapSqlParameterSource()
//                    .addValue("book_id", booksKeyHolder.getKeys().get("id"))
//                    .addValue("author_id", author.getId());
//            jdbc.update(
//                    INSERT_BOOK_TO_REFERENCE,
//                    authorsParameterSource,
//                    authorsKeyHolder
//            );
//        }
//    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

//    @Override
//    public Optional<Book> findById(int id) {
//        List<Book> books = jdbc.query(
//                SELECT_BOOK_BY_ID,
//                Collections.singletonMap("book_id", id),
//                new ListResultSetExtractor()
//        );
//        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
//    }
//
//    @Override
//    public void remove(Book book) throws DataAccessException {
//        jdbc.update(
//                DELETE_BOOK_FROM_REFERENCE,
//                Collections.singletonMap("book_id", book.getId())
//        );
//
//        jdbc.update(
//                DELETE_BOOK_BY_ID,
//                Collections.singletonMap("book_id", book.getId())
//        );
//    }
//
//    @Override
//    public int count() {
//        return jdbc.queryForObject(
//                COUNT_BOOKS,
//                Collections.EMPTY_MAP,
//                Integer.class
//        );
//    }

}