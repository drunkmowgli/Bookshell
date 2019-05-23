--Authors table contents
INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;

--Genres table contents
insert into genres (genre) values ('Comics');
insert into genres (genre) values ('Horror');

--Books table contents
insert into BOOKS (title, genre_id) values ('Captain America Comics (1941) #3', 1);
insert into BOOKS (title, genre_id) values ('Adventure Comics (Sandman) #72', 1);

--Reference table contents
insert into reference (book_id, author_id) values (1, 1);
insert into reference (book_id, author_id) values (2, 2);