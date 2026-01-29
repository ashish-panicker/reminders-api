package org.example.remindersapi.repository;

import org.example.remindersapi.model.Reminder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryReminderRepository implements ReminderRepository {

    private Map<Long, Reminder> reminders = new HashMap<>();
    private static long id = 1;

    @Override
    public Reminder create(Reminder reminder) {
        reminder.setId(id);
        id += 1;
        reminders.put(reminder.getId(), reminder);
        return reminder;
    }

    @Override
    public List<Reminder> findAll() {
        return new ArrayList<>(reminders.values());
    }

    @Override
    public Optional<Reminder> findById(long id) {
        return Optional.ofNullable(reminders.get(id));
    }
}
