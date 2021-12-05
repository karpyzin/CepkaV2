package ru.karpyzin.data.reminders

import retrofit2.http.GET
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.reminders.RemindersService
import javax.inject.Inject

class RemindersServiceImpl @Inject constructor(
    private val remindersApi: RemindersApi,
    private val reminderItemMapper: ReminderItemMapper
) : RemindersService {
    override suspend fun loadReminders(userId: String): List<ReminderModel> {
        return reminderItemMapper.convertList(remindersApi.getList())
    }

    override suspend fun uploadReminders(userId: String, reminders: List<ReminderModel>): Boolean {
        return true
    }

    interface RemindersApi {
        @GET("reminders")
        suspend fun getList(): List<ReminderItemData>
    }
}