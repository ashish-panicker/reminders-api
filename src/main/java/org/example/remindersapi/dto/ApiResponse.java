package org.example.remindersapi.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse(
        String message,
        HttpStatus status,
        Object data
) {
}
