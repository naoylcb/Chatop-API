# Chatop API

This is the backend API of the Chatop application which handles all data process.

## Required

Make sure you have JDK (Java 17), Maven and MySQL installed on your computer.

## Launch the api

Git clone:

> git clone https://github.com/naoylcb/Chatop-API.git

Go inside folder:

> cd Chatop-API

Install dependencies:

> mvn clean install

Launch the api:

> mvn spring-boot:run

## Configuration

In "application.properties", there are a few environment variables you need to define for the project to work :
- CHATOP_SERVER_PORT : Server port to access the api
- CHATOP_DB_PORT : Database port
- CHATOP_DB_NAME : Database name
- CHATOP_DB_USER : Database user
- CHATOP_DB_PASSWORD : Database password
- CHATOP_JWT_SECRET_KEY : Secret key to sign and verify the JWT tokens
- CHATOP_IMAGES_FOLDER : Folder to which images will be transferred
- CHATOP_IMAGES_BASE_URL : Base images url preceding the file name to obtain an image via api

## Database

In MySQL, create a database and use a SQL script for creating the schema (present in the frontend project : ressources/sql/script.sql).

This is a cheatsheet to help you with mysql : https://buzut.net/maitrisez-mysql-en-cli/

## Swagger

Once api is started, you can access the swagger to get info for all endpoints :

> http://localhost:{CHATOP_SERVER_PORT}/swagger-ui/index.html#/