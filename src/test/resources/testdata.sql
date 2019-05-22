INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;

insert into genres (genre) values ('Comics');
insert into genres (genre) values ('Horror');

insert into books (title, author_id, genre_id) values ('Spider-Man #1', 1, 1);
insert into books (title, author_id, genre_id) values ('Hulk #2', 2, 2);