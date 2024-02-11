package com.example.shortener.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg) {
        super(msg);
    }
}
