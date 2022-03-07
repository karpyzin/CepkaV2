package ru.karpyzin.domain.reminders

import kotlinx.coroutines.flow.Flow
import ru.karpyzin.domain.account.AccountService
import ru.karpyzin.domain.categories.CategoriesRepository
import javax.inject.Inject


interface RemindersUseCase {
    val remindersFlow: Flow<List<ReminderModel>>

    suspend fun add(title: String, description: String?, date: Long, emojiId: Int)
    suspend fun complete(id: Int)
    suspend fun snooze(id: Int)
    suspend fun getTest(): List<ReminderModel>
    suspend fun uploadTest(list: List<ReminderModel>)
}

class RemindersUseCaseImpl @Inject constructor(
    private val remindersRepository: RemindersRepository,
    private val remindersService: RemindersService,
    private val categoriesRepository: CategoriesRepository,
    private val accountService: AccountService
) : RemindersUseCase {
    override val remindersFlow: Flow<List<ReminderModel>>
        get() = remindersRepository.remindersFlow

    override suspend fun add(title: String, description: String?, date: Long, emojiId: Int) {
        val emoji = categoriesRepository.getEmoji(emojiId)

        remindersRepository.add(title, description, date, emoji)
    }

    override suspend fun complete(id: Int) =
        remindersRepository.complete(id)

    override suspend fun snooze(id: Int) {
        val snoozeTime = 60 * 60 * 1000
        val reminderDate = remindersRepository.getReminder(id)?.date ?: 0
        val currentDate = System.currentTimeMillis()
        val newDate = if (reminderDate < currentDate) {
            currentDate + snoozeTime
        } else {
            reminderDate + snoozeTime
        }

        remindersRepository.changeDate(id, newDate)
    }

    override suspend fun getTest(): List<ReminderModel> {
        return emptyList() //remindersService.loadReminders(accountService.auth("karp123@inbox.ru", "111111").id)
    }

    override suspend fun uploadTest(list: List<ReminderModel>) {
        //remindersService.uploadReminders(accountService.auth("karp123@inbox.ru", "111111").id, list)
    }

}