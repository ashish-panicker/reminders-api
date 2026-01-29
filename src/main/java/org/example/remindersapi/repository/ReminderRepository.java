package org.example.remindersapi.repository;

import org.example.remindersapi.model.Reminder;

import java.util.List;
import java.util.Optional;

public interface ReminderRepository {

    // Create, update, view, and delete reminders
    // Mark reminders as completed

    Reminder create(Reminder reminder);

    List<Reminder> findAll();

    Optional<Reminder> findById(long id);
}
