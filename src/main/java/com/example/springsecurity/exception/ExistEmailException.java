package com.example.springsecurity.exception;

public class ExistEmailException extends  RuntimeException{
    public ExistEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistEmailException() {
        super();
    }

    public ExistEmailException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return " Email already existed !";
    }
}
