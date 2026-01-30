package org.example.remindersapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.remindersapi.dto.CreateReminderRequest;
import org.example.remindersapi.dto.CustomApiResponse;
import org.example.remindersapi.model.Reminder;
import org.example.remindersapi.model.Status;
import org.example.remindersapi.service.ReminderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
@Tag(name = "Reminders", description = "Reminders Management API")
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
     * @return a {@link ResponseEntity} containing a {@link CustomApiResponse} with a success
     * message, HTTP 201 status, and the created reminder object
     */

    @Operation(summary = "Create a new Reminder",
            description = "Create a new reminder and the persist it in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reminder created sucessfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse> createReminder(
            @Parameter(
                    name = "Create reminder request",
                    description = "The JSON data send by the client to create the reminder")
            @RequestBody @Valid CreateReminderRequest request
    ) {

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
        var apiResponse = new CustomApiResponse(
                "Reminder created",
                HttpStatus.CREATED,
                createdRem
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getReminderById(@PathVariable long id) {
        var reminder = service.findById(id);
        var apiResponse = new CustomApiResponse(
                "Reminder found",
                HttpStatus.OK,
                reminder
        );
        return ResponseEntity.ok(apiResponse);
    }


}
