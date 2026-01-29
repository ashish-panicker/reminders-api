package org.example.remindersapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.remindersapi.model.Priority;

import java.time.LocalDateTime;

@Schema(description = "Create Reminder Request")
public record CreateReminderRequest(

        @Schema(name = "title", description = "Title of the reminder", example = "Meet the doctor")
        String title,

        @Schema(name = "description", description = "More details about the reminder",
                example = "Meet the doctor for the agreeed consultation")
        String description,

        @Schema(name = "dueAt", description = "The date and time at which the reminder is due")
        LocalDateTime dueAt,

        @Schema(name = "priority", description = " Whether the reminder is a LOW, MEDIUM, HIGH, CRITICAL reminder")
        Priority priority
) {
}
