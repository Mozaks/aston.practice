package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Don't enough money to make action")
public class DontEnoughMoneyException extends RuntimeException {
    public DontEnoughMoneyException() {
        super("Don't enough money to make action");
    }
}
