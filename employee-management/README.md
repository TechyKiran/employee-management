# Employee Management System

Spring Boot CRUD project with REST Web, JPA/Hibernate, MySQL, Spring Security + JWT, Redis Cache, DTO validation and global exception handling.

## Requirements
- Java 17+
- Maven 3.9+
- MySQL
- Redis

## Run
1. Start MySQL and Redis.
2. Check `application.properties` credentials.
3. Run: `mvn spring-boot:run`

## Authentication
POST `/api/auth/register`
```json
{"username":"admin","password":"password123"}
```
Registration creates USER. To create an ADMIN, change the user's role in MySQL:
`UPDATE users SET role='ADMIN' WHERE username='admin';`

POST `/api/auth/login`
```json
{"username":"admin","password":"password123"}
```
Use returned JWT as:
`Authorization: Bearer <token>`

## Employee APIs
- POST `/api/employees` ADMIN
- GET `/api/employees` USER/ADMIN
- GET `/api/employees/{id}` USER/ADMIN
- PUT `/api/employees/{id}` ADMIN
- DELETE `/api/employees/{id}` ADMIN

Example:
```json
{"name":"Kiran","email":"kiran@example.com","department":"IT","salary":65000}
```
