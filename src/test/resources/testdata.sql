INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;

insert into genres (genre) values ('Comics');
insert into genres (genre) values ('Horror');

insert into books (title, genre_id) values ('Spider-Man #1', 1);
insert into books (title, genre_id) values ('Hulk #2', 1);

insert into reference (book_id, author_id) values (1, 1);
insert into reference (book_id, author_id) values (2, 2);
