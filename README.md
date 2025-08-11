# Library Management System

A robust Spring Boot application for managing library resources including books, user loans, and administration functions.

## Features

- **User Authentication & Authorization**
  - JWT-based authentication system
  - Role-based access control (User, Admin)
  - Secure password handling with BCrypt encryption

- **Book Management**
  - Add, update, delete, and search books
  - Track book availability
  - Partial update support for book information

- **Loan System**
  - Borrow and return books
  - Prevent duplicate loans
  - Automatic inventory adjustment
  - User-specific loan tracking

- **Admin Features**
  - Register new admins
  - Full CRUD operations for books
  - User management



### Setup

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/library-management.git
   cd library-management
   ```

2. Start the database
   ```bash
   docker-compose up -d
   ```

3. Run the application
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the API at `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /register` - Register a new user
- `POST /login` - Login and get JWT token

### Books
- `GET /books` - List all books
- `GET /book/{bookId}` - Get book details by ID

### Loans
- `POST /loan/book/{bookId}` - Borrow a book
- `PUT /repay/loan/{loanId}` - Return a borrowed book

### Admin Endpoints
- `POST /admin/register` - Register a new admin
- `POST /admin/addBook` - Add a new book
- `PUT /admin/book/{bookId}` - Update book details
- `DELETE /admin/book/{bookId}` - Delete a book


```

## Security

The application uses JWT for stateless authentication. Tokens are valid for 15 minutes.

## Error Handling

Comprehensive exception handling with custom exceptions:
- ResourceNotFoundException
- AlreadyLoanedException
- UnauthorizedLoan
- LoanRepaidException
- UserAlreadyExistsException

## Architecture

The application follows a layered architecture:
- Controllers - Handle HTTP requests
- Services - Implement business logic
- Repositories - Data access layer
- DTOs - Data transfer objects
- Entities - JPA entities
- Mappers - Convert between DTOs and entities

