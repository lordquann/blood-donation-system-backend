package com.example.blood_donation.service;

import com.example.blood_donation.entity.Reminder;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public Reminder getReminderById(Integer id) {
        return reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id " + id));
    }

    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public Reminder updateReminder(Integer id, Reminder updatedReminder) {
        Reminder reminder = getReminderById(id);
        if (updatedReminder.getMember() != null) {
            reminder.setMember(updatedReminder.getMember());
        }
        if (updatedReminder.getMessage() != null && !updatedReminder.getMessage().isBlank()) {
            reminder.setMessage(updatedReminder.getMessage());
        }
        if (updatedReminder.getReminderDate() != null) {
            reminder.setReminderDate(updatedReminder.getReminderDate());
        }
        if (updatedReminder.getStatus() != null) {
            reminder.setStatus(updatedReminder.getStatus());
        }
        return reminderRepository.save(reminder);
    }

    public void deleteReminder(Integer id) {
        reminderRepository.deleteById(id);
    }
}
