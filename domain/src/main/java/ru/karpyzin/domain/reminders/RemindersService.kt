package ru.karpyzin.domain.reminders

interface RemindersService {
    suspend fun loadReminders(userId: String): List<ReminderModel>
    suspend fun uploadReminders(userId: String, reminders: List<ReminderModel>): Boolean
}