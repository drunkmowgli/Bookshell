package org.asm.labs.entity;

public class Book {

    private final int id;

    private final String title;

    private final String description;

    private final Author author;
    
    public Book(int id, String title, String decription) {
        this.id = id;
        this.title = title;
        this.description = decription;
        this.author = null;
    }
    
    public Book(String title, String decription, Author author) {
        this.id = -1;
        this.title = title;
        this.description = decription;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                '}';
    }
}
