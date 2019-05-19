DROP TABLE IF EXISTS AUTHORS;


CREATE TABLE authors (
  id   SERIAL NOT NULL ,
  author_name varchar(255),

  primary key (id)
);