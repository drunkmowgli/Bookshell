package org.asm.labs.entity;

import java.util.List;

public class Book {

    private final int id;

    private final String title;

    private final List<Author> authors;

    private final Genre genre;

    public Book(int id, String title, List<Author> authors, Genre genre) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }

    public Book(String title, List<Author> authors, Genre genre) {
        this.id = -1;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", genre=" + genre +
                '}';
    }
}
