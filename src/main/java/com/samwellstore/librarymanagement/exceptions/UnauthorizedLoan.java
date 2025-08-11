package com.samwellstore.librarymanagement.exceptions;

public class UnauthorizedLoan extends RuntimeException {
    public UnauthorizedLoan(String message) {
        super(message);
    }

    public UnauthorizedLoan(String resource, String field, Object value) {
        super(String.format("%s with %s '%s' is not authorized to perform this action", resource, field, value));
    }
}
