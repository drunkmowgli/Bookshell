--Authors table contents
INSERT INTO authors (author_name) VALUES ('Stan Lee') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Jack Kirby') ON CONFLICT DO NOTHING;

insert into BOOKS (title) values ('Captain America Comics (1941) #3');
insert into BOOKS (title) values ('Adventure Comics (Sandman) #72');
