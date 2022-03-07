package ru.karpyzin.data.reminders

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.karpyzin.data.db.AppDatabase
import ru.karpyzin.data.db.entity.ReminderEntity
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.reminders.RemindersRepository
import javax.inject.Inject

class RemindersRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val reminderEntityTransformer: ReminderEntityMapper
) : RemindersRepository {

    private val remindersDao = appDatabase.remindersDao()

    override val remindersFlow: Flow<List<ReminderModel>>
        get() = remindersDao.getAllFlow().map { list -> list.map { reminderEntityTransformer.fromEntity(it) } }

    override suspend fun add(title: String, description: String?, date: Long, emoji: String?) {
        remindersDao.addReminder(ReminderEntity(0, title, description, date, emoji = emoji))
    }

    override suspend fun changeDate(id: Int, newDate: Long) {
        remindersDao.changeDate(id, newDate)
    }

    override suspend fun getReminder(id: Int): ReminderModel? {
        return remindersDao.get(id)?.let { reminderEntityTransformer.fromEntity(it) }
    }

    override suspend fun complete(id: Int) {
        remindersDao.complete(id)
    }

    override suspend fun getTestData() {
    }
}