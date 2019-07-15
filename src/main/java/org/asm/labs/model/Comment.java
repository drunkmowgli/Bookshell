package org.asm.labs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String description;

    @DBRef
    private Book book;

    public Comment() {
    }

    public Comment(String description) {
        this.description = description;
    }

    public Comment(String description, Book book) {
        this.description = description;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", book=" + book +
                '}';
    }
}
