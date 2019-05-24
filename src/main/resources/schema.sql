DROP TABLE IF EXISTS AUTHORS cascade;
DROP TABLE IF EXISTS BOOKS cascade;
DROP TABLE IF EXISTS GENRES cascade;
DROP TABLE IF EXISTS REFERENCE cascade;


CREATE TABLE authors (
    id   serial NOT NULL ,
    author_name varchar(255) unique ,

    primary key (id)
);


CREATE TABLE genres (
    id  serial NOT NULL ,
    genre varchar(255) unique ,

    primary key (id)
);


CREATE TABLE books (
    id  serial NOT NULL ,
    title varchar(255) unique ,
    genre_id integer references genres (id) ,

    primary key (id)
);

CREATE TABLE reference (
    id  serial NOT NULL ,
    book_id integer references books (id) ,
    author_id integer references authors (id),

    primary key (id)
)