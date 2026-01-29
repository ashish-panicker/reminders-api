package org.example.remindersapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.remindersapi.dto.CreateReminderRequest;
import org.example.remindersapi.dto.ApiResponse;
import org.example.remindersapi.model.Reminder;
import org.example.remindersapi.model.Status;
import org.example.remindersapi.service.ReminderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService service;

    /**
     * Handles the creation of a new reminder.
     * <p>
     * This endpoint accepts a {@link CreateReminderRequest} containing the reminder details,
     * maps it to a {@link Reminder} entity, initializes system-managed fields such as
     * timestamps, deletion flag, and default status, and persists it using the service layer.
     * <p>
     * Example Request Body:
     * <pre>
     * {
     *   "title": "Doctor Appointment",
     *   "description": "Visit cardiologist",
     *   "dueAt": "2026-02-01T10:00:00",
     *   "priority": "HIGH"
     * }
     * </pre>
     *
     * @param request the reminder creation payload containing title, description,
     *                due date, and priority
     * @return a {@link ResponseEntity} containing a {@link ApiResponse} with a success
     *         message, HTTP 201 status, and the created reminder object
     */

    @PostMapping
    public ResponseEntity<ApiResponse> createReminder(@RequestBody CreateReminderRequest request) {

        // Validating the request is pending

        // Create the Reminder based on the NewReminderDto
        var reminder = Reminder.builder()
                                .title(request.title())
                                .description(request.description())
                                .dueAt(request.dueAt())
                                .priority(request.priority())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(null)
                                .isDeleted(false)
                                .status(Status.PENDING)
                                .build();

        // Forward the request to the service layer, which will
        // then forward it to the repository and then to the database
        var createdRem = service.create(reminder);

        // Building the response object
        var apiResponse = new ApiResponse(
                "Reminder created",
                HttpStatus.CREATED,
                createdRem
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);

    }
}
