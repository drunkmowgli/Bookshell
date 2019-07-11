package org.asm.labs.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String description;

    public Comment() {}

    public Comment(String description) {
        this.description = description;
    }

    public Comment(String id, String description) {
        this.id = id;
        this.description = description;
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
                '}';
    }
}
