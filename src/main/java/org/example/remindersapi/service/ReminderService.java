package org.example.remindersapi.service;

import org.example.remindersapi.model.Reminder;

import java.util.List;

public interface ReminderService {

    Reminder create(Reminder reminder);

    List<Reminder> findAll();

    Reminder findById(long id);
}
