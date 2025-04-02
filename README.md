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

- **Backend:** Java Spring Boot
- **Frontend:** HTML, CSS (Bootstrap), JavaScript (jQuery & AJAX)
- **Database:** PostgreSQL

## Requirements

Before running the application, ensure you have the following installed:

- Java (JDK 17 or later)
- PostgreSQL
- Maven

## Setup

### 1. Install PostgreSQL

#### Windows

1. Download PostgreSQL from [official website](https://www.postgresql.org/download/windows/).
2. Run the installer and follow the setup instructions.
3. During installation, set up a username and password for PostgreSQL.
4. Ensure that **pgAdmin 4** is installed for database management.
5. After installation, start the PostgreSQL service from **pgAdmin 4** or **Windows Services**.

#### macOS

1. Install PostgreSQL using Homebrew:
   ```sh
   brew install postgresql
   ```
2. Start PostgreSQL service:
   ```sh
   brew services start postgresql
   ```
3. Verify installation:
   ```sh
   psql --version
   ```
4. Access PostgreSQL shell:
   ```sh
   psql postgres
   ```
5. (Optional) Set up a password for your PostgreSQL user:
   ```sql
   ALTER USER postgres PASSWORD 'your_password';
   ```

### 2. Configure the Database

1. Open **pgAdmin 4** or use the terminal (macOS/Linux).
2. Create a new database:
   ```sql
   CREATE DATABASE todo_db;
   ```
3. Restore the database from the SQL file located at `todolist/scripts/todo.sql`.
4. Update `application.properties` in `src/main/resources/` with your PostgreSQL credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3. Install Dependencies

Navigate to the project root directory and install dependencies using Maven:

```sh
mvn clean install
```

### 4. Run the Application

```sh
mvn spring-boot:run
```

Once the application starts, it will be accessible at: [http://localhost:8080]

### 5. Running Tests

To run unit tests, execute:

```sh
mvn test
```

## Assumptions

- The user has PostgreSQL running locally.
- Basic front-end validation is handled by Bootstrap.

## Troubleshooting

- If PostgreSQL fails to connect, verify that the service is running and credentials are correct.
- If a port conflict occurs, change the default PostgreSQL port in `application.properties`.
- Ensure Maven is installed correctly by checking:
  ```sh
  mvn -version
  ```
