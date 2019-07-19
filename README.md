[![Build Status](https://travis-ci.com/drunkmowgli/Bookshell.svg?branch=master)](https://travis-ci.com/drunkmowgli/Bookshell)
# OTUS Homework 5

Создать приложение хранящее информацию о книгах в библиотеке
Использовать Spring JDBC и реляционную базу.

Опционально использовать настоящую реляционную БД, но можно использовать H2.

Предусмотреть таблицы авторов, книг и жанров.

Интерфейс на Spring Shell

Покрыть тестами, насколько это возможно.

# OTUS Homework 6

Переписать приложение для хранения книг на ORM
Использовать JPA, Hibernate только в качестве JPA-провайдера.

Добавить комментарии к книгам, и высокоуровневые сервисы, оставляющие комментарии к книгам.

Покрыть DAO тестами используя H2 базу данных и соответствующий H2 Hibernate-диалект

# OTUS Homework 7

Билиотеку на Spring Data JPA
Реализовать весь функционал работы с БД в приложении книг с использованием spring-data-jpa репозиториев.

# OTUS Homework 8

Использовать MongoDB и spring-data для хранения информации о книгах
Тесты можно реализовать с помощью spring-boot-starter-embedded-mongodb

# OTUS Homework 9

CRUD приложение с Web UI и хранением данных в БД
Создайте приложение с хранением сущностей в БД (можно взять DAOs из прошлых занятий)

Использовать классический View, предусмотреть страницу отображения всех сущностей и создания/редактирования.

View на Thymeleaf, classic Controllers.

#### For develop (branch HW05 only):
`docker run --name postgres-develop -p 6543:5432 -e POSTGRES_USER=test_user -e POSTGRES_PASSWORD=test_password -d postgres`

#### For production:
`docker run --name postgres -p 5432:5432 -e POSTGRES_USER=prod_user -e POSTGRES_PASSWORD=prod_password -d postgres`

## Done:
    1. Implemented basic functionality
    2. Implemented JDBC to JPA with Comments
