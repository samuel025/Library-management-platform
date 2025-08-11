package com.samwellstore.librarymanagement.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, String field, Object value){
        super(String.format("%s with %s '%s' not found", resource, field, value));
    }
}
