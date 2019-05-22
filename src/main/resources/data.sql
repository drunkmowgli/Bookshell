--Authors table contents
INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;

--Genres table contents
insert into genres (genre) values ('Comics');
insert into genres (genre) values ('Horror');

--Books table contents
insert into BOOKS (title) values ('Captain America Comics (1941) #3', 1, 1);
insert into BOOKS (title) values ('Adventure Comics (Sandman) #72', 1, 1);
