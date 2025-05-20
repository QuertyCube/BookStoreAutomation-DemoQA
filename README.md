# Case Study 1: Payment Aggregator - Settlement Request Flow Testing

This case study covers the validation of the **Settlement Request Flow** feature on the Merchant Dashboard, which allows merchants to request settlements for their account balances.

All detailed test cases — including scenarios, steps, expected results, and mapping to user stories — have been compiled into an Excel document.

📂  **Test Case File** : https://docs.google.com/spreadsheets/d/1wup5dPOytKZ6LsreFH9bVlZtBeQbFaFA/edit?usp=sharing&ouid=112773817720273419085&rtpof=true&sd=true

# Automation Scripts (DemoQA Bookstore)

This project contains automated test scripts for the DemoQA Bookstore application hosted at [https://demoqa.com/books](https://demoqa.com/books). The tests ensure that the application's features are visually correct, functional, and properly integrated.

## **Run a Specific Test Class**

To execute a specific test class, use the `-Dtest` option:

`mvn test -Dtest="ClassName"`

For example, to run each class on this project:

`mvn -Dtest=DeleteAccountTest test`

`mvn -Dtest=DeleteBookTest test`

`mvn -Dtest=LoginTest test`

`mvn -Dtest=LogoutTest test`

`mvn -Dtest=RegisterTest test`

`mvn -Dtest=SearchBookTest test`

## Run a Specific Test Method

To execute a specific test method within a class:

`mvn test -Dtest="ClassName#methodName"`

For example, to run the `userCanSearchBook` method in the `SearchBookTest` class:

`mvn test -Dtest="SearchBookTest#userCanSearchBook"`

---

## Features Tested

1. **Delete Account**
2. **Delete Book from Profile**
3. **Login Functionality**
4. **Logout Functionality**
5. **Register New User**
6. **Search Functionality**

---

## Tools and Frameworks

- **Programming Language**: Java
- **Automation Framework**: Selenium WebDriver
- **Environment Management**: Dotenv
- **Build Tool**: Maven
- **Browser**: Google Chrome (via WebDriverManager)

---

## Test Plan

### **Objective**

To ensure that the bookstore application at `https://demoqa.com/books` is visually correct, functional, and properly integrated by automating test cases for various features.

---

### **Test Scenarios**

### 1. Delete Account

#### Positive Cases

- User can delete account successfully. *(Implemented)*

---

### 2. Delete Book from Profile ( Mostly Not Implemented, cause unable Add and Open book Detail)

#### Positive Cases

- User can delete a specific book from their profile. *(Not Implemented)*
- User can delete all books from their profile. *(Not Implemented)*

#### Negative Cases

- User can delete all books from their profile when book is empty. *(Implemented)*

---

### 3. Login Functionality

#### Positive Cases

- User can log in with valid credentials. *(Implemented)*

#### Negative Cases

- User cannot log in with invalid username/password. *(Implemented)*
- Error message is displayed for invalid credentials. *(Implemented)*
- User cannot log in with empty username or password fields. *(Implemented)*

---

### 4. Logout Functionality

#### Positive Cases

- User can log out successfully. *(Implemented)*

#### Negative Cases

- User cannot access profile page after logging out. *(Implemented)*

---

### 5. Register New User

#### Positive Cases

- User can register with valid details. *(Not Implemented)*
- User is redirected to the login page after successful registration. *(Not Implemented)*

#### Negative Cases

- Error message is displayed for invalid or missing registration details. *(Implemented)*
- User cannot register without verifying the reCAPTCHA. *(Implemented)*

---

### 6. Search Functionality

#### Positive Cases

- User can search for a book by title. *(Implemented)*
- Search results are case-insensitive. *(Implemented)*

#### Negative Cases

- No results are displayed for invalid search queries. *(Implemented)*

---

### **Test Data**

- **Environment Variables** (stored in `.env` file):
  - `username`, `password` for login.
  - `firstName`, `lastName`, `usernameDelete`, `passwordDelete` for registration and deletion tests.
- **Search Queries**:
  - Valid: `"Git Pocket Guide"`, `"Learning JavaScript"`.
  - Invalid: `"NonExistentBook123"`.

---

### **Test Execution**

1. **Setup**:
   - Initialize WebDriver and navigate to the application.
   - Load environment variables for test data.
2. **Test Execution**:
   - Execute test cases using TestNG with priority-based execution.
   - Validate UI elements, functionality, and error handling.
3. **Teardown**:
   - Clear cookies and close the browser after each test.

---

### **Reporting**

- Generate test reports using TestNG.
- Log errors and failed test cases for debugging.

---

### API Testing
