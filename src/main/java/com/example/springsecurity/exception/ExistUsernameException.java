package com.example.springsecurity.exception;

public class ExistUsernameException extends RuntimeException{
    public ExistUsernameException() {
        super();
    }

    public ExistUsernameException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Username already existed !";
    }
}
