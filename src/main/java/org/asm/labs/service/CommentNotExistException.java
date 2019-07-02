package org.asm.labs.service;

public class CommentNotExistException extends Exception {
    public CommentNotExistException() {
        super("Comment not exist.");
    }
}
