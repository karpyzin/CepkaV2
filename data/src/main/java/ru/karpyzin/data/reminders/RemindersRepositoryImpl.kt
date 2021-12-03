package ru.karpyzin.data.reminders

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.domain.reminders.Reminder
import ru.karpyzin.domain.reminders.RemindersRepository
import javax.inject.Inject

class RemindersRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val reminderEntityTransformer: ReminderEntityTransformer
) : RemindersRepository {

    private val remindersDao = appDatabase.remindersDao()

    override val reminders: List<Reminder>
        get() = remindersDao.getAll().map { reminderEntityTransformer.fromEntity(it) }
    override val remindersFlow: Flow<List<Reminder>>
        get() = remindersDao.getAllFlow()
            .map { list -> list.map { reminderEntityTransformer.fromEntity(it) } }

    override suspend fun add(title: String, description: String?, date: String) {
        remindersDao.addReminder(ReminderEntity(0, title, description, date))
    }

    override suspend fun complete(id: Int) {
        remindersDao.delete(id)
    }
}