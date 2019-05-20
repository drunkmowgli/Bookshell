DROP TABLE IF EXISTS AUTHORS cascade;
DROP TABLE IF EXISTS BOOKS cascade;


CREATE TABLE authors (
    id   SERIAL NOT NULL ,
    author_name varchar(255),

    primary key (id)
);

CREATE TABLE books (
    id  SERIAL NOT NULL ,
    author_id SERIAL references authors (id) ,
    title varchar(255),
    description varchar(255),

    primary key (id)
)