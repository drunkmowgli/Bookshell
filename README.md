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

Использовать MogoDB и spring-data для хранения информации о книгах
Тесты можно реализовать с помощью spring-boot-strter-embedded-mongodb

# OTUS Homework 9

Использовать MogoDB и spring-data для хранения информации о книгах
Тесты можно реализовать с помощью spring-boot-strter-embedded-mongodb

# OTUS Homework 10

Переписать приложение с использованием AJAX и REST-контроллеров
Переписать приложение с классических View на AJAX архитектуру и REST-контроллеры.

Опционально: Сделать SPA приложение на любом из Web-фреймоврков

#### For develop (branch HW05 only):
`docker run --name postgres-develop -p 6543:5432 -e POSTGRES_USER=test_user -e POSTGRES_PASSWORD=test_password -d postgres`

#### For production:
`docker run --name postgres -p 5432:5432 -e POSTGRES_USER=prod_user -e POSTGRES_PASSWORD=prod_password -d postgres`

# Deploy:

`1. mvn clean install`

`2. java -jar backend/target/backend-1.0-SNAPSHOT.jar`

`3. Go to http://localhost:9999`

# OTUS Homework 11

Использовать WebFlux
Вместо классического потока и embedded Web-сервера использовать WebFlux.

PR этой Домашней Работы пойдет на отдельную ветку `master-webflux`

# OTUS Homework 12

В CRUD Web-приложение добавить механизм аутентификации
В существующее CRUD-приложение добавить мехнизм Form-based аутентификации.

UsersServices реализовать самостоятельно.

**Внимание!** Задание выполняется на основе нереактивного приложения Spring MVC!

#### For production:
`docker run --restart=always --name mongo -p 8081:8081 -p 27017:27017 -d mongo`

# Deploy

`1. mvn clean install`

`2. java -jar backend/target/backend-*.jar`

`Go to http://localhost:9999`