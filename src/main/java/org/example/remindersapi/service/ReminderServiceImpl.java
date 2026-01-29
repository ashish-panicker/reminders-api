package org.example.remindersapi.service;

import org.example.remindersapi.model.Reminder;
import org.example.remindersapi.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository repository;

    public ReminderServiceImpl(ReminderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reminder create(Reminder reminder) {
        return repository.create(reminder);
    }

    @Override
    public List<Reminder> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Reminder> findById(long id) {
        return repository.findById(id);
    }
}
