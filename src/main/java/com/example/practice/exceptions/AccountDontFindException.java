package com.example.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such account")
public class AccountDontFindException extends RuntimeException {
    public AccountDontFindException(Long accountId) {
        super("Can't find account with id - " + accountId);
    }
}
