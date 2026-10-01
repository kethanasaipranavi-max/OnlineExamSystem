# Online Examination System

A Java Swing-based **Online Examination System** developed as a college project. The application provides a graphical interface for students to attend subject-based multiple-choice examinations and for administrators to manage questions and view examination results.

## Project Overview

The Online Examination System is designed to conduct computer-based examinations using **Java Swing** and **MySQL**. Students can log in, select a subject, answer randomly selected questions, and receive their results immediately after submission.

Administrators can log in separately to manage examination questions and view student result history.

## Features

### Student Module

* Student login authentication
* Subject selection
* Four subjects:

  * Java Fundamentals
  * Machine Learning
  * Computer Networks
  * Operating Systems
* 10 random questions for each examination
* Multiple-choice questions using radio buttons
* Previous and Next navigation
* Clear answer option
* Question navigation palette
* 60-second examination timer
* Automatic submission when the timer expires
* Negative marking
* Answer review after submission
* Automatic score calculation
* Percentage calculation
* Pass/Fail result
* Result storage in MySQL

### Admin Module

* Admin login authentication
* Admin dashboard
* View examination result history
* Add new examination questions
* Select the correct answer while adding questions
* Refresh result history
* Logout functionality

## Technologies Used

* **Java**
* **Java Swing**
* **JDBC**
* **MySQL**
* **MySQL Connector/J**
* **ArrayList**
* **Object-Oriented Programming**
* **Event Handling**
* **Timer**
* **JOptionPane**

## Project Structure

```text
OnlineExamSystem/
│
├── .vscode/
│   └── settings.json
│
├── src/
│   ├── Question.java
│   ├── Database.java
│   ├── LoginFrame.java
│   ├── OnlineExam.java
│   └── AdminPanel.java
│
├── mysql/
│   └── exam_database.sql
│
└── lib/
    └── MySQL Connector/J
```

## Database

The application uses a MySQL database named:

```text
online_exam
```

The database contains the following tables:

* `users` – stores student and administrator login information
* `questions` – stores examination questions and correct answers
* `results` – stores examination results and history

## Login Credentials

### Student

```text
Username: student
Password: student
Role: STUDENT
```

### Admin

```text
Username: admin
Password: admin
Role: ADMIN
```

> These credentials are provided for demonstration and project testing purposes.

## Examination Details

Each examination contains **10 randomly selected questions** from the selected subject.

### Marking Scheme

```text
Correct answer   : +1 mark
Wrong answer     : -0.25 mark
Unanswered       : 0 mark
Pass percentage  : 40%
Time limit       : 60 seconds
```

The system automatically submits the examination when the timer reaches zero.

## Subjects

The system currently supports:

1. Java Fundamentals
2. Machine Learning
3. Computer Networks
4. Operating Systems

## Application Workflow

```text
Start Application
       ↓
     Login
       ↓
 ┌───────────────┐
 │               │
Student         Admin
 │               │
 ↓               ↓
Select Subject  Admin Panel
 │               │
 ↓               ├── View Results
Attend Exam      ├── Add Questions
 │               └── Logout
 ↓
Submit Exam
 │
 ↓
Calculate Result
 │
 ↓
Display Result
 │
 ↓
Store Result in MySQL
```

## Concepts Demonstrated

This project demonstrates the following programming concepts:

* Classes and Objects
* Constructors
* Encapsulation
* ArrayList
* Inheritance through Swing components
* Event Handling
* JDBC Database Connectivity
* MySQL CRUD Operations
* GUI Development using Java Swing
* Timer-based operations
* Exception Handling
* Random question selection

## Future Enhancements

Possible future improvements include:

* Secure password hashing
* Student registration
* Multiple examination categories
* Detailed student performance reports
* Question editing functionality
* Question search and filtering
* Examination scheduling
* Improved database security
* Online deployment

## Project Purpose

This project was developed as an academic project to demonstrate the implementation of a **Java-based Online Examination System** using GUI programming, database connectivity, object-oriented programming, and event-driven programming.

## Author

**Kethana Sai Pranavi Atmuri**

College Project – Online Examination System
