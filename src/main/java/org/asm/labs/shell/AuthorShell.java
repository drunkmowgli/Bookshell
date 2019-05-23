package org.asm.labs.shell;


import org.asm.labs.entity.Author;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorShell {

    private final AuthorService authorService;


    @Autowired
    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("add author")
    public void add_author(@ShellOption String name) {
        authorService.add(new Author(name));
    }

    @ShellMethod("get by name")
    public void get_author(@ShellOption String name) {
        System.out.println(authorService.getByName(name));
    }

    @ShellMethod("get by id")
    public void get_author_by_id(@ShellOption int id) {
        System.out.println(authorService.getById(id));
    }

    @ShellMethod("get all")
    public void get_all_authors() {
        authorService.getAll().forEach(System.out::println);
    }

    @ShellMethod("remove author")
    public void remove_author(@ShellOption String authorName) {
        Author author = authorService.getByName(authorName);
        authorService.remove(author);
    }

    @ShellMethod("count")
    public void count_authors() {
        System.out.println(authorService.count());
    }
}
