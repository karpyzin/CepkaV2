package ru.karpyzin.data.reminders

import com.google.gson.reflect.TypeToken
import ru.karpyzin.data.firestore.FireStore
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.reminders.RemindersService
import javax.inject.Inject

class RemindersServiceImpl @Inject constructor(private val fireStore: FireStore) : RemindersService {
    override suspend fun loadReminders(userId: String): List<ReminderModel> {
        val type = object : TypeToken<List<ReminderModel>>() {}.type
        return fireStore.getData(FireStore.REMINDERS, userId, type)
    }

    override suspend fun uploadReminders(userId: String, reminders: List<ReminderModel>): Boolean {
        return fireStore.uploadData(FireStore.REMINDERS, userId, reminders)
    }
}