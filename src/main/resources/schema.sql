DROP TABLE IF EXISTS AUTHOR cascade;
DROP TABLE IF EXISTS BOOK cascade;
DROP TABLE IF EXISTS GENRE cascade;
DROP TABLE IF EXISTS BOOK_AUTHORS cascade;
DROP TABLE IF EXISTS COMMENT cascade;


CREATE TABLE AUTHOR (
    id   serial NOT NULL ,
    name varchar(255) unique ,

    primary key (id)
);


CREATE TABLE GENRE (
    id  serial NOT NULL ,
    genre_name varchar(255) unique ,

    primary key (id)
);


CREATE TABLE BOOK (
    id  serial NOT NULL ,
    title varchar(255) unique ,
    genre_id integer references genre (id) ,

    primary key (id)
);

CREATE TABLE BOOK_AUTHORS (
    id  serial NOT NULL ,
    book_id integer references book (id) NOT NULL ,
    author_id integer references author (id) NOT NULL ,

    primary key (id)
);

CREATE TABLE COMMENT (
    id serial NOT NULL,
    comment_description varchar(255) NOT NULL,
    book_id integer references book (id) NOT NULL ,

    primary key (id)

)