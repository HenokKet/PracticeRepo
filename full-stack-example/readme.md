📚 Pet Management Application
This is a full-stack application for managing a pet registry, featuring a secure Spring Boot (Java) back-end with JDBC/MySQL for data persistence and a React front-end for the user interface.

🌟 Features
Secure API: All pet management endpoints require a valid JWT (JSON Web Token).

Authentication: User registration and login endpoints are available.

Data Security: User passwords are stored using MySQL's reversible AES-256 encryption (using the hardcoded secret "carrot").

Front-end: React components manage pet listing, creation, editing, and deletion, with global state for authentication.

🛠️ Setup and Prerequisites
Before running the application, ensure you have the following installed:

Java 17+ (Required for Spring Boot 3)

Maven (Used to build and manage the Java project)

Node.js & npm (Required for the React front-end)

MySQL Server (Version 8.0+ recommended)

1. ⚙️ Back-end Setup (Spring Boot / Java)
A. Database Configuration (MySQL)
You must create the pets database and the pet and user tables.

Create Database:

SQL

CREATE DATABASE pets;
USE pets;
Create Tables and Sample Data:

SQL

-- 1. Pet Table
create table pet (
    pet_id int primary key auto_increment,
    `name` varchar(100) not null,
    `type` varchar(50) not null
);

-- Insert Sample Pets
INSERT INTO pet (`name`, `type`) VALUES
('Meep', 'Mouse'),
('Gizmo', 'Cat'),
('Noodles', 'Dog');

-- 2. User Table (Includes AES Encryption)
create table user (
	user_id int primary key auto_increment,
    user_name varchar(100) not null,
    user_email varchar(254) not null,
    user_role varchar(10) not null,
    password_aes VARBINARY(256) not null
);

-- Insert Sample Users (Secret key is 'carrot')
INSERT INTO user (user_name, user_email, user_role, password_aes) VALUES
('test_user_1', 'user1@email.com', 'admin', AES_ENCRYPT('password123', 'carrot')),
('test_admin_1', 'admin@email.com', 'user', AES_ENCRYPT('securepass', 'carrot'));
B. Application Properties
Ensure your src/main/resources/application.properties file is correctly configured to connect to your local MySQL database:

Properties

# Database Connection Settings
spring.datasource.url=jdbc:mysql://localhost:3306/pets
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

# Set the driver class name
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Server Port (optional, defaults to 8080)
server.port=8080
C. Run the Back-end
Navigate to the root directory of your Spring Boot project (where pom.xml is located) and run the application using Maven:

Bash

# Clean, compile, and run the Spring Boot application
mvn spring-boot:run
The API will be accessible at http://localhost:8080.

2. 💻 Front-end Setup (React)
A. Install Dependencies
Navigate to your React project directory (where package.json is located) and install the necessary Node modules:

Bash

npm install
B. Configure API Base URL
Ensure your React application knows where to find the back-end API. In your src/lib/api.js (or similar configuration file), verify the base URL:

JavaScript

// src/lib/api.js (Example)
export const API_BASE = 'http://localhost:8080';
C. Run the Front-end
Start the React development server:

Bash

npm run dev
The front-end application will typically open in your browser at http://localhost:5173 (or port 3000, depending on your setup).

🔐 Security & Testing
To test the secured endpoints, you must follow these steps:

Register or Login: Use the initial screen to log in with one of the sample users (test_user_1/password123) or register a new one.

Token Storage: Upon successful login, the application stores the JWT in the browser's Local Storage.

Authorized Calls: All subsequent API calls (including fetching the pet list, adding, editing, or deleting pets) will automatically include the JWT in the Authorization: Bearer <token> header, satisfying the requirements of the Spring Boot controller.

If you attempt a secure action before logging in, the React application will receive a 401 Unauthorized response, which triggers the user to be directed back to the login screen.