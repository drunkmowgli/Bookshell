[![Build Status](https://travis-ci.com/drunkmowgli/Bookshell.svg?branch=master)](https://travis-ci.com/drunkmowgli/Bookshell)

# OTUS Homework 5

Создать приложение хранящее информацию о книгах в библиотеке
Использовать Spring JDBC и реляционную базу.

Опционально использовать настоящую реляционную БД, но можно использовать H2.

Предусмотреть таблицы авторов, книг и жанров.

Интерфейс на Spring Shell

Покрыть тестами, насколько это возможно.

#### For develop:
`docker run --name postgres-develop -p 6543:5432 -e POSTGRES_USER=test_user -e POSTGRES_PASSWORD=test_password -d postgres`

#### For production:
`docker run --name postgres -p 5432:5432 -e POSTGRES_USER=prod_user -e POSTGRES_PASSWORD=prod_password -d postgres`

## Done:
    Implemented basic functionality
