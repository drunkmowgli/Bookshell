package org.asm.labs.dao.impl;

class SqlQueryTemplates {
    
    final static String INSERT_AUTHOR = "insert into authors (author_name) values (:authorName)";
    
    final static String SELECT_ALL_AUTHORS = "select * from authors";
    
    final static String SELECT_AUTHOR_BY_ID = SELECT_ALL_AUTHORS + " where id = :author_id";
    
    final static String DELETE_AUTHOR_FROM_REFERENCE = "delete from reference where (author_id) = :author_id";
    
    final static String DELETE_AUTHOR_BY_ID= "delete from authors where (id) = :author_id";
    
    final static String COUNT_AUTHORS = "select count(*) from authors";
    
    final static String INSERT_BOOK = "insert into books (title, genre_id) values (:book_title, :genre_id)";
    
    final static String INSERT_BOOK_TO_REFERENCE = "insert into reference (book_id, author_id) values (:book_id, :author_id)";
    
    private final static String ORDER_ALL_SELECT_BOOK = "order by b.id ASC," +
            "g.id ASC," +
            "a.id ASC";
    
    private final static String SELECT_ALL_BOOKS = "select * from books b inner join genres g on b.genre_id = g.id " +
                                            "inner join reference r on b.id = r.book_id " +
                                            "inner join authors a on r.author_id = a.id ";
    
    final static String SELECT_ALL_BOOK_BY_ORDER = SELECT_ALL_BOOKS + ORDER_ALL_SELECT_BOOK;
    
    
    final static String SELECT_BOOK_BY_ID = SELECT_ALL_BOOKS + " where b.id = :book_id " + ORDER_ALL_SELECT_BOOK;
    
    final static String DELETE_BOOK_BY_ID = "delete from books where id = :book_id";
    
    final static String DELETE_BOOK_FROM_REFERENCE = "delete from reference where book_id = :book_id";
    
    final static String COUNT_BOOKS = "select count(*) from books";
    
    final static String SELECT_ALL_GENRES = "select * from genres";
    
    final static String SELECT_GENRE_BY_ID = SELECT_ALL_GENRES + " where id = :genre_id";
    
    final static String COUNT_GENRES = "select count(*) from genres";
    
    private SqlQueryTemplates() {}
}
