package com.example.shortener.config;

import com.example.shortener.exceptions.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<String> dataNotFound(DataNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
}