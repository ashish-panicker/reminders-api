package org.example.remindersapi.exceptions;

public class ReminderNotFoundException extends RuntimeException {
    public ReminderNotFoundException(String s) {
        super(s);
    }
}
