package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface RemindersUseCase {
    val reminderModels: List<ReminderModel>
    val remindersFlow: Flow<List<ReminderModel>>

    suspend fun add(title: String, description: String?, date: String)
    suspend fun complete(id: Int)
}

class RemindersUseCaseImpl @Inject constructor(
    private val remindersRepository: RemindersRepository
) : RemindersUseCase {
    override val reminderModels: List<ReminderModel>
        get() = remindersRepository.reminderModels
    override val remindersFlow: Flow<List<ReminderModel>>
        get() = remindersRepository.remindersFlow

    override suspend fun add(title: String, description: String?, date: String) =
        remindersRepository.add(title, description, date)

    override suspend fun complete(id: Int) =
        remindersRepository.complete(id)

}