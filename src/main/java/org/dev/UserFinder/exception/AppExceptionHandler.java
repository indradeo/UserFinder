package org.dev.UserFinder.exception;

import org.dev.UserFinder.entity.User;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String argumentsHandler(MethodArgumentNotValidException e){
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                ));
        System.out.println("exception method");
        return errors.toString();
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public String userHandler(UserNotFoundException e){
        return e.getMessage();
    }
}
