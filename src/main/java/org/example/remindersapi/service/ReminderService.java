package org.example.remindersapi.service;

import org.example.remindersapi.model.Reminder;

import java.util.List;
import java.util.Optional;

public interface ReminderService {

    Reminder create(Reminder reminder);

    List<Reminder> findAll();

    Optional<Reminder> findById(long id);
}
