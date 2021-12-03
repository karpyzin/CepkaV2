package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


interface RemindersUseCase {
    val reminders: List<Reminder>
    val remindersFlow: Flow<List<Reminder>>

    suspend fun add(title: String, description: String?, date: String)
    suspend fun complete(id: Int)
}

class RemindersUseCaseImpl @Inject constructor(
    private val remindersRepository: RemindersRepository
) : RemindersUseCase {
    override val reminders: List<Reminder>
        get() = remindersRepository.reminders
    override val remindersFlow: Flow<List<Reminder>>
        get() = remindersRepository.remindersFlow

    override suspend fun add(title: String, description: String?, date: String) =
        remindersRepository.add(title, description, date)

    override suspend fun complete(id: Int) =
        remindersRepository.complete(id)

}