package org.asm.labs.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment_description")
    private String commentDescription;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment() {}

    public Comment(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public Comment(int id, String commentDescription) {
        this.id = id;
        this.commentDescription = commentDescription;
    }

    public Comment(String commentDescription, Book book) {
        this.commentDescription = commentDescription;
        this.book = book;
    }

    public Comment(int id, String commentDescription, Book book) {
        this.id = id;
        this.commentDescription = commentDescription;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public int getId() {
        return id;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentDescription='" + commentDescription + '\'' +
                '}';
    }
}
