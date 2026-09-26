# Employee Management System

A secure **Employee Management REST API** built using **Spring Boot, Spring Security, JWT, Spring Data JPA, MySQL, and Redis**.

The application provides employee CRUD operations with **role-based authorization**, JWT-based authentication, DTO validation, global exception handling, and Redis caching.

---

## 🚀 Features

* User registration and login
* JWT-based authentication
* Role-based authorization using `USER` and `ADMIN`
* Employee CRUD operations
* Spring Data JPA and Hibernate
* MySQL database integration
* Redis caching
* DTO-based request and response handling
* Bean Validation
* Global exception handling
* BCrypt password encryption
* Stateless Spring Security configuration
* RESTful API architecture
* Maven-based project

---

## 🛠️ Technologies Used

| Technology        | Purpose                        |
| ----------------- | ------------------------------ |
| Java 17           | Programming Language           |
| Spring Boot 3.5.5 | Backend Framework              |
| Spring Web        | REST API Development           |
| Spring Security   | Authentication & Authorization |
| JWT               | Token-Based Authentication     |
| Spring Data JPA   | Database Access                |
| Hibernate         | ORM                            |
| MySQL             | Relational Database            |
| Redis             | Caching                        |
| Maven             | Dependency Management & Build  |
| Lombok            | Boilerplate Code Reduction     |
| Postman           | API Testing                    |

---

## 📁 Project Structure

```text
src
└── main
    ├── java
    │   └── com.example.employee
    │       ├── config
    │       │   ├── RedisConfig.java
    │       │   └── SecurityConfig.java
    │       │
    │       ├── controller
    │       │   ├── AuthController.java
    │       │   └── EmployeeController.java
    │       │
    │       ├── dto
    │       │   ├── EmployeeRequest.java
    │       │   ├── EmployeeResponse.java
    │       │   ├── LoginRequest.java
    │       │   ├── LoginResponse.java
    │       │   └── RegisterRequest.java
    │       │
    │       ├── entity
    │       │   ├── AppUser.java
    │       │   ├── Employee.java
    │       │   └── Role.java
    │       │
    │       ├── exception
    │       │   ├── EmailAlreadyExistsException.java
    │       │   ├── EmployeeNotFoundException.java
    │       │   └── GlobalExceptionHandler.java
    │       │
    │       ├── repository
    │       │   ├── EmployeeRepository.java
    │       │   └── UserRepository.java
    │       │
    │       ├── security
    │       │   ├── CustomUserDetailsService.java
    │       │   ├── JwtAuthenticationFilter.java
    │       │   └── JwtService.java
    │       │
    │       └── service
    │           ├── AuthService.java
    │           ├── EmployeeService.java
    │           └── EmployeeServiceImpl.java
    │
    └── resources
        └── application.properties
```

---

## 🔐 Authentication & Authorization

The application uses **JWT authentication** with Spring Security.

### Roles

There are two roles:

* `USER`
* `ADMIN`

Newly registered users are assigned the `USER` role by default.

### Authorization Rules

| Endpoint                     | USER | ADMIN |
| ---------------------------- | :--: | :---: |
| `POST /api/auth/register`    |   ✅  |   ✅   |
| `POST /api/auth/login`       |   ✅  |   ✅   |
| `GET /api/employees`         |   ✅  |   ✅   |
| `GET /api/employees/{id}`    |   ✅  |   ✅   |
| `POST /api/employees`        |   ❌  |   ✅   |
| `PUT /api/employees/{id}`    |   ❌  |   ✅   |
| `DELETE /api/employees/{id}` |   ❌  |   ✅   |

Employee `GET` operations are available to both authenticated users and administrators, while employee creation, modification, and deletion require the `ADMIN` role.

---

## 🗄️ Database Configuration

The project uses MySQL.

Default configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
```

Hibernate automatically updates the database schema:

```properties
spring.jpa.hibernate.ddl-auto=update
```

> Change the database username and password according to your local MySQL configuration.

---

## ⚡ Redis Caching

Redis is used to cache employee details and reduce repeated database queries.

Redis configuration:

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

The project uses Spring Cache annotations:

```java
@Cacheable(value = "employees", key = "#id")
```

Employee cache entries are removed when an employee is updated or deleted:

```java
@CacheEvict(value = "employees", key = "#id")
```

The Redis cache is configured with a **10-minute TTL**.

---

## 🔑 JWT Configuration

JWT settings are configured in `application.properties`:

```properties
app.jwt.secret=change-this-secret-key-to-a-long-random-value-at-least-32-bytes
app.jwt.expiration=3600000
```

The default expiration is:

```text
3600000 ms = 1 hour
```

### ⚠️ Security Recommendation

For production, do not commit a real JWT secret or database password to GitHub.

Use environment variables or external configuration instead.

---

# ▶️ Getting Started

## Prerequisites

Make sure the following are installed:

* Java 17 or later
* Maven 3.9+
* MySQL 8+
* Redis
* Postman (optional, for API testing)

---

## 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/employee-management-spring-security.git
```

