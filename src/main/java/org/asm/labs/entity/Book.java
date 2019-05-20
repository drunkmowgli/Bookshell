package org.asm.labs.entity;

public class Book {

    private final int id;

    private final String title;

    private final Author author;
    
    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.author = null;
    }
    
    public Book(String title, Author author) {
        this.id = -1;
        this.title = title;
        this.author = author;
    }

    public Book(Book book, Author author) {
        this.id = book.id;
        this.title = book.title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
