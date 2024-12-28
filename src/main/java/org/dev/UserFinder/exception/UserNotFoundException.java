package org.dev.UserFinder.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    String message;
    public UserNotFoundException(String message) {
        this.message=message;
    }
}
