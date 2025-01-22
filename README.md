
# Employee-performance-App

This project is a web application for analyzing employee performance data using the Bell Curve methodology. 
The application includes a backend powered by Spring Boot for handling API requests and a frontend built with React.js for the user interface.

## Table of Contents
- [Backend (Spring Boot)](#backend-spring-boot)
- [Frontend (React.js)](#frontend-react.js)
- [Installation Instructions](#installation-instructions)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Environment Variables](#environment-variables)
- [Technologies Used](#technologies-used)

## Backend (Spring Boot)

The backend is built with **Spring Boot** and provides API endpoints to handle employee data, perform the Bell Curve analysis, and suggest performance adjustments.

### Requirements
- **Java 21** or higher
- **Maven**
- **MySQL** database (or another RDBMS)

### Backend Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-folder>
   ```

2. **Set up MySQL database**:
   - Create a MySQL database (e.g., `employee_performance`).
   - Update `application.properties` with your database credentials.

3. **Install dependencies**:
   - If you haven't installed Maven, you can install it globally or use the Maven wrapper:
     ```bash
     mvn clean install
     ```

4. **Configure `application.properties`**:
   Create or update the `src/main/resources/application.properties` file with the following settings:

   ```properties
   # Replace Username & Password with actual credential

   # Database connection details
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_performance
   spring.datasource.username=root
   spring.datasource.password=2bl19ec054
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

5. **Run the application**:
   - Start the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```

   - The backend will be available at `http://localhost:8080`.

### API Endpoints
-To get actual percentages: http://localhost:8080/performance/actual_percentages

-To get deviations b/w standard and actual percentages: http://localhost:8080/performance/deviations

## Frontend (React.js)

The frontend is built with **React.js** and provides a user interface for adding employees and viewing the Bell Curve analysis.

### Requirements
- **React.js** 
- **npm** (Node package manager)

### Frontend Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <frontend-folder>
   ```

2. **Install dependencies**:
   - Install the required packages:
     ```bash
     npm install
     ```

3. **Run the application**:
   - Start the React.js development server:
     ```bash
     npm run dev
     ```

   - The frontend will be available at `http://localhost:5173`.

## Running the Application

To run the application locally, follow these steps:

1. **Start the backend** (Spring Boot):
   ```bash
   mvn spring-boot:run
   ```

2. **Start the frontend** (React.js):
   ```bash
   npm run dev
   ```

Now, you can access the application at `http://localhost:5173/` and interact with the Bell Curve performance appraisal system.

## Environment Variables

### Backend
In the backend, you need to configure the following environment variables in `application.properties`:
- `spring.datasource.url`: Database URL
- `spring.datasource.username`: Database username
- `spring.datasource.password`: Database password

## Technologies Used

- **Backend**:
  - Spring Boot
  - MySQL
  - Hibernate
  - RESTful API
  - Java 21

- **Frontend**:
  - React.js
  - React
  - TailwindCSS
  - Axios / Fetch API (for making HTTP requests)


