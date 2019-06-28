--Authors table contents
INSERT INTO author (author_name) VALUES ('Stan Lee');
INSERT INTO author (author_name) VALUES ('Jack Kirby');
INSERT INTO author (author_name) VALUES ('Chris Claremont');
INSERT INTO author (author_name) VALUES ('Stephen King');


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
insert into BOOK (title, genre_id) values ('Captain America Comics (1941) #3', 1);
insert into BOOK (title, genre_id) values ('Adventure Comics (Sandman) #72', 1);
insert into book (title, genre_id) values ('Dark Horse Comics #1', 1);
insert into book (title, genre_id) values ('The Shining', 13);

--Reference table contents
insert into BOOK_AUTHORS (book_id, author_id) values (1, 1);
insert into BOOK_AUTHORS (book_id, author_id) values (2, 2);
insert into BOOK_AUTHORS (book_id, author_id) values (3, 3);
insert into BOOK_AUTHORS (book_id, author_id) values (3, 1);
insert into BOOK_AUTHORS (book_id, author_id) values (4, 4);