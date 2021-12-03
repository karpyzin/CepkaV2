package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow

interface RemindersRepository {
    val reminders: List<Reminder>
    val remindersFlow: Flow<List<Reminder>>

    suspend fun add(title: String, description: String?, date: String)
    suspend fun complete(id: Int)
}