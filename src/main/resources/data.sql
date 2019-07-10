--Authors table contents
INSERT INTO AUTHOR (name) VALUES ('Stan Lee');
INSERT INTO AUTHOR (name) VALUES ('Jack Kirby');
INSERT INTO AUTHOR (name) VALUES ('Chris Claremont');
INSERT INTO AUTHOR (name) VALUES ('Stephen King');


--Genres table contents
insert into GENRE (genre_name) values ('Comics');
insert into GENRE (genre_name) values ('Action and Adventure');
insert into GENRE (genre_name) values ('Anthology');
insert into GENRE (genre_name) values ('Classic');
insert into GENRE (genre_name) values ('Comic and Graphic Novel');
insert into GENRE (genre_name) values ('Crime and Detective');
insert into GENRE (genre_name) values ('Drama');
insert into GENRE (genre_name) values ('Fable');
insert into GENRE (genre_name) values ('Fairy Tale');
insert into GENRE (genre_name) values ('Fan-Fiction');
insert into GENRE (genre_name) values ('Fantasy');
insert into GENRE (genre_name) values ('Historical Fiction');
insert into GENRE (genre_name) values ('Horror');
insert into GENRE (genre_name) values ('Humor');
insert into GENRE (genre_name) values ('Legend');
insert into GENRE (genre_name) values ('Magical Realism');
insert into GENRE (genre_name) values ('Mystery');
insert into GENRE (genre_name) values ('Mythology');
insert into GENRE (genre_name) values ('Realistic Fiction');
insert into GENRE (genre_name) values ('Romance');
insert into GENRE (genre_name) values ('Satire');
insert into GENRE (genre_name) values ('Science Fiction (Sci-Fi)');
insert into GENRE (genre_name) values ('Short Story');
insert into GENRE (genre_name) values ('Suspense/Thriller');
insert into GENRE (genre_name) values ('[Under Nonfiction Category]');
insert into GENRE (genre_name) values ('Biography/Autobiography');
insert into GENRE (genre_name) values ('Essay');
insert into GENRE (genre_name) values ('Memoir');
insert into GENRE (genre_name) values ('Narrative Nonfiction');
insert into GENRE (genre_name) values ('Periodicals');
insert into GENRE (genre_name) values ('Reference Books');
insert into GENRE (genre_name) values ('Self-help Book');
insert into GENRE (genre_name) values ('Speech');
insert into GENRE (genre_name) values ('Textbook');
insert into GENRE (genre_name) values ('Poetry (Can be both Fiction or Nonfiction)');

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
INSERT INTO COMMENT (comment_description, book_id) VALUES ('Excellent book', 1);
INSERT INTO COMMENT (comment_description, book_id) VALUES ('Very good book', 1);
INSERT INTO COMMENT (comment_description, book_id) VALUES ('Good book', 3);
INSERT INTO COMMENT (comment_description, book_id) VALUES ('Normal book', 4);
INSERT INTO COMMENT (comment_description, book_id) VALUES ('Bad book', 4);