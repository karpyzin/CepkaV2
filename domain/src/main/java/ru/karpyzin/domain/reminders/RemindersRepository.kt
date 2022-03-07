package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow

interface RemindersRepository {
    val remindersFlow: Flow<List<ReminderModel>>

    suspend fun add(title: String, description: String?, date: Long, emoji: String?)
    suspend fun changeDate(id: Int, newDate: Long)
    suspend fun getReminder(id: Int): ReminderModel?
    suspend fun complete(id: Int)
    suspend fun getTestData()
}