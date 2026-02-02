package org.example.remindersapi.repository;

import org.example.remindersapi.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}
