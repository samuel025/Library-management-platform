package com.samwellstore.librarymanagement.exceptions;


import com.samwellstore.librarymanagement.DTOs.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
