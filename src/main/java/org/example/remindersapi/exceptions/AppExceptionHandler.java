package org.example.remindersapi.exceptions;

import org.example.remindersapi.dto.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ReminderNotFoundException.class)
    public ResponseEntity<CustomApiResponse> handleReminderNotFoundException(ReminderNotFoundException ex) {
        var apiResponse = new CustomApiResponse(
                "Reminder not found",
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<Object, Object>();

        ex.getFieldErrors()
                .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));

        var apiResponse = new CustomApiResponse(
                "Input not correct",
                HttpStatus.BAD_REQUEST,
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
