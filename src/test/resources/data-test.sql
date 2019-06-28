INSERT INTO author (author_name) VALUES ('Stan Lee');
INSERT INTO author (author_name) VALUES ('Jack Kirby');
INSERT INTO author (author_name) VALUES ('Chris Claremont');

INSERT INTO genre (genre_name) VALUES ('Comics');
INSERT INTO genre (genre_name) VALUES ('Horror');

--
INSERT INTO book (title, genre_id) VALUES ('Spider-Man #1', 1);
INSERT INTO book (title, genre_id) VALUES ('Dark Horse Comics #1', 2);
INSERT INTO book (title, genre_id) VALUES ('Hulk #2', 2);
INSERT INTO book (title, genre_id) VALUES ('Dark Horse Comics #2', 1);
INSERT INTO book (title, genre_id) VALUES ('Spider-Man #2', 1);
--
INSERT INTO book_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO book_authors (book_id, author_id) VALUES (1, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (2, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (3, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (4, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (4, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (5, 1);