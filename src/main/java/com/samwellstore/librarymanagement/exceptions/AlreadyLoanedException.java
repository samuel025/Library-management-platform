package com.samwellstore.librarymanagement.exceptions;

public class AlreadyLoanedException extends RuntimeException {
    public AlreadyLoanedException(String message) {
        super(message);
    }

    public AlreadyLoanedException(String resource, String field, Object value) {
        super(String.format("%s with %s '%s' is already loaned", resource, field, value));
    }
}
