package org.asm.labs.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment_description")
    private String commentDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    public Comment() {}

    public Comment(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public Comment(long id, String commentDescription) {
        this.id = id;
        this.commentDescription = commentDescription;
    }

    public Comment(String commentDescription, Book book) {
        this.commentDescription = commentDescription;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public long getId() {
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
