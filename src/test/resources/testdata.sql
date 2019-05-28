INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Chris Claremont') ON CONFLICT DO NOTHING;

insert into genres (genre) values ('Comics');
insert into genres (genre) values ('Horror');

insert into books (title, genre_id) values ('Spider-Man #1', 1);
insert into books (title, genre_id) values ('Dark Horse Comics #1', 1);
insert into books (title, genre_id) values ('Hulk #2', 1);
insert into books (title, genre_id) values ('Dark Horse Comics #2', 1);
insert into books (title, genre_id) values ('Spider-Man #2', 1);

insert into reference (book_id, author_id) values (1, 1);
insert into reference (book_id, author_id) values (1, 2);
insert into reference (book_id, author_id) values (2, 3);
insert into reference (book_id, author_id) values (3, 2);
insert into reference (book_id, author_id) values (4, 3);
insert into reference (book_id, author_id) values (4, 2);
insert into reference (book_id, author_id) values (5, 1);
