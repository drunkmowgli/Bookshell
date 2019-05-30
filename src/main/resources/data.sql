--Authors table contents
INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Chris Claremont') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Stephen King') ON CONFLICT DO NOTHING;


--Genres table contents
insert into genres (genre) values ('Comics');
insert into genres (genre) values ('Action and Adventure');
insert into genres (genre) values ('Anthology');
insert into genres (genre) values ('Classic');
insert into genres (genre) values ('Comic and Graphic Novel');
insert into genres (genre) values ('Crime and Detective');
insert into genres (genre) values ('Drama');
insert into genres (genre) values ('Fable');
insert into genres (genre) values ('Fairy Tale');
insert into genres (genre) values ('Fan-Fiction');
insert into genres (genre) values ('Fantasy');
insert into genres (genre) values ('Historical Fiction');
insert into genres (genre) values ('Horror');
insert into genres (genre) values ('Humor');
insert into genres (genre) values ('Legend');
insert into genres (genre) values ('Magical Realism');
insert into genres (genre) values ('Mystery');
insert into genres (genre) values ('Mythology');
insert into genres (genre) values ('Realistic Fiction');
insert into genres (genre) values ('Romance');
insert into genres (genre) values ('Satire');
insert into genres (genre) values ('Science Fiction (Sci-Fi)');
insert into genres (genre) values ('Short Story');
insert into genres (genre) values ('Suspense/Thriller');
insert into genres (genre) values ('[Under Nonfiction Category]');
insert into genres (genre) values ('Biography/Autobiography');
insert into genres (genre) values ('Essay');
insert into genres (genre) values ('Memoir');
insert into genres (genre) values ('Narrative Nonfiction');
insert into genres (genre) values ('Periodicals');
insert into genres (genre) values ('Reference Books');
insert into genres (genre) values ('Self-help Book');
insert into genres (genre) values ('Speech');
insert into genres (genre) values ('Textbook');
insert into genres (genre) values ('Poetry (Can be both Fiction or Nonfiction)');

--Books table contents
insert into BOOKS (title, genre_id) values ('Captain America Comics (1941) #3', 1);
insert into BOOKS (title, genre_id) values ('Adventure Comics (Sandman) #72', 1);
insert into books (title, genre_id) values ('Dark Horse Comics #1', 1);
insert into books (title, genre_id) values ('The Shining', 13);

--Reference table contents
insert into reference (book_id, author_id) values (1, 1);
insert into reference (book_id, author_id) values (2, 2);
insert into reference (book_id, author_id) values (3, 3);
insert into reference (book_id, author_id) values (3, 1);
insert into reference (book_id, author_id) values (4, 4);