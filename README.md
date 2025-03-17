# To-Do List App

## Overview

This is a simple To-Do List application built using Java Spring Boot for the backend, Bootstrap (HTML & CSS) for the frontend, and PostgreSQL as the database. The app supports full CRUD (Create, Read, Update, Delete) operations and follows best practices for separation of concerns.

## Features

- View all tasks
- Add a new task
- Edit an existing task
- Delete a single task
- Delete all tasks
- Mark tasks as completed

## Tech Stack

- Backend: Java Spring Boot
- Frontend: HTML, CSS (Bootstrap), JavaScript (jQuery & AJAX)
- Database: PostgreSQL

## Requirements

Before running the application, ensure you have the following installed:

- Java (JDK 17 or later)
- PostgreSQL
- Maven

## Setup

### 1. Clone the Repository

Since the project should not be uploaded to GitHub, extract the compressed folder with Git history to your local machine.

### 2.Configure the Database

1. Open **pgAdmin 4**.
2. Create a new database (e.g., `todo_db`).
3. Restore the database from the SQL file located at `todolist/scripts/todo.sql`.
4. Update `application.properties` in `src/main/resources/` with your PostgreSQL credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3.Install Dependencies

Navigate to the project root directory and install dependencies using Maven:

```sh
 mvn clean install
```

### 4.Run the Application

```sh
 mvn spring-boot:run
```

Once the application starts, it will be accessible at: http://localhost:8080

### 5.Running Tests

To run unit tests, execute:

```sh
  mvn test
```

## Assumptions

- The user has PostgreSQL running locally.
- Basic front-end validation is handled by Bootstrap.
