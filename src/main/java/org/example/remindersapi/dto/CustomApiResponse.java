package org.example.remindersapi.dto;

import org.springframework.http.HttpStatus;

public record CustomApiResponse(
        String message,
        HttpStatus status,
        Object data
) {
}
