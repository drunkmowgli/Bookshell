--Authors table contents
INSERT INTO author (name) VALUES ('Stan Lee');
INSERT INTO author (name) VALUES ('Jack Kirby');
INSERT INTO author (name) VALUES ('Chris Claremont');
INSERT INTO author (name) VALUES ('Stephen King');


--Genres table contents
insert into genre (genre_name) values ('Comics');
insert into genre (genre_name) values ('Action and Adventure');
insert into genre (genre_name) values ('Anthology');
insert into genre (genre_name) values ('Classic');
insert into genre (genre_name) values ('Comic and Graphic Novel');
insert into genre (genre_name) values ('Crime and Detective');
insert into genre (genre_name) values ('Drama');
insert into genre (genre_name) values ('Fable');
insert into genre (genre_name) values ('Fairy Tale');
insert into genre (genre_name) values ('Fan-Fiction');
insert into genre (genre_name) values ('Fantasy');
insert into genre (genre_name) values ('Historical Fiction');
insert into genre (genre_name) values ('Horror');
insert into genre (genre_name) values ('Humor');
insert into genre (genre_name) values ('Legend');
insert into genre (genre_name) values ('Magical Realism');
insert into genre (genre_name) values ('Mystery');
insert into genre (genre_name) values ('Mythology');
insert into genre (genre_name) values ('Realistic Fiction');
insert into genre (genre_name) values ('Romance');
insert into genre (genre_name) values ('Satire');
insert into genre (genre_name) values ('Science Fiction (Sci-Fi)');
insert into genre (genre_name) values ('Short Story');
insert into genre (genre_name) values ('Suspense/Thriller');
insert into genre (genre_name) values ('[Under Nonfiction Category]');
insert into genre (genre_name) values ('Biography/Autobiography');
insert into genre (genre_name) values ('Essay');
insert into genre (genre_name) values ('Memoir');
insert into genre (genre_name) values ('Narrative Nonfiction');
insert into genre (genre_name) values ('Periodicals');
insert into genre (genre_name) values ('Reference Books');
insert into genre (genre_name) values ('Self-help Book');
insert into genre (genre_name) values ('Speech');
insert into genre (genre_name) values ('Textbook');
insert into genre (genre_name) values ('Poetry (Can be both Fiction or Nonfiction)');

--Books table contents
insert into BOOK (title, genre_id) VALUES ('Captain America Comics (1941) #3', 1);
insert into BOOK (title, genre_id) VALUES ('Adventure Comics (Sandman) #72', 1);
insert into BOOK (title, genre_id) VALUES ('Dark Horse Comics #1', 1);
insert into BOOK (title, genre_id) VALUES ('The Shining', 13);

--Reference book_authors table contents
insert into BOOK_AUTHORS (book_id, author_id) VALUES (1, 1);
insert into BOOK_AUTHORS (book_id, author_id) VALUES (2, 2);
insert into BOOK_AUTHORS (book_id, author_id) VALUES (3, 3);
insert into BOOK_AUTHORS (book_id, author_id) VALUES (3, 1);
insert into BOOK_AUTHORS (book_id, author_id) VALUES (4, 4);

--Comments table contents
INSERT INTO comment (comment_description, book_id) VALUES ('Excellent book', 1);
INSERT INTO comment (comment_description, book_id) VALUES ('Very good book', 1);
INSERT INTO comment (comment_description, book_id) VALUES ('Good book', 3);
INSERT INTO comment (comment_description, book_id) VALUES ('Normal book', 4);
INSERT INTO comment (comment_description, book_id) VALUES ('Bad book',4);