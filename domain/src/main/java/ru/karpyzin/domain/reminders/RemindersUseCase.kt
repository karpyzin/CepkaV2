package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow
import ru.karpyzin.domain.account.AccountService
import javax.inject.Inject


interface RemindersUseCase {
    val remindersFlow: Flow<List<ReminderModel>>

    suspend fun add(title: String, description: String?, date: Long)
    suspend fun complete(id: Int)
    suspend fun snooze(id: Int)
    suspend fun getTest(): List<ReminderModel>
    suspend fun uploadTest(list: List<ReminderModel>)
}

class RemindersUseCaseImpl @Inject constructor(
    private val remindersRepository: RemindersRepository,
    private val remindersService: RemindersService,
    private val accountService: AccountService
) : RemindersUseCase {
    override val remindersFlow: Flow<List<ReminderModel>>
        get() = remindersRepository.remindersFlow

    override suspend fun add(title: String, description: String?, date: Long) =
        remindersRepository.add(title, description, date)

    override suspend fun complete(id: Int) =
        remindersRepository.complete(id)

    override suspend fun snooze(id: Int) {
        val snoozeTime = 60 * 60 * 1000
        val currentDate = remindersRepository.getReminder(id)?.date

        if (currentDate != null) {
            remindersRepository.changeDate(id, currentDate + snoozeTime)
        }
    }

    override suspend fun getTest(): List<ReminderModel> {
        return emptyList() //remindersService.loadReminders(accountService.auth("karp123@inbox.ru", "111111").id)
    }

    override suspend fun uploadTest(list: List<ReminderModel>) {
        //remindersService.uploadReminders(accountService.auth("karp123@inbox.ru", "111111").id, list)
    }

}