package com.samwellstore.librarymanagement.exceptions;


import com.samwellstore.librarymanagement.DTOs.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        private String getRequestPath(WebRequest webRequest){
            if(webRequest instanceof ServletWebRequest){
                return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
            }
            return "";
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
                ResourceNotFoundException ex, WebRequest request
        ) {
            log.error("Resource not found: {}", ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    "Resource not found",
                    ex.getMessage(),
                    getRequestPath(request),
                    "RESOURCE_NOT_FOUND"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(AlreadyLoanedException.class)
        public  ResponseEntity<ErrorResponse> handleAlreadyLoanedException(
            AlreadyLoanedException ex, WebRequest request
            ){
                log.error("Already loaned: {}", ex.getMessage());
                ErrorResponse errorResponse = ErrorResponse.of(
                        "Yoy have already loaned this book",
                        ex.getMessage(),
                        getRequestPath(request),
                        "ALREADY_LOANED"
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(LoanRepaidException.class)
        public ResponseEntity<ErrorResponse> handleLoanRepaidException(
                LoanRepaidException ex, WebRequest request
        ) {
            log.error("Loan already repaid: {}", ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    "Loan already repaid",
                    ex.getMessage(),
                    getRequestPath(request),
                    "LOAN_REPAID"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(UnauthorizedLoan.class)
        public ResponseEntity<ErrorResponse> handleUnauthorizedLoan(
                UnauthorizedLoan ex, WebRequest request
        ) {
            log.error("Unauthorized loan: {}", ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    "Unauthorized action",
                    ex.getMessage(),
                    getRequestPath(request),
                    "UNAUTHORIZED_LOAN"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
                UserAlreadyExistsException ex, WebRequest request
        ) {
            log.error("User already exists: {}", ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    "User already exists",
                    ex.getMessage(),
                    getRequestPath(request),
                    "USER_ALREADY_EXISTS"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.error("Illegal argument exception: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.of(
                "Invalid Argument",
                ex.getMessage(),
                getRequestPath(request),
                "INVALID_ARGUMENT"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {
        log.error("Bad credentials exception: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.of(
                "Invalid Credentials",
                "The provided credentials are invalid",
                getRequestPath(request),
                "INVALID_CREDENTIALS"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }


}
