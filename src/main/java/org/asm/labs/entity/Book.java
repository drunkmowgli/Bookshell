package org.asm.labs.entity;

import java.util.List;

public class Book {

    private final int id;

    private final String title;

    private final String description;

    private final List<Author> authors;
    
    
    public Book(String title, String description) {
        this.id = -1;
        this.title = title;
        this.description = description;
        this.authors = null;
    }
    
    public Book(int id, String title, String decription) {
        this.id = id;
        this.title = title;
        this.description = decription;
        this.authors = null;
    }
    
    public Book(Book book, List<Author> authors) {
        this.id = book.id;
        this.title = book.title;
        this.description = book.description;
        this.authors = authors;
    }
    
    public Book(String title, String decription, List<Author> authors) {
        this.id = -1;
        this.title = title;
        this.description = decription;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Author> getAuthors() {
        return authors;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                '}';
    }
}
