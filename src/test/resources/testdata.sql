INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;

insert into books (title, description) values ('Spider-Man #1', 'First Comics');
insert into books (title, description) values ('Hulk #2', 'Second Comics');