Navigate into the project:

```bash
cd employee-management-spring-security
```

---

## 2. Configure MySQL

Create a MySQL database if required:

```sql
CREATE DATABASE employee_db;
```

Update the credentials in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

---

## 3. Start Redis

Make sure Redis is running on:

```text
localhost:6379
```

---

## 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or build the project:

```bash
mvn clean package
```

Then run the generated JAR:

```bash
java -jar target/employee-management-0.0.1-SNAPSHOT.jar
```

The application runs on:

```text
http://localhost:8080
```

---

# 🔑 API Documentation

## Authentication APIs

### 1. Register User

**POST**

```text
/api/auth/register
```

Request:

```json
{
  "username": "admin",
  "password": "password123"
}
```

Response:

```text
User registered
```

A newly registered user receives the `USER` role by default.

---

### 2. Login

**POST**

```text
/api/auth/login
```

Request:

```json
{
  "username": "admin",
  "password": "password123"
}
```

The API returns a JWT token.

Example:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Use this token for protected endpoints:

```text
Authorization: Bearer <JWT_TOKEN>
```

---

# 👨‍💼 Employee APIs

## 1. Create Employee

**POST**

```text
/api/employees
```

**Role:** `ADMIN`

Request:

```json
{
  "name": "Kiran",
  "email": "kiran@example.com",
  "department": "IT",
  "salary": 65000
}
```

---

## 2. Get All Employees

**GET**

```text
/api/employees
```

**Role:** `USER` / `ADMIN`

---

## 3. Get Employee by ID

**GET**

```text
/api/employees/{id}
```

Example:

```text
/api/employees/1
```

**Role:** `USER` / `ADMIN`

---

## 4. Update Employee

**PUT**

```text
/api/employees/{id}
```

**Role:** `ADMIN`

Example:

```text
/api/employees/1
```

Request:

```json
{
  "name": "Kiran Kumar",
  "email": "kiran@example.com",
  "department": "Development",
  "salary": 70000
}
```

---

## 5. Delete Employee

**DELETE**

```text
/api/employees/{id}
```

**Role:** `ADMIN`

Example:

```text
/api/employees/1
```

Successful deletion returns:

```text
HTTP 204 No Content
```

---

# 🛡️ Security Flow

```text
                ┌─────────────────┐
                │     Client      │
                │    / Postman    │
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │  Login / Register│
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │   JWT Token     │
                └────────┬────────┘
                         │
              Authorization: Bearer
                         │
                         ▼
                ┌─────────────────┐
                │ JWT Auth Filter │
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │ Spring Security │
                │ Role Validation │
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │ Employee API    │
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │ Service / Cache │
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │ MySQL Database  │
                └─────────────────┘
```

---

# 🧪 Testing with Postman

Recommended testing sequence:

### Step 1

Register a user:

```text
POST /api/auth/register
```

### Step 2

Login:

```text
POST /api/auth/login
```

### Step 3

Copy the JWT token returned by the login API.

### Step 4

In Postman, select:

```text
Authorization → Bearer Token
```

Paste the JWT token.

### Step 5

Test employee APIs according to the user's role.

---

# 📌 Exception Handling

The application includes centralized exception handling through:

```text
GlobalExceptionHandler
```

It handles scenarios such as:

* Employee not found
* Duplicate email
* Validation errors
* Invalid requests

This provides consistent API error responses instead of exposing application-level exceptions directly.

---

# 🏗️ Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Additional components:

```text
Spring Security
       ↓
JWT Authentication Filter
       ↓
Role-Based Authorization

Redis
  ↓
Caching Layer
```

---

# 📈 Key Learning Outcomes

This project demonstrates practical implementation of:

* Spring Boot REST API development
* Spring Security configuration
* JWT authentication
* Role-based authorization
* Password encryption using BCrypt
* Spring Data JPA
* Hibernate ORM
* MySQL database integration
* Redis caching
* DTO pattern
* Bean validation
* Global exception handling
* RESTful API design
* Maven project management

---

# 🔮 Future Enhancements

Possible future improvements:

* Swagger / OpenAPI documentation
* Refresh token implementation
* Docker and Docker Compose support
* Pagination and sorting
* Search and filtering
* Unit and integration testing
* CI/CD pipeline
* Production environment configuration using environment variables
* API monitoring and logging

---

# 👨‍💻 Author

**Kiran K**

Java Full Stack Developer

Skills demonstrated in this project:

`Java` • `Spring Boot` • `Spring Security` • `JWT` • `Spring Data JPA` • `Hibernate` • `MySQL` • `Redis` • `REST API` • `Maven`

---

## ⭐ If you found this project useful

Feel free to explore the source code and use the project for learning and development.
