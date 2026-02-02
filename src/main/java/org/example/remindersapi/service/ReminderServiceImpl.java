package org.example.remindersapi.service;

import org.example.remindersapi.exceptions.ReminderNotFoundException;
import org.example.remindersapi.model.Reminder;
import org.example.remindersapi.repository.ReminderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ReminderServiceImpl(ReminderRepository repository) {
        this.repository = repository;
        logger.debug("Injected the repository.");
    }

    @Override
    @Transactional
    public Reminder create(Reminder reminder) {
        logger.debug("Created the reminder: {}" + reminder.getTitle());
        return repository.save(reminder);
    }

    @Override
    public List<Reminder> findAll() {
        return repository.findAll();
    }

    @Override
    public Reminder findById(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ReminderNotFoundException("Cannot find reminder with id: " + id));
    }
}
