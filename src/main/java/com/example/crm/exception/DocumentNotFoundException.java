package com.example.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(final String message) {
        super(message);
    }
}
