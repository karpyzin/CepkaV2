package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow

interface RemindersRepository {
    val reminderModels: List<ReminderModel>
    val remindersFlow: Flow<List<ReminderModel>>

    suspend fun add(title: String, description: String?, date: Long)
    suspend fun complete(id: Int)
    suspend fun getTestData()
}