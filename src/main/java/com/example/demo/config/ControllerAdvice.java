package com.example.demo.config;


import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestControllerAdvice
public class ControllerAdvice {


    Logger logger = LogManager.getLogger();
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception e) {
        e.printStackTrace();
        logger.error(e);
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}
