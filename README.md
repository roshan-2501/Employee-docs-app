# 📁 Employee Document Management App

This is a Spring Boot application for managing employee details and their documents (like resumes, certificates, etc.). It allows you to:

- Upload documents for a specific employee
- Download documents by ID
- View all employees and their associated documents

## 📦 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- REST APIs
- Postman (for testing)

## 🛠 Database Configuration (PostgreSQL)

Make sure you have PostgreSQL running locally.

Update your `application.properties` or `application.yml`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
