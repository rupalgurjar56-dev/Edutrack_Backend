# EduTrack Spring Boot Backend Application

A RESTful web service built with **Spring Boot 3**, **Spring Data JPA**, **H2 Database**, and **Lombok** to provide backend services for the EduTrack application.

## 🚀 Features

- **Authentication & User Management**:
  - `POST /api/auth/register` - User registration (Teachers/Students)
  - `POST /api/auth/login` - Authenticate users with email & password
  - `POST /api/auth/logout` - User logout endpoint
  - `PUT /api/auth/users/{email}/status` - Toggle user status (`active` / `blocked`)
  - `DELETE /api/auth/users/{email}` - Remove user credentials

- **Student Management**:
  - `GET /api/students` - Retrieve all students (Supports search by name and filter by class)
  - `GET /api/students/{id}` - Retrieve student details by ID
  - `POST /api/students` - Create a new student (Auto-generates student login credentials)
  - `PUT /api/students/{id}` - Update student record (name, rollNo, class, attendance, marks, status)
  - `PATCH /api/students/{id}/status` - Toggle student status (`active` / `blocked`)
  - `DELETE /api/students/{id}` - Delete student record and corresponding login credentials

- **Database & Data Seeding**:
  - In-memory H2 database configured with web console enabled (`http://localhost:8080/h2-console`)
  - Automatic initial seeding of demo teacher account (`teacher@demo.com` / `password123`) and sample student records.

- **CORS Support**:
  - Configured for cross-origin access from `http://localhost:5173` (Vite dev server) and other local frontend clients.

---

## 🛠️ Prerequisites

- **Java Development Kit (JDK)**: 17+ or 21+ (Java 24 supported)
- **Apache Maven**: 3.8+

---

## 📦 How to Run

### 1. Start the Spring Boot Backend Server
Open terminal in the `EduTrack-Backend` directory and execute:

```bash
mvn spring-boot:run
```

Or using Maven executable path:
```powershell
C:\Maven\apache-maven-3.9.16\bin\mvn spring-boot:run
```

The application will start on **http://localhost:8080**.

### 2. Accessing H2 Database Console
Navigate to: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:edutrackdb`
- **User Name**: `sa`
- **Password**: *(leave blank)*

---

## 🔑 Default Seed Credentials

- **Teacher Credentials**:
  - **Email**: `teacher@demo.com`
  - **Password**: `password123`

- **Sample Student Credentials**:
  - **Email**: `rahul.101@edutrack.com` | **Password**: `pass101`
  - **Email**: `priya.102@edutrack.com` | **Password**: `pass102`
  - **Email**: `aman.103@edutrack.com` | **Password**: `pass103`
  - **Email**: `sneha.104@edutrack.com` | **Password**: `pass104`

---

## 🏗 Project Architecture

```
EduTrack-Backend
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── edutrack
    │   │           └── backend
    │   │               ├── EduTrackBackendApplication.java
    │   │               ├── config
    │   │               │   ├── CorsConfig.java
    │   │               │   └── DataInitializer.java
    │   │               ├── controller
    │   │               │   ├── AuthController.java
    │   │               │   └── StudentController.java
    │   │               ├── dto
    │   │               │   ├── AuthResponse.java
    │   │               │   ├── LoginRequest.java
    │   │               │   ├── RegisterRequest.java
    │   │               │   ├── StatusUpdateRequest.java
    │   │               │   └── StudentDto.java
    │   │               ├── entity
    │   │               │   ├── Student.java
    │   │               │   └── User.java
    │   │               ├── exception
    │   │               │   ├── BadRequestException.java
    │   │               │   ├── GlobalExceptionHandler.java
    │   │               │   └── ResourceNotFoundException.java
    │   │               ├── repository
    │   │               │   ├── StudentRepository.java
    │   │               │   └── UserRepository.java
    │   │               └── service
    │   │                   ├── AuthService.java
    │   │                   └── StudentService.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── com
                └── edutrack
                    └── backend
                        └── EduTrackBackendApplicationTests.java
```
