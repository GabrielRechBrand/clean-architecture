
# Clean Architecture Task Manager

A simple task manager API built with Kotlin and Spring Boot following the Clean Architecture principles.

---

## What is Clean Architecture?

Clean Architecture is a software design philosophy that emphasizes separation of concerns and independence of frameworks, UI, database, and external agencies. It structures code in layers where the core business logic (use cases and domain models) remains isolated from infrastructure and delivery mechanisms, improving maintainability, testability, and flexibility over time.

![Clean Architecture Diagram](https://programmingideaswithjake.wordpress.com/wp-content/uploads/2022/12/untitled.png)

---

## Features

- Create, read, update, and delete tasks
- Uses PostgreSQL for persistence (configurable for localhost or cloud)
- Layered architecture with clear separation of concerns
- Integration with Swagger for API documentation
- Unit tests for core application logic and REST controller

---

## Technologies Used

- Kotlin
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger (Springdoc OpenAPI)
- Mockk (for testing)
- JUnit 5

---

## Getting Started

### Prerequisites

- Java 17 or higher
- PostgreSQL (running locally or in the cloud)
- Gradle or Maven (depending on your build setup)

### Environment Variables and Defaults

The application uses environment variables to configure the database connection. Defaults are provided for local development:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:clean-architecture}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Running Locally

1. Make sure PostgreSQL is running locally and accessible with the default credentials or set your environment variables accordingly.
2. Build the project:
   ```bash
   ./gradlew build
   ```
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```
4. Access the API at `http://localhost:8080`

### API Documentation (Swagger)

Once the application is running, access the interactive API docs at:

```
http://localhost:8080/swagger-ui.html
```

---

## Project Structure

- **domain**: Core business entities and interfaces (e.g., `Task`, `TaskRepository`)
- **application**: Use cases implementing business logic
- **infrastructure**: Persistence adapters and database entities
- **interface**: REST controllers and Spring configuration

---

## Testing

The project includes unit tests for use cases and controllers using Mockk and JUnit 5.

To run tests:

```bash
./gradlew test
```
