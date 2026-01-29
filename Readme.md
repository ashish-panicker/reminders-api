# Reminders Management API – Spring Boot

## 1. Problem Statement

In many applications (task management systems, healthcare follow-ups, personal productivity apps, CRM systems, etc.), users need a reliable way to manage time-based reminders.

The objective of this project is to build a **Reminders Management REST API** that allows clients to:

* Create reminders for future actions
* View and manage existing reminders
* Update reminder details
* Mark reminders as completed
* Delete reminders safely
* Search and filter reminders based on status, priority, and due date

The system must ensure:

* Data validation
* Proper error handling
* Clean RESTful API design
* Extensibility for future features like recurring reminders and notifications

---

## 2. High-Level Features

* Create, update, view, and delete reminders
* Mark reminders as completed
* Filter reminders by:

    * Status (PENDING, COMPLETED, CANCELLED)
    * Priority (LOW, MEDIUM, HIGH, CRITICAL)
    * Due date
* Pagination and sorting support
* Soft delete support
* Centralized exception handling
* Validation of input data

---

## 3. Base URL

```
/api/reminders
```

---

## 4. Data Model

### Reminder

| Field       | Type          | Description                   |
| ----------- | ------------- | ----------------------------- |
| id          | Long          | Unique identifier             |
| title       | String        | Short title of reminder       |
| description | String        | Optional detailed description |
| dueAt       | LocalDateTime | When the reminder is due      |
| status      | Enum          | PENDING, COMPLETED, CANCELLED |
| priority    | Enum          | LOW, MEDIUM, HIGH, CRITICAL   |
| createdAt   | LocalDateTime | Created timestamp             |
| updatedAt   | LocalDateTime | Last updated timestamp        |
| deleted     | boolean       | Soft delete flag              |

---

## 5. API Endpoints

---

### 5.1 Create Reminder

### Endpoint

```
POST /api/reminders
```

### Description

Creates a new reminder.

### Request Body

```json
{
  "title": "Doctor Appointment",
  "description": "Visit cardiologist",
  "dueAt": "2026-02-01T10:00:00",
  "priority": "HIGH"
}
```

### Response (201 Created)

```json
{
  "id": 1,
  "title": "Doctor Appointment",
  "description": "Visit cardiologist",
  "dueAt": "2026-02-01T10:00:00",
  "status": "PENDING",
  "priority": "HIGH",
  "createdAt": "2026-01-29T14:30:00",
  "updatedAt": null
}
```

### Validations

* title must not be blank
* dueAt must not be null
* dueAt must be a future date/time
* priority must be a valid enum value (if provided)

### Exceptions

| Exception                       | When Raised          |
| ------------------------------- | -------------------- |
| ValidationException             | title is blank       |
| ValidationException             | dueAt is null        |
| ValidationException             | dueAt is in the past |
| HttpMessageNotReadableException | Invalid enum value   |

---

### 5.2 Get Reminder By ID

### Endpoint

```
GET /api/reminders/{id}
```

### Description

Fetch a reminder by its unique ID.

### Response (200 OK)

```json
{
  "id": 1,
  "title": "Doctor Appointment",
  "description": "Visit cardiologist",
  "dueAt": "2026-02-01T10:00:00",
  "status": "PENDING",
  "priority": "HIGH",
  "createdAt": "2026-01-29T14:30:00",
  "updatedAt": null
}
```

### Validations

* id must be a positive number

### Exceptions

| Exception                           | When Raised                     |
| ----------------------------------- | ------------------------------- |
| ReminderNotFoundException           | No reminder exists for given id |
| MethodArgumentTypeMismatchException | id is not a number              |

---

### 5.3 List / Search Reminders

### Endpoint

```
GET /api/reminders
```

### Description

Returns a paginated list of reminders with optional filters.

### Query Parameters

| Parameter | Type          | Description                    |
| --------- | ------------- | ------------------------------ |
| status    | Enum          | Filter by status               |
| priority  | Enum          | Filter by priority             |
| dueBefore | LocalDateTime | Reminders due before this time |
| page      | int           | Page number                    |
| size      | int           | Page size                      |
| sort      | String        | Sort field and direction       |

