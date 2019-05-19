package org.asm.labs.entities;

import java.util.List;

public class Book {

    private final int id;

    private final String title;

    private final String description;

    private final List<Author> authors;

    private final List<Genre> genres;

    public Book(int id, String title, String description, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
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

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
