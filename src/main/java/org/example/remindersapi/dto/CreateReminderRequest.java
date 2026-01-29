package org.example.remindersapi.dto;

import org.example.remindersapi.model.Priority;

import java.time.LocalDateTime;

public record CreateReminderRequest(
        String title,
        String description,
        LocalDateTime dueAt,
        Priority priority
) { }
