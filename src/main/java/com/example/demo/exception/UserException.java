package com.example.demo.exception;

public class UserException extends Exception{

    private String message;
    public UserException() {

    }
    public UserException(String message) {
        super(message);
    }
}