### Example

```
/api/reminders?status=PENDING&priority=HIGH&dueBefore=2026-02-10T00:00:00&page=0&size=10
```

### Response (200 OK)

```json
{
  "content": [
    {
      "id": 1,
      "title": "Doctor Appointment",
      "description": "Visit cardiologist",
      "dueAt": "2026-02-01T10:00:00",
      "status": "PENDING",
      "priority": "HIGH",
      "createdAt": "2026-01-29T14:30:00",
      "updatedAt": null
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 1,
  "totalPages": 1
}
```

### Validations

* status must be valid enum
* priority must be valid enum
* dueBefore must be valid datetime format

### Exceptions

| Exception                           | When Raised             |
| ----------------------------------- | ----------------------- |
| MethodArgumentTypeMismatchException | Invalid enum value      |
| DateTimeParseException              | Invalid datetime format |

---

### 5.4 Update Reminder

### Endpoint

```
PUT /api/reminders/{id}
```

### Description

Updates an existing reminder.

### Request Body

```json
{
  "title": "Doctor Follow-up",
  "description": "Follow-up consultation",
  "dueAt": "2026-02-05T11:00:00",
  "priority": "CRITICAL"
}
```

### Response (200 OK)

```json
{
  "id": 1,
  "title": "Doctor Follow-up",
  "description": "Follow-up consultation",
  "dueAt": "2026-02-05T11:00:00",
  "status": "PENDING",
  "priority": "CRITICAL",
  "createdAt": "2026-01-29T14:30:00",
  "updatedAt": "2026-01-29T15:00:00"
}
```

### Validations

* id must be positive
* title must not be blank
* dueAt must be future date/time
* priority must be valid enum

### Exceptions

| Exception                 | When Raised             |
| ------------------------- | ----------------------- |
| ReminderNotFoundException | Reminder does not exist |
| ValidationException       | Invalid request fields  |

---

### 5.5 Mark Reminder as Completed

### Endpoint

```
PATCH /api/reminders/{id}/complete
```

### Description

Marks a reminder as COMPLETED.

### Response

```
204 No Content
```

### Validations

* id must be positive

### Exceptions

| Exception                 | When Raised                |
| ------------------------- | -------------------------- |
| ReminderNotFoundException | Reminder does not exist    |
| IllegalStateException     | Reminder already completed |

---

### 5.6 Delete Reminder (Soft Delete)

### Endpoint

```
DELETE /api/reminders/{id}
```

### Description

Soft deletes a reminder.

### Response

```
204 No Content
```

### Validations

* id must be positive

### Exceptions

| Exception                 | When Raised              |
| ------------------------- | ------------------------ |
| ReminderNotFoundException | Reminder does not exist  |
| IllegalStateException     | Reminder already deleted |

---

## 6. Validations Summary

| Field     | Rule                     |
| --------- | ------------------------ |
| title     | Required, not blank      |
| dueAt     | Required, must be future |
| priority  | Must be valid enum       |
| status    | Must be valid enum       |
| id        | Must be positive         |
| dueBefore | Must be valid datetime   |

---

## 7. Exception Handling & Error Responses

All errors are returned in a consistent format:

```json
{
  "code": "REMINDER_NOT_FOUND",
  "message": "Reminder not found with id: 99"
}
```

### Common Exceptions

| Error Code         | HTTP Status | Scenario                |
| ------------------ | ----------- | ----------------------- |
| REMINDER_NOT_FOUND | 404         | Reminder ID not found   |
| VALIDATION_ERROR   | 400         | Bean validation failure |
| INVALID_ENUM       | 400         | Invalid enum in request |
| INVALID_DATE_TIME  | 400         | Invalid date format     |
| INTERNAL_ERROR     | 500         | Unexpected server error |

---

## 8. Future Enhancements

* User-based reminders (multi-user support)
* Recurring reminders
* Notification engine (Email/SMS/Push)
* Scheduler integration (Quartz)
* Audit trail & history
* Role-based access control

---