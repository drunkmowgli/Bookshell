DROP TABLE IF EXISTS AUTHORS cascade;
DROP TABLE IF EXISTS BOOKS cascade;
DROP TABLE IF EXISTS GENRES cascade;


CREATE TABLE authors (
    id   serial NOT NULL ,
    author_name varchar(255),

    primary key (id)
);


CREATE TABLE genres (
    id  serial NOT NULL ,
    genre varchar(255) unique ,

    primary key (id)
);


CREATE TABLE books (
    id  serial NOT NULL ,
    author_id integer references authors (id) ,
    title varchar(255),
    genre_id integer references genres (id) ,

    primary key (id)
)