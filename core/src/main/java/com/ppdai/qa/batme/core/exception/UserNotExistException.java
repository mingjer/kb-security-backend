package com.ppdai.qa.batme.core.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("user not found");
    }
}
