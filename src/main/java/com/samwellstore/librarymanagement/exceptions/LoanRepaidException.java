package com.samwellstore.librarymanagement.exceptions;

public class LoanRepaidException extends RuntimeException {
    public LoanRepaidException(String message) {
        super(message);
    }

    public LoanRepaidException(String resource, String field, Object value) {
        super(String.format("%s with %s '%s' has already been repaid", resource, field, value));
    }
}
